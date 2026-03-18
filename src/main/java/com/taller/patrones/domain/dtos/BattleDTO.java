package com.taller.patrones.domain.dtos;

import java.util.List;

public record BattleDTO (
    String battleId,
    CharacterDTO player,
    CharacterDTO enemy,
    String currentTurn,
    List<String> battleLog,
    boolean finished,
    List<String> playerAttacks,
    List<String> enemyAttacks,
    int lastDamage,
    String lastDamageTarget
) {
}
