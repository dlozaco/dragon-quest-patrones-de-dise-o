package com.taller.patrones.domain.Attacks;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.AttackI;
import com.taller.patrones.domain.AttackType;

public class SlashAttack implements AttackI {
    @Override
    public Attack createAttack() {
        return new Attack("Slash", 55, AttackType.NORMAL);
    }
}
