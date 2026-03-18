package com.taller.patrones.domain;

import com.taller.patrones.application.BattleService;
import com.taller.patrones.domain.dtos.BattleDTO;
import com.taller.patrones.domain.dtos.CharacterDTO;
import com.taller.patrones.infrastructure.persistence.BattleRepository;
import org.springframework.stereotype.Component;

@Component
public class BattleFacade {

    private final BattleService battleService;
    private final BattleRepository battleRepository;

    public BattleFacade(BattleService battleService) {
        this.battleService = battleService;
        this.battleRepository = BattleRepository.getInstance();
    }

    public BattleDTO startBattle(String playerName, String enemyName) {
        BattleService.BattleStartResult result = battleService.startBattle(playerName, enemyName);
        return toBattleDTO(result.battleId(), result.battle());
    }

    public BattleDTO startBattleExternal(Character player, Character enemy) {
        BattleService.BattleStartResult result = battleService.startBattleFromExternal(player, enemy);
        return toBattleDTO(result.battleId(), result.battle());
    }

    public BattleDTO executeAttack(String battleId, String attackName, boolean isPlayer) {
        if (isPlayer) {
            battleService.executePlayerAttack(battleId, attackName);
        } else {
            battleService.executeEnemyAttack(battleId, attackName);
        }
        Battle battle = battleRepository.findById(battleId);
        return toBattleDTO(battleId, battle);
    }

    public BattleDTO undoAttack(String battleId) {
        battleService.undoLastAttack(battleId);
        Battle battle = battleRepository.findById(battleId);
        return toBattleDTO(battleId, battle);
    }

    private BattleDTO toBattleDTO(String battleId, Battle battle) {
        return new BattleDTO(
            battleId,
            toCharacterDTO(battle.getPlayer()),
            toCharacterDTO(battle.getEnemy()),
            battle.getCurrentTurn(),
            battle.getBattleLog(),
            battle.isFinished(),
            BattleService.PLAYER_ATTACKS,
            BattleService.ENEMY_ATTACKS,
            battle.getLastDamage(),
            battle.getLastDamageTarget() != null ? battle.getLastDamageTarget() : ""
        );
    }

    private CharacterDTO toCharacterDTO(Character c) {
        return new CharacterDTO(
                c.getName(),
                c.getCurrentHp(),
                c.getMaxHp(),
                c.getHpPercentage(),
                c.getAttack(),
                c.getDefense(),
                c.getSpeed(),
                c.isAlive()
        );
    }

    public BattleDTO getBattle(String battleId) {
        Battle battle = battleRepository.findById(battleId);
        if (battle == null) return null;
        return toBattleDTO(battleId, battle);
    }
}
