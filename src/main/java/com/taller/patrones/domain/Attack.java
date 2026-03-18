package com.taller.patrones.domain;

import java.util.List;

/**
 * Representa un ataque que puede ejecutar un personaje.
 */
public class Attack {

    protected final String name;
    protected final int basePower;
    protected final AttackType type;

    public Attack(String name, int basePower, AttackType type) {
        this.name = name;
        this.basePower = basePower;
        this.type = type;
    }

    public String getName() { return name; }
    public int getBasePower() { return basePower; }
    public AttackType getType() { return type; }

    
    public List<Attack> getHits() {
        return List.of(this);
    }
}
