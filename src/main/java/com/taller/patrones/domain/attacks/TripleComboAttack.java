package com.taller.patrones.domain.attacks;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.AttackI;
import com.taller.patrones.domain.ComboAttack;

public class TripleComboAttack implements AttackI {
    @Override
    public Attack createAttack() {
        ComboAttack combo = new ComboAttack("Triple Combo");
        combo.addAttack(new TackleAttack().createAttack());
        combo.addAttack(new SlashAttack().createAttack());
        combo.addAttack(new FireballAttack().createAttack());
        return combo;
    }
}
