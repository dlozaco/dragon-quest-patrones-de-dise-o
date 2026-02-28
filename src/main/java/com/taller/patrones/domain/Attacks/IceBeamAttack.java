package com.taller.patrones.domain.Attacks;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.AttackI;
import com.taller.patrones.domain.AttackType;

public class IceBeamAttack implements AttackI {
    @Override
    public Attack createAttack() {
        return new Attack("Ice Beam", 70, AttackType.SPECIAL);
    }
}