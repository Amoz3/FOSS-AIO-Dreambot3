package org.dreambot.framework;


import org.dreambot.api.script.AbstractScript;
import org.dreambot.config.Config;

public abstract class Leaf<T extends AbstractScript> {

    protected Config config = Config.getConfig();

    public abstract boolean isValid();

    public abstract int onLoop();
}
