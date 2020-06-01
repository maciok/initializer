package com.macpi.initializer.core.hello;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class HelloWorld {
    @GetMapping(value = "/hello/{name}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<HelloMessage> sayHelloTo(@PathVariable final String name) {
        log.info("'{}' is calling", name);
        final var message = new HelloMessage(name);
        return ok(message);
    }
}
