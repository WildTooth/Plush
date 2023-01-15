package com.github.wildtooth.plush.auth;

import java.util.ArrayList;
import java.util.UUID;

public class AuthHandler {
    private ArrayList<UUID> authLocked;

    public AuthHandler() {
        authLocked = new ArrayList<>();
    }

}
