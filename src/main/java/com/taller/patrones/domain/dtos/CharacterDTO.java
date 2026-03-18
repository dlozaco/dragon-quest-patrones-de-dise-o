package com.taller.patrones.domain.dtos;

public record CharacterDTO(
    String name,
    int currentHp,
    int maxHp,
    double hpPercentage,
    int attack,
    int defense,
    int speed,
    boolean alive
) {}
