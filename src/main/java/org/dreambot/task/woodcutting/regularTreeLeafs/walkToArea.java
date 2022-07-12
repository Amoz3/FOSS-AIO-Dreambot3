package org.dreambot.task.woodcutting.regularTreeLeafs;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;


public class walkToArea extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return !normalTree.contains(Players.localPlayer()) && Inventory.contains(woodcuttingAPI.bestAxe());
    }

    @Override
    public int onLoop() {
        Walking.walk(normalTree);
        return (int) Calculations.nextGaussianRandom(350, 250);
    }
    Area normalTree = new Area(3185, 3207, 3196, 3223);

}
