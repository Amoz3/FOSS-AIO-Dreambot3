package org.dreambot.config;

import org.dreambot.data.Activities;

public class Config {
    private Config() {}
    private static final Config config = new Config();
    public static Config getConfig() {
        return config;
    }

    private Activities activity = Activities.NONE;
}
