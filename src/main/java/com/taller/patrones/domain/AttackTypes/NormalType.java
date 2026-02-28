package com.taller.patrones.domain.AttackTypes;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.DamageI;
import com.taller.patrones.domain.Character;

public class NormalType implements DamageI {
    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        int raw = attacker.getAttack() * attack.getBasePower() / 100;
        return Math.max(1, raw - defender.getDefense());
    }
}
