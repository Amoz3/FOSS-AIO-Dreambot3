package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.config.Config;
import org.dreambot.data.Activities;
import org.dreambot.framework.Tree;
import org.dreambot.task.fallback.FallbackLeaf;
import org.dreambot.task.timeout.TimeoutLeaf;

@ScriptManifest(category = Category.MISC, name = "FOSS AIO", author = "camalCase", version = 0.0)
public class Main extends AbstractScript {
    private Config config = Config.getConfig();
    private final Tree<Main> tree = new Tree<>();

    @Override
    public void onStart(String... params) {
        instantiateTree();
    }

    @Override
    public void onStart() {
        instantiateTree();
    }

    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),

                new FallbackLeaf()
        );
    }

    @Override
    public int onLoop() {
        // todo add a timer to this
        if (config.getActivity().equals(Activities.NONE)) {
            setActivity();
            return 100;
        }
        return this.tree.onLoop();
    }

    private void setActivity() {
        // todo this will need some kind of logic as more activities are added
        config.setActivity(Activities.MINING);
    }
}
