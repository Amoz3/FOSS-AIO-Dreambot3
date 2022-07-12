package org.dreambot.task.woodcutting.oakTreeLeafs;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;



public class WalkToOak extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return !oakTree.contains(Players.localPlayer()) && Inventory.contains(woodcuttingAPI.bestAxe());
    }

    @Override
    public int onLoop() {
        Walking.walk(oakTree);
        return (int) Calculations.nextGaussianRandom(350, 250);
    }


    Area oakTree = new Area(3092, 3289, 3107, 3282);
}
