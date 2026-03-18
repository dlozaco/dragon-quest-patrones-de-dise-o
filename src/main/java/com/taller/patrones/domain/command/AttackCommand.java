package com.taller.patrones.domain.command;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Battle;
import com.taller.patrones.domain.Character;


public class AttackCommand implements Command {

    private final Battle battle;
    private final Character attacker;
    private final Character defender;
    private final Attack attack;
    private final int damage;
    private final String logEntry;

    public AttackCommand(Battle battle, Character attacker, Character defender,
                         Attack attack, int damage, String logEntry) {
        this.battle = battle;
        this.attacker = attacker;
        this.defender = defender;
        this.attack = attack;
        this.damage = damage;
        this.logEntry = logEntry;
    }

    @Override
    public void execute() {
        defender.takeDamage(damage);
        String target = defender == battle.getPlayer() ? "player" : "enemy";
        battle.setLastDamage(damage, target);
        battle.log(logEntry);
        battle.switchTurn();
        if (!defender.isAlive()) {
            battle.finish(attacker.getName());
        }
    }

    @Override
    public void undo() {
        defender.heal(damage);
        battle.removeLastLog();
        battle.switchTurn();
        defender.revive();
    }
}