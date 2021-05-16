package es.alejandro12120.galacticraft.commands;

import es.alejandro12120.galacticraft.Galacticraft;
import es.alejandro12120.galacticraft.utils.cooldown.Cooldown;
import es.alejandro12120.galacticraft.utils.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static es.alejandro12120.galacticraft.utils.Utilities.getDecimalTimeLeft;

public class StreamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Utilities.color("&cNo console."));
            return true;
        }
        if(!sender.hasPermission("galacticraft.directo")) {
            sender.sendMessage(Utilities.color("&cNo perms!"));
            return true;
        }

        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(Utilities.color(Galacticraft.getInstance().getConfig().getString("USAGE-DIRECTO")));
            return true;
        }
        if(args.length == 1) {
            if(Cooldown.isInCooldown(p)) {
                sendCooldownMessage(p);
                return true;
            }

            String url = args[0];
            if(!url.contains("https://") && !url.contains("http://")) {
                p.sendMessage(Utilities.color(Galacticraft.getInstance().getConfig().getString("NOT-A-LINK")));
                return true;
            }

            if(url.contains("twitch") || url.contains("youtube") || url.contains("mixer") || url.contains("dailymotion")) {
                for(String message : Galacticraft.getInstance().getConfig().getStringList("DIRECTO-MESSAGE")) {
                    Utilities.broadcast(message.replaceAll("<name>", p.getName()).replaceAll("<url>", url));
                }
                Cooldown.addCooldown(p);
            }else{
                p.sendMessage(Utilities.color("&cPillín, que te he pillao"));
            }
            return true;
        }
        return true;
    }

    private void sendCooldownMessage(Player player) {
        if (player == null) {
            return;
        }

        long millisLeft = Cooldown.getTimeLeftMillis(player);

        String timeLeftDecimal = getDecimalTimeLeft(millisLeft);
        String message = Utilities.color(Galacticraft.getInstance().getConfig().getString("COOLDOWN-MESSAGE").replaceAll("<cooldown>", timeLeftDecimal));

        player.sendMessage(message);
    }

}
