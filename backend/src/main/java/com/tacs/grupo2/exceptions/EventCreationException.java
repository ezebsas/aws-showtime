package com.tacs.grupo2.exceptions;

import java.util.List;

public class EventCreationException extends RuntimeException {
    public EventCreationException(List<String> errors) {
        super("Error creating event: \n" + String.join("\n", errors));
    }
}
