package com.taller.patrones.domain.events;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;

public class DamageEvent {
    private final Character attacker;
    private final Character defender;
    private final int damage;
    private final Attack attack;
    private final String battleId;

    public DamageEvent(com.taller.patrones.domain.Character attacker, com.taller.patrones.domain.Character defender, int damage, Attack attack, String battleId) {
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.attack = attack;
        this.battleId = battleId;
    }

    public com.taller.patrones.domain.Character getAttacker() { return attacker; }
    public Character getDefender() { return defender; }
    public int getDamage() { return damage; }
    public Attack getAttack() { return attack; }
    public String getBattleId() { return battleId; }
}
