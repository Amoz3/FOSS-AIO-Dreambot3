package org.dreambot.task.mining;

import org.dreambot.Main;
import org.dreambot.api.Client;
import org.dreambot.data.Activities;
import org.dreambot.framework.Branch;
/*
    The reason this is a whole branch despite having only 1 leaf (as of writing this) is for future updates,
    in the future we might add settings such as prefer money making or prefer speed
    speed would 15-99 powermining iron ore,
    money making leaf might bank iron until 70, then go to adamantite then runite.
 */
public class F2PMiningBranch extends Branch<Main> {
    @Override
    public boolean isValid() {
        return config.getActivity().equals(Activities.MINING) && !Client.isMembers();
    }
}
