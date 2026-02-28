package com.taller.patrones.domain;

import com.taller.patrones.domain.AttackTypes.DefaultType;
import com.taller.patrones.domain.AttackTypes.NormalType;
import com.taller.patrones.domain.AttackTypes.SpecialType;
import com.taller.patrones.domain.AttackTypes.StatusType;

public class DamageFactory {

    public static DamageI getDamage(AttackType attackType){
        return switch (attackType){
            case NORMAL -> new NormalType();
            case SPECIAL -> new SpecialType();
            case STATUS -> new StatusType();
            default -> new DefaultType();
        };
    }
}
