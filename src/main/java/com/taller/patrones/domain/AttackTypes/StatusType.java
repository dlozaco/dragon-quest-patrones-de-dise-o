package com.taller.patrones.domain.AttackTypes;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.DamageStrategy;
import com.taller.patrones.domain.Character;

public class StatusType implements DamageStrategy {

    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return 0;
    }
}
