package org.dreambot.util;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.Entity;

public class Interaction {
    public static boolean delayEntityInteract(Entity entity, String action, long sleepDelay) {
        MethodProvider.sleep((int) sleepDelay);
        return entity.interact(action);
    }

    public static boolean delayEntityInteract(Entity entity, long sleepDelay) {
        MethodProvider.sleep((int) sleepDelay);
        return entity.interact();
    }
}
