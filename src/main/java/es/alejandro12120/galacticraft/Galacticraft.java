package es.alejandro12120.galacticraft;

import es.alejandro12120.galacticraft.commands.StreamCommand;
import es.alejandro12120.galacticraft.commands.SetupArenasCommand;
import es.alejandro12120.galacticraft.utils.cooldown.CooldownTask;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Galacticraft extends JavaPlugin implements Listener {

    @Getter
    private static Galacticraft instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        getCommand("directo").setExecutor(new StreamCommand());
        getCommand("setuparenas").setExecutor(new SetupArenasCommand());

        Bukkit.getPluginManager().registerEvents(this, this);

        CooldownTask task = new CooldownTask();
        task.runTaskTimerAsynchronously(this, 0L, 1L);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }

}
