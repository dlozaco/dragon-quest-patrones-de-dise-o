package com.taller.patrones.domain;

import com.taller.patrones.domain.attackTypes.*;

public class DamageFactory {

    public static DamageStrategy getStrategy(AttackType type) {
        return switch (type) {
            case NORMAL -> new NormalType();
            case SPECIAL -> new SpecialType();
            case STATUS -> new StatusType();
            case CRITIC -> new CriticType();
        };
    }
}
