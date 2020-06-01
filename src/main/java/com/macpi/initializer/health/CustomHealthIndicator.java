package com.macpi.initializer.health;

import static org.springframework.boot.actuate.health.Status.UP;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = CustomHealthIndicator.ROOT, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
class CustomHealthIndicator {
  static final String ROOT = "/";

  private final HealthEndpoint healthEndpoint;
  private final InfoEndpoint infoEndpoint;

  @GetMapping("/health")
  ResponseEntity<Void> checkHealth() {
    final Status appStatus = healthEndpoint.health().getStatus();

    if (UP.equals(appStatus)) {
      return ok().build();
    } else {
      return status(INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/info")
  ResponseEntity<Map<String, Object>> getAppInfo() {
    return ok(infoEndpoint.info());
  }

}
