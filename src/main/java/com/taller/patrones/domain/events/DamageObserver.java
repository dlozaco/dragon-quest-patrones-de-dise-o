package com.taller.patrones.domain.events;

public interface DamageObserver {
    void onDamageOccurred(DamageEvent event);
}
