package org.dreambot.data.mining;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum MineLocations {
    LUMMY_SWAMP_EAST(new Area(
            new Tile( 3226, 3151 ),
            new Tile ( 3233, 3149 ),
            new Tile ( 3232, 3142 ),
            new Tile ( 3224, 3143 ),
            new Tile ( 3219, 3148 ),
            new Tile ( 3221, 3152 )
    ));

    public final Area LOCATION;

    MineLocations(org.dreambot.api.methods.map.Area LOCATION) {
        this.LOCATION = LOCATION;
    }
}
