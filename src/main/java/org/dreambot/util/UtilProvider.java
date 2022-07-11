package org.dreambot.util;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;

public class UtilProvider {
    // this just saves me from writing the shouldwalk check every time, also lets me update all walking to support
    // stamina drinking, run on attack etc
    public static void stdWalk(Area area) {
        if (Walking.shouldWalk() && (Walking.getDestination() == null || !area.contains(Walking.getDestination()))) {
            Walking.walk(area.getCenter());
        }
    }
}
