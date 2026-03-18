package com.taller.patrones.domain.attackTypes;

import com.taller.patrones.domain.Attack;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.DamageStrategy;

import java.util.Random;

public class CriticType implements DamageStrategy {
    private final Random random = new Random();

    @Override
    public int calculateDamage(Character attacker, Character defender, Attack attack) {
        int baseDamage = (attacker.getAttack() + attack.getBasePower()) - defender.getDefense();

        if(random.nextInt(100) < 20){
            return (int) (baseDamage * 1.5);
        }

        return baseDamage;
    }
}
