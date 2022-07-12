package org.dreambot.task.woodcutting;

import org.dreambot.Main;
import org.dreambot.api.Client;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.data.Activities;
import org.dreambot.framework.Root;
import org.dreambot.data.woodcutting.woodcuttingAPI;

public class WillowBranch extends Root<Main> {
    @Override
    public boolean isValid() {
        return config.getActivity().equals(Activities.WOODCUTTING) && !Client.isMembers() && Skills.getRealLevel(Skill.WOODCUTTING) >= 30 && Inventory.contains(woodcuttingAPI.bestAxe()) && !Inventory.isFull();
    }

}
