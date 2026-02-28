package com.taller.patrones.domain;

import com.taller.patrones.domain.Attacks.*;

public class AttackFactory {

    public static AttackI getAttack(String attackName) {
        return switch (attackName.toUpperCase()) {
            case "SLASH" -> new SlashAttack();
            case "FIREBALL" -> new FireballAttack();
            case "ICE_BEAM" -> new IceBeamAttack();
            case "POISON_STING" -> new PoisonStingAttack();
            case "THUNDER" -> new ThunderAttack();
            case "METEOR" -> new MeteorAttack();
            case "TACKLE" -> new TackleAttack();
            default -> throw new IllegalArgumentException("Ataque no reconocido: " + attackName);
        };
    }
}
