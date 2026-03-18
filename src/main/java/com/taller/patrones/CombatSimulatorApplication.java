package com.taller.patrones;

import com.taller.patrones.application.BattleService;
import com.taller.patrones.domain.events.AnalyticsObserver;
import com.taller.patrones.domain.events.AuditLogObserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CombatSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombatSimulatorApplication.class, args);

    }
}
