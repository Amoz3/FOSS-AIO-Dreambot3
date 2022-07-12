package org.dreambot;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.config.Config;
import org.dreambot.data.Activities;
import org.dreambot.framework.API;
import org.dreambot.framework.Tree;
import org.dreambot.paint.FluffeesPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.task.fallback.FallbackLeaf;
import org.dreambot.task.mining.F2PMiningBranch;
import org.dreambot.task.mining.f2pmining.F2PMiningLeaf;
import org.dreambot.task.timeout.TimeoutLeaf;
import org.dreambot.task.woodcutting.*;
import org.dreambot.task.woodcutting.InventoryLeafs.DepositAllExceptAxe;
import org.dreambot.task.woodcutting.InventoryLeafs.InventoryIsFullLeaf;
import org.dreambot.task.woodcutting.WillowLeafs.ChopWillow;
import org.dreambot.task.woodcutting.WillowLeafs.WalkToWillow;
import org.dreambot.task.woodcutting.getBestAxe.GrandExchangeFunction;
import org.dreambot.task.woodcutting.oakTreeLeafs.ChopOak;
import org.dreambot.task.woodcutting.oakTreeLeafs.WalkToOak;
import org.dreambot.task.woodcutting.regularTreeLeafs.ChopTree;
import org.dreambot.task.woodcutting.regularTreeLeafs.walkToArea;

import java.awt.*;

@ScriptManifest(category = Category.MISC, name = "FOSS AIO", author = "camalCase", version = 0.0)
public class Main extends AbstractScript implements PaintInfo {
    private Config config = Config.getConfig();
    private final Tree<Main> tree = new Tree<>();

    // PAINT
    Color[] txtcolors = new Color[]{Color.green};
    Color[] backingcolors = new Color[]{new Color(0, 0, 0, 220)};
    Color[] bordercolors = new Color[]{Color.darkGray};
    private final Timer runtime = new Timer();
    private final FluffeesPaint FLUFFEES_PAINT = new FluffeesPaint(this,
            FluffeesPaint.PaintLocations.TOP_LEFT_PLAY_SCREEN,
            txtcolors,
            "test",
            backingcolors,
            bordercolors,
            1,
            false,
            3,
            3, 3
    );

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
                new F2PMiningBranch().addLeafs(new F2PMiningLeaf()),
                new GrandExchangeBranch().addLeafs(new GrandExchangeFunction()),
                new InventoryBranch().addLeafs(new InventoryIsFullLeaf(), new DepositAllExceptAxe()),
                new NormalTreeCondition().addLeafs(new ChopTree(), new walkToArea()),
                new OakBranch().addLeafs(new ChopOak(), new WalkToOak()),
                new WillowBranch().addLeafs(new ChopWillow(), new WalkToWillow()),
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
        //Woodcutter by Dogcube
        config.setActivity(Activities.WOODCUTTING);
    }

    @Override
    public void onPaint(Graphics g) {
        FLUFFEES_PAINT.paint(g);
    }

    @Override
    public String[] getPaintInfo() {
        return new String[]{
                "FOSS AIO - "  + config.getActivity() + " - " + API.currentBranch + " - " + API.currentLeaf,
                "Runtime: " + runtime.formatTime()
        };
    }
}
