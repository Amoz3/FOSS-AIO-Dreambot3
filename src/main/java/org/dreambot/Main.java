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
import org.dreambot.task.timeout.TimeoutLeaf;

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
