package com.taller.patrones.domain.events;

import org.springframework.stereotype.Component;

@Component
public class AnalyticsObserver implements DamageObserver{
    @Override
    public void onDamageOccurred(DamageEvent event) {
        System.out.println("[ANALYTICS] Daño registrado: " + event.getDamage() +
                " de " + event.getAttacker().getName() +
                " a " + event.getDefender().getName());
    }
}
