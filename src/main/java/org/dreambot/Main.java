package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.framework.Tree;

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
        tree.addBranches();
    }

    @Override
    public int onLoop() {
        return this.tree.onLoop();
    }
}
