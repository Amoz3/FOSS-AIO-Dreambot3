package org.dreambot.task.fishing.f2pfishing;

import org.dreambot.Main;
import org.dreambot.framework.Leaf;
import org.dreambot.util.Timing;

public class F2PFishingLeaf extends Leaf<Main> {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        return Timing.loopReturn();
    }
}
