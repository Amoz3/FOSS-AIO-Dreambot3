package org.dreambot.task.woodcutting.WillowLeafs;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;


public class WalkToWillow extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return !WillowTree.contains(Players.localPlayer()) && Inventory.contains(woodcuttingAPI.bestAxe());
    }

    @Override
    public int onLoop() {
        Walking.walk(WillowTree);
        return (int) Calculations.nextGaussianRandom(350, 250);
    }


    Area WillowTree = new Area(3081, 3238, 3089, 3227);
}
