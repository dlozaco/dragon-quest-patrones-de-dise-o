package com.taller.patrones.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite class para manejar un grupo de ataques como si fuera uno solo.
 */
public class ComboAttack extends Attack {
    private final List<Attack> subAttacks = new ArrayList<>();

    public ComboAttack(String name) {
        super(name, 0, AttackType.NORMAL);
    }

    public void addAttack(Attack attack) {
        subAttacks.add(attack);
    }

    @Override
    public List<Attack> getHits() {
        List<Attack> hits = new ArrayList<>();
        for (Attack subAttack : subAttacks) {
            hits.addAll(subAttack.getHits());
        }
        return hits;
    }
}
