package com.taller.patrones.domain.Attacks;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.AttackI;
import com.taller.patrones.domain.AttackType;

public class DefaultAttack implements AttackI{
    @Override
    public Attack createAttack() {
        return new Attack("Golpe", 30, AttackType.NORMAL);
    }
}
