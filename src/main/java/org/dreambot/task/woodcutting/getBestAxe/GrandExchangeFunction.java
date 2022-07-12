package org.dreambot.task.woodcutting.getBestAxe;

import org.dreambot.Main;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.grandexchange.LivePrices;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.data.woodcutting.woodcuttingAPI;

public class GrandExchangeFunction extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return !Inventory.contains(woodcuttingAPI.bestAxe());
    }

    @Override
    public int onLoop() {

        if (!BankLocation.GRAND_EXCHANGE.getArea(5).contains(Players.localPlayer())){
            Walking.walk(BankLocation.GRAND_EXCHANGE.getCenter());
        }
        else if(BankLocation.GRAND_EXCHANGE.getArea(5).contains(Players.localPlayer())){

            if (!Bank.isOpen() && Inventory.count("Coins") < LivePrices.getHigh(woodcuttingAPI.bestAxe())){
                Bank.openClosest();
                MethodProvider.sleepUntil(()->Bank.isOpen(), 5000);
            }
            else if (Bank.isOpen()){
                if (Bank.contains("Coins")){
                    Bank.withdrawAll("Coins");
                    Bank.close();
                }


            }
            else if(Inventory.count("Coins") > LivePrices.getHigh(woodcuttingAPI.bestAxe())){
                if(GrandExchange.isOpen()){

                    if(GrandExchange.buyItem(woodcuttingAPI.bestAxe(),1,LivePrices.getHigh(woodcuttingAPI.bestAxe()))){
                        MethodProvider.sleepUntil(()->GrandExchange.isReadyToCollect(), 10000000);
                        if (GrandExchange.isReadyToCollect()){
                            GrandExchange.collect();
                            GrandExchange.close();
                        }
                    }

                }
                else if (!GrandExchange.isOpen()){
                    GrandExchange.open();
                    MethodProvider.sleepUntil(()->GrandExchange.isOpen(), 5000);
                }
            }


        }


        return (int) Calculations.nextGaussianRandom(350, 250);
    }
}
