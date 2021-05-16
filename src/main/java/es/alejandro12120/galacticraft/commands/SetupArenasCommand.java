package es.alejandro12120.galacticraft.commands;

import es.alejandro12120.galacticraft.Galacticraft;
import es.alejandro12120.galacticraft.utils.SetupArena;
import es.alejandro12120.galacticraft.utils.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupArenasCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("setuparenas")) {
            if(!sender.hasPermission("galacticraft.setuparenas")) {
                sender.sendMessage(Utilities.color("&cNo perms!"));
                return true;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(Utilities.color("&cNo console fucking retard."));
                return true;
            }

            Player player = (Player) sender;
            SetupArena.setupArenas(player);
            sender.sendMessage(Utilities.color("&eSuccessfully setup the arenas."));
            return true;
        }
        return true;
    }

}
