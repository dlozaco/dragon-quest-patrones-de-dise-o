package com.taller.patrones.interfaces.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.patrones.domain.BattleFacade;
import com.taller.patrones.domain.Character;
import com.taller.patrones.domain.adapters.ExternalFighterAdapter;
import com.taller.patrones.domain.adapters.FighterAdapter;
import com.taller.patrones.domain.dtos.BattleDTO;

@RestController
@RequestMapping("/api/battle")
@CrossOrigin(origins = "*")
public class BattleController {

    private final BattleFacade battleFacade;

    public BattleController(BattleFacade battleFacade) {
        this.battleFacade = battleFacade;
    }

    @PostMapping("/start")
    public ResponseEntity<BattleDTO> startBattle(@RequestBody(required = false) Map<String, String> body) {
        String playerName = body != null && body.containsKey("playerName") ? body.get("playerName") : null;
        String enemyName = body != null && body.containsKey("enemyName") ? body.get("enemyName") : null;

        BattleDTO result = battleFacade.startBattle(playerName, enemyName);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint alternativo: recibe datos de combate en formato "externo".
     * Los campos vienen con nombres distintos (fighter1_hp, fighter1_atk...).
     * La conversión se hace aquí, manualmente, en el controller.
     */
    @PostMapping("/start/external")
    public ResponseEntity<BattleDTO> startBattleFromExternal(@RequestBody Map<String, Object> body) {
        FighterAdapter fighterAdapter = new ExternalFighterAdapter();
        Character player = fighterAdapter.toPlayer(body);
        Character enemy = fighterAdapter.toEnemy(body);

        BattleDTO result = battleFacade.startBattleExternal(player, enemy);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{battleId}")
    public ResponseEntity<BattleDTO> getBattle(@PathVariable String battleId) {
        BattleDTO battle = battleFacade.getBattle(battleId);
        if (battle == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(battle);
    }

    @PostMapping("/{battleId}/attack")
    public ResponseEntity<BattleDTO> attack(@PathVariable String battleId,
                                            @RequestBody Map<String, String> body) {
        BattleDTO battle = battleFacade.getBattle(battleId);
        if (battle == null) return ResponseEntity.notFound().build();

        String attackName = body != null && body.get("attack") != null ? body.get("attack") : "TACKLE";

        boolean isPlayerTurn = "player".equals(battle.currentTurn());
        BattleDTO updatedBattle = battleFacade.executeAttack(battleId, attackName, isPlayerTurn);

        return ResponseEntity.ok(updatedBattle);
    }

    @PostMapping("/{battleId}/enemy-turn")
    public ResponseEntity<BattleDTO> enemyTurn(@PathVariable String battleId) {
        BattleDTO battle = battleFacade.getBattle(battleId);
        if (battle == null) return ResponseEntity.notFound().build();
        if ("player".equals(battle.currentTurn()) || battle.finished()) {
            return ResponseEntity.ok(battle);
        }
        
        // This simulates selecting a random enemy attack
        String attack = battle.enemyAttacks().get((int) (Math.random() * battle.enemyAttacks().size()));
        BattleDTO updatedBattle = battleFacade.executeAttack(battleId, attack, false);
        return ResponseEntity.ok(updatedBattle);
    }

    @PostMapping("/{battleId}/undo")
    public ResponseEntity<BattleDTO> undoLastAttack(@PathVariable String battleId) {
        BattleDTO battle = battleFacade.getBattle(battleId);
        if (battle == null) {
            return ResponseEntity.notFound().build();
        }

        BattleDTO updatedBattle = battleFacade.undoAttack(battleId);
        return ResponseEntity.ok(updatedBattle);
    }
}
