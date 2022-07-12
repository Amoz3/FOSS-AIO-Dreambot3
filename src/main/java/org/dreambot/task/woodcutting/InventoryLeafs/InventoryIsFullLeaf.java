package org.dreambot.task.woodcutting.InventoryLeafs;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;


import java.io.IOException;


public class InventoryIsFullLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Inventory.isFull();
    }

    @Override
    public int onLoop() {

        Inventory.dropAllExcept(woodcuttingAPI.bestAxe(), "Coins");



        return (int) Calculations.nextGaussianRandom(350, 250);
    }


}
