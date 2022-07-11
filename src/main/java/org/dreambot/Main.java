package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.framework.Tree;
import org.dreambot.task.fallback.FallbackLeaf;
import org.dreambot.task.timeout.TimeoutLeaf;

@ScriptManifest(category = Category.MISC, name = "FOSS AIO", author = "camalCase", version = 0.0)
public class Main extends AbstractScript {
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
        return this.tree.onLoop();
    }
}
