package com.taller.patrones.infrastructure.combat;

import com.taller.patrones.domain.*;
import com.taller.patrones.domain.Character;

public class CombatEngine {

    /**
     * Crea un ataque a partir de su nombre.
     */
    public Attack createAttack(String attackName) {
        AttackI attackI = AttackFactory.getAttack(attackName);
        return attackI.createAttack();
    }

    /**
     * Calcula el daño según el tipo de ataque.
     */
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        DamageStrategy damageStrategy = DamageFactory.getStrategy(attack.getType());
        return damageStrategy.calculateDamage(attacker, defender, attack);
    }
}
