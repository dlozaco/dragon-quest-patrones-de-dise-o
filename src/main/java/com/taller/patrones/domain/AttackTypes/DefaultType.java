package com.taller.patrones.domain.AttackTypes;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.DamageI;

public class DefaultType implements DamageI {

    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        return 0;
    }
}
