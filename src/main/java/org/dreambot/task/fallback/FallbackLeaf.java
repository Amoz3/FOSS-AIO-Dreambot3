package org.dreambot.task.fallback;

import org.dreambot.Main;
import org.dreambot.framework.Leaf;
import org.dreambot.util.Timing;

public class FallbackLeaf extends Leaf<Main> {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        return Timing.loopReturn();
    }
}
