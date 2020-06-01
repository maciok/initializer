package com.macpi.initializer.core.hello;

import static java.lang.String.format;

import lombok.Value;

@Value
class HelloMessage {
    private final String message;

    HelloMessage(final String name) {
        this.message = format("Hello to %s", name);
    }
}
