package org.dreambot.data.mining;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum MineLocations {
    RIMMINGTON(new Area(
            new Tile(2970, 3248 ),
            new Tile(2981, 3250 ),
            new Tile(2990, 3242 ),
            new Tile(2988, 3233 ),
            new Tile(2978, 3229 ),
            new Tile(2965, 3238 )
    )),
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
