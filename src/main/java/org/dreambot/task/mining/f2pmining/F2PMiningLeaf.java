package org.dreambot.task.mining.f2pmining;

import org.dreambot.Main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.data.mining.MineLocations;
import org.dreambot.data.mining.Pickaxe;
import org.dreambot.data.mining.Rock;
import org.dreambot.framework.Leaf;
import org.dreambot.util.Interaction;
import org.dreambot.util.Timing;
import org.dreambot.util.UtilProvider;

/*
    goal of this leaf is to level as fast as possible
    1 - 15 copper ore @ east lumbridge swamp
    15 - 99 iron ore @
 */
public class F2PMiningLeaf extends Leaf<Main> {
    Pickaxe bestOwnedPickaxe;
    int cachedLvl = 0; // this is to skip the for loop until you have leveled up
    // used to detect if you have items you shouldnt when mining so you can go put them away
    Filter<Item> miningFilter = x -> !x.getName().toLowerCase().contains("ore")
            && !x.getName().toLowerCase().contains("uncut")
            && !x.getName().toLowerCase().contains("pickaxe");
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        /*
            before going to mine we must consider the bot could be anywhere doing anything when this is leaf starts
            as this is going to powermine we need to
            ensure we are using the best pickaxe we own, (not going to think about buying items just yet)
            ensure we dont have items from another activity in our inventory, as those would get dropped upon full inv
         */

        // check we have a bank cache (so we cant find what the best pickaxe we have is), if we dont go open bank
        if (Bank.getLastBankHistoryCacheTime() <= 0) {
            if (Walking.shouldWalk()) {
                Bank.openClosest();
            }
            return 100;
        }
        // now we have a bank cache, we need to find our best owned pickaxe and make sure we have that equipped / in inv
        for (Pickaxe p : Pickaxe.values()) {
            if (cachedLvl == Skills.getRealLevel(Skill.MINING)) {
                break; // skip loop if you have already found the best pickaxe for this level
            }
            if (p.getREQ() > Skills.getRealLevel(Skill.MINING)) {
                continue;
            }
            if (Inventory.contains(p.ID) || Equipment.contains(p.ID) || Bank.contains(p.ID)) {
                bestOwnedPickaxe = p;
                cachedLvl = Skills.getRealLevel(Skill.MINING);
            }
        }

        if (bestOwnedPickaxe == null) {
            // todo handle buying
            MethodProvider.log("we dont own any pickaxe!");
            return -1;
        }

        // if you need to get out your pickaxe or deposit items, do that.
        if ((!Inventory.contains(bestOwnedPickaxe.ID) && !Equipment.contains(bestOwnedPickaxe.ID))
                || Inventory.contains(miningFilter)) {
            if (Walking.shouldWalk() && Bank.openClosest()) {
                if (Inventory.contains(miningFilter)) {
                    Bank.depositAllItems();
                    return Timing.loopReturn();
                }
                Bank.withdraw(bestOwnedPickaxe.ID);
            }
            return Timing.loopReturn();
        }

        // from here we should only have our best pickaxe and an empty inventory
        if (Bank.isOpen() && Bank.close()) {
            return Timing.loopReturn();
        }

        if (Inventory.contains(bestOwnedPickaxe.ID) && bestOwnedPickaxe.ATKREQ <= Skills.getRealLevel(Skill.ATTACK)) {
            Interaction.delayInventoryInteract(bestOwnedPickaxe.ID, "Wield", Timing.getSleepDelay());
            return Timing.loopReturn();
        }

        // mine copper @ lummy swamp east
        if (Skills.getRealLevel(Skill.MINING) < 15) {
            if (!MineLocations.LUMMY_SWAMP_EAST.LOCATION.contains(Players.localPlayer())) {
                UtilProvider.stdWalk(MineLocations.LUMMY_SWAMP_EAST.LOCATION);
                return 100;
            }

            if (Inventory.isFull()) {
                Inventory.dropAllExcept(x -> x.getName().contains("pickaxe"));
                return Timing.loopReturn();
            }

            GameObject closestCopper = Rock.COPPER.getRockWithOres(Rock.COPPER);
            if (closestCopper != null && Interaction.delayEntityInteract(closestCopper, Timing.getSleepDelay())) {
                MethodProvider.sleepUntil(() -> Players.localPlayer().isAnimating(), 3000);
                MethodProvider.sleepUntil(() -> !Players.localPlayer().isAnimating(), 45000);
            }
            return Timing.loopReturn();
        }

        return Timing.loopReturn();
    }
}
