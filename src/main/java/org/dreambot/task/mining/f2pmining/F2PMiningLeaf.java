package org.dreambot.task.mining.f2pmining;

import org.dreambot.Main;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.util.Timing;
/*
    goal of this leaf is to level as fast as possible
    1 - 15 copper ore @ east lumbridge swamp
    15 - 99 iron ore @
 */
public class F2PMiningLeaf extends Leaf<Main> {
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
            if (Walking.shouldWalk() && Bank.openClosest()) {
                return 100;
            }
        }


        return Timing.loopReturn();
    }
}
