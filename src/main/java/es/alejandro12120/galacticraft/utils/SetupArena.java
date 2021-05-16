package es.alejandro12120.galacticraft.utils;

import es.alejandro12120.galacticraft.Galacticraft;
import org.bukkit.entity.Player;

public class SetupArena {

    public static void setupArenas(Player player) {
        for(String buildName : Galacticraft.getInstance().getConfig().getStringList("BUILD-ARENAS")) {
            player.performCommand("arena setType "+buildName+" build");
        }

        for(String sumoName : Galacticraft.getInstance().getConfig().getStringList("SUMO-ARENAS")) {
            player.performCommand( "arena setType "+sumoName+" sumo");
        }
    }

}
