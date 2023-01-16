package com.github.wildtooth.plush.event.code;

import org.bukkit.event.Event;

/**
 * The type Code event.
 */
public abstract class CodeEvent extends Event {
    /**
     * The Security Code.
     */
    protected Integer code;

    /**
     * Instantiates a new Code event.
     *
     * @param theCode the security code
     */
    public CodeEvent(Integer theCode) {
        this.code = theCode;
    }

    /**
     * @return the security code used in the event.
     */
    public final Integer getCode() {
        return code;
    }

}
