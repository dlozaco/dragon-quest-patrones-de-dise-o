package com.taller.patrones.domain.Adapters;

import java.util.Map;
import com.taller.patrones.domain.Character;

public interface FighterAdapter {
    Character toPlayer(Map<String, Object> body);
    Character toEnemy(Map<String, Object> body);
}
