package es.alejandro12120.galacticraft.utils.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import es.alejandro12120.galacticraft.Galacticraft;
import es.alejandro12120.galacticraft.utils.Utilities;
import org.bukkit.entity.Player;

public class Cooldown {
    private static final Map<UUID, Long> cooldownExpireMap = new HashMap<>();

    public static boolean isInCooldown(Player player) {
        if(player == null) return false;

        UUID uuid = player.getUniqueId();
        return cooldownExpireMap.containsKey(uuid);
    }

    public static void addCooldown(Player player) {
        if(player == null) return;

        long secondsToAdd = Utilities.parseTime(Galacticraft.getInstance().getConfig().getString("DIRECTO-COOLDOWN"));
        long millisToAdd = TimeUnit.SECONDS.toMillis(secondsToAdd);

        long systemMillis = System.currentTimeMillis();
        long expireMillis = (systemMillis + millisToAdd);

        UUID uuid = player.getUniqueId();
        cooldownExpireMap.put(uuid, expireMillis);
    }

    public static void removeCooldown(Player player) {
        if(player == null) return;

        UUID uuid = player.getUniqueId();
        cooldownExpireMap.remove(uuid);
    }

    public static long getTimeLeftMillis(Player player) {
        if(!isInCooldown(player)) return 0L;

        UUID uuid = player.getUniqueId();
        long expireTime = cooldownExpireMap.getOrDefault(uuid, 0L);

        long systemTime = System.currentTimeMillis();
        return (expireTime - systemTime);
    }

    public static long getTimeLeftSeconds(Player player) {
        long timeLeftMillis = getTimeLeftMillis(player);
        return TimeUnit.MILLISECONDS.toSeconds(timeLeftMillis);
    }
}