package es.alejandro12120.galacticraft.utils.cooldown;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownTask extends BukkitRunnable {

    @Override
    public void run() {
        List<Player> playerList = getCooldownPlayers();
        for(Player player : playerList) {
            checkCooldown(player);
        }
    }

    private void checkCooldown(Player player) {
        if(player == null) return;

        long timeLeftMillis = Cooldown.getTimeLeftMillis(player);
        if(timeLeftMillis > 0) return;

        Cooldown.removeCooldown(player);
    }

    private List<Player> getCooldownPlayers() {
        List<Player> playerList = new ArrayList<>();

        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(!Cooldown.isInCooldown(player)) continue;

            playerList.add(player);
        }

        return playerList;
    }

}
