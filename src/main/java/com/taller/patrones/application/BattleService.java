package com.taller.patrones.application;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.command.AttackCommand;
import com.taller.patrones.domain.command.Command;
import com.taller.patrones.domain.events.AnalyticsObserver;
import com.taller.patrones.domain.events.AuditLogObserver;
import com.taller.patrones.domain.events.DamageEvent;
import com.taller.patrones.domain.events.DamageObserver;
import com.taller.patrones.infrastructure.combat.CombatEngine;
import com.taller.patrones.infrastructure.persistence.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso: gestionar batallas.
 * <p>
 * Nota: Crea sus propias dependencias con new. Cada vez que necesitamos
 * un CombatEngine o BattleRepository, hacemos new aquí.
 */
@Service
public class BattleService {

    private final CombatEngine combatEngine = new CombatEngine();
    private final BattleRepository battleRepository = BattleRepository.getInstance();
    private final List<DamageObserver> damageObservers = new ArrayList<>();

    public static final List<String> PLAYER_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL", "ICE_BEAM", "POISON_STING", "THUNDER", "METEOR");
    public static final List<String> ENEMY_ATTACKS = List.of("TACKLE", "SLASH", "FIREBALL");

    public BattleService(AnalyticsObserver analyticsObserver, AuditLogObserver auditLogObserver) {
        subscribe(analyticsObserver);
        subscribe(auditLogObserver);
    }

    public void subscribe(DamageObserver observer){
        damageObservers.add(observer);
    }

    public void unsubscribe(DamageObserver damageObserver){
        damageObservers.remove(damageObserver);
    }

    private void notifyDamage(DamageEvent event) {

        if (damageObservers.isEmpty()) {
            System.out.println("[BattleService DEBUG] ¡¡No hay observadores!!");
            return;
        }

        for (DamageObserver observer : damageObservers) {
            observer.onDamageOccurred(event);
        }
    }


    public BattleStartResult startBattle(String playerName, String enemyName) {
        Character player = Character.builder()
                .name(playerName != null ? playerName : "Héroe")
                .hp(150)
                .attack(25)
                .defense(15)
                .speed(20)
                .build();

        Character enemy = Character.builder()
                .name(enemyName != null ? enemyName : "Dragón")
                .hp(120)
                .attack(30)
                .build();


        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);

        return new BattleStartResult(battleId, battle);
    }

    public Battle getBattle(String battleId) {
        return battleRepository.findById(battleId);
    }

    public void executePlayerAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || !battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackName);
        int damage = combatEngine.calculateDamage(battle.getPlayer(), battle.getEnemy(), attack);
        applyDamage(battle, battle.getPlayer(), battle.getEnemy(), damage, attack);
    }

    public void executeEnemyAttack(String battleId, String attackName) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null || battle.isFinished() || battle.isPlayerTurn()) return;

        Attack attack = combatEngine.createAttack(attackName != null ? attackName : "TACKLE");
        int damage = combatEngine.calculateDamage(battle.getEnemy(), battle.getPlayer(), attack);
        applyDamage(battle, battle.getEnemy(), battle.getPlayer(), damage, attack);
    }

    private void applyDamage(Battle battle, Character attacker, Character defender, int damage, Attack attack) {
        String logEntry = attacker.getName() + " usa " + attack.getName() +
                " y hace " + damage + " de daño a " + defender.getName();

        Command cmd = new AttackCommand(battle, attacker, defender, attack, damage, logEntry);
        cmd.execute();
        battle.addCommand(cmd);

        DamageEvent event = new DamageEvent(attacker, defender, damage, attack, "battleId");
        notifyDamage(event);
    }

    public void undoLastAttack(String battleId) {
        Battle battle = battleRepository.findById(battleId);
        if (battle != null) {
            battle.undoLastCommand();
        }
    }

    public BattleStartResult startBattleFromExternal(Character player, Character enemy) {
        Battle battle = new Battle(player, enemy);
        String battleId = UUID.randomUUID().toString();
        battleRepository.save(battleId, battle);
        return new BattleStartResult(battleId, battle);
    }

    public record BattleStartResult(String battleId, Battle battle) {}
}
