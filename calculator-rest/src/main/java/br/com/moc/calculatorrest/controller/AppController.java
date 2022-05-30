package br.com.moc.calculatorrest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api")
@Slf4j
public class AppController {

    @GetMapping(value = "/health")
    public ResponseEntity<Object> healthCheck() {
        log.info("Api request on health check ... ");
        return ResponseEntity.ok(new HashMap<>(
                Map.of("Status", "App is running health.")
        ));
    }

}