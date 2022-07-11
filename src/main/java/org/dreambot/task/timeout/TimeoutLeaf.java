package org.dreambot.task.timeout;

import org.dreambot.Main;
import org.dreambot.framework.Leaf;

public class TimeoutLeaf extends Leaf<Main> {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public int onLoop() {
        return 0;
    }
}
