package com.taller.patrones.domain.Attacks;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.AttackI;
import com.taller.patrones.domain.AttackType;

public class TackleAttack implements AttackI {
    @Override
    public Attack createAttack() {
        return new Attack("Tackle", 40, AttackType.NORMAL);
    }
}
