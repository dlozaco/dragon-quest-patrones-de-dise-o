package com.taller.patrones.domain;

public interface DamageStrategy {
    int calculateDamage(Character attacker, Character defender, Attack attack);
}
