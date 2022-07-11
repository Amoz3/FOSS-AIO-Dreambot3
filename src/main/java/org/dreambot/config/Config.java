package org.dreambot.config;

public class Config {
    private Config() {}
    private static final Config config = new Config();

    public static Config getConfig() {
        return config;
    }
}
