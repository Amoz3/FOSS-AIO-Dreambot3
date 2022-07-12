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
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;


public class DepositAllExceptAxe extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return BankLocation.GRAND_EXCHANGE.getArea(5).contains(Players.localPlayer()) && !Inventory.onlyContains(woodcuttingAPI.bestAxe());
    }

    @Override
    public int onLoop() {
        if (Bank.isOpen()){
            Bank.depositAllExcept(woodcuttingAPI.bestAxe());
            Bank.close();
        }
        else{
            Bank.open();
        }

        return (int) Calculations.nextGaussianRandom(350, 250);
    }

}
