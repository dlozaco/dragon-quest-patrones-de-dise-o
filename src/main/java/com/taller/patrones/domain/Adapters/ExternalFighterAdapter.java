package com.taller.patrones.domain.Adapters;

import java.util.Map;
import com.taller.patrones.domain.Character;

public class ExternalFighterAdapter implements FighterAdapter{

    @Override
    public Character toPlayer(Map<String, Object> body){
        return Character.builder()
                .name((String) body.getOrDefault("fighter1_name", "Héroe"))
                .hp(((Number) body.getOrDefault("fighter1_hp", 150)).intValue())
                .attack(((Number) body.getOrDefault("fighter1_atk", 25)).intValue())
                .build();
    }

    @Override
    public Character toEnemy(Map<String, Object> body){
        return Character.builder()
                .name((String) body.getOrDefault("fighter2_name", "Dragón"))
                .hp(((Number) body.getOrDefault("fighter2_hp", 150)).intValue())
                .attack(((Number) body.getOrDefault("fighter2_atk", 25)).intValue())
                .build();
    }
}
