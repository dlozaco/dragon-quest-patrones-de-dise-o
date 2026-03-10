package com.taller.patrones.domain.Builders;

import com.taller.patrones.domain.Character;

public class CharacterBuilder {

    private String name = "Unknown";
    private int maxHp = 100;
    private int attack = 10;
    private int defense = 10;
    private int speed = 10;

    public CharacterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder hp(int maxHp) {
        this.maxHp = maxHp;
        return this;
    }

    public CharacterBuilder attack(int attack) {
        this.attack = attack;
        return this;
    }

    public CharacterBuilder defense(int defense) {
        this.defense = defense;
        return this;
    }

    public CharacterBuilder speed(int speed) {
        this.speed = speed;
        return this;
    }

    public Character build() {
        return new Character(name, maxHp, attack, defense, speed);
    }

    public void reset() {
        this.name = "Unknown";
        this.maxHp = 100;
        this.attack = 10;
        this.defense = 10;
        this.speed = 10;
    }
}