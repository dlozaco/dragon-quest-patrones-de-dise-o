package com.taller.patrones.domain.events;

import org.springframework.stereotype.Component;

@Component
public class AuditLogObserver implements DamageObserver {
    @Override
    public void onDamageOccurred(DamageEvent event) {
        System.out.println("[AUDIT] " + event.getAttacker().getName() +
                " atacó a " + event.getDefender().getName() +
                " con " + event.getAttack().getName());
    }
}