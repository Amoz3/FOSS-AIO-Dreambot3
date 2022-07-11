package org.dreambot.util;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
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

    // inventory interact is a little safer because it checks you are on the right tab
    public static boolean delayInventoryInteract(int id, String action, long sleepDelay) {
        MethodProvider.sleep((int) sleepDelay);
        return Inventory.interact(id, action);
    }

    public static boolean delayInventoryInteract(String name, String action, long sleepDelay) {
        MethodProvider.sleep((int) sleepDelay);
        return Inventory.interact(name, action);
    }
}
