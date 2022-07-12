package org.dreambot.task.woodcutting.oakTreeLeafs;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;



public class ChopOak extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return oakTree.contains(Players.localPlayer()) && !Inventory.isFull();
    }

    @Override
    public int onLoop() {
        GameObject tree = GameObjects.closest("Oak");
        if (tree != null) {

            if(tree.interact("Chop down")) {
                MethodProvider.sleep(4000);
                MethodProvider.sleepUntil(() -> !Players.localPlayer().isAnimating() && !Players.localPlayer().isMoving(), 10000);
            }
        }
        return (int) Calculations.nextGaussianRandom(350, 250);
    }
    Area oakTree = new Area(3092, 3289, 3107, 3282);
}
