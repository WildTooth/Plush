package com.github.wildtooth.plush.exceptions;

public final class HookNotEnabledException extends RuntimeException {
    /**
     * Constructs a new HookNotEnabledException with the specified detail message.
     *
     * @param message the detail message.
     */
    public HookNotEnabledException(String message) {
        super(message);
    }
}
