package org.dreambot.task.fishing;

import org.dreambot.Main;
import org.dreambot.api.Client;
import org.dreambot.data.Activities;
import org.dreambot.framework.Branch;

public class F2PFishingBranch extends Branch<Main> {
    @Override
    public boolean isValid() {
        return config.getActivity().equals(Activities.FISHING) && !Client.isMembers();
    }
}
