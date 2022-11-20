package com.github.leanfe;

import com.github.leanfe.discord.info.InfoCommand;
import com.github.leanfe.discord.permissions.PermissionsCommand;
import com.github.leanfe.discord.rating.RatingAddCommand;
import com.github.leanfe.discord.rating.RatingCommand;
import com.github.leanfe.discord.rating.RatingListCommand;
import com.github.leanfe.discord.rating.RatingRemoveCommand;
import com.github.leanfe.json.UserManager;
import com.github.leanfe.json.UserParser;
import discord4j.core.DiscordClient;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class App extends JavaPlugin {

    @Getter
    private static App instance;

    private final FileConfiguration config = getConfig();

    @Getter
    private final UserManager userManager = new UserManager();

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage("&eNJUTILS > &7Loaded!");

        config.options().copyDefaults(true);
        saveConfig();

        if (!config.getBoolean("enable"))
            onDisable();

        String token = config.getString("token");

        if (token == null || token.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage("&eTOKEN = NULL! \n EXITING..");
            onDisable();
        }

        boolean usePermissions = config.getBoolean("permissions");
        boolean useRating = config.getBoolean("rating");

        if (!usePermissions && !useRating) {
            Bukkit.getConsoleSender().sendMessage("&eYOU DONT WANT USE ANY FUNCTION! \n EXITING..");
            onDisable();
        }

        new Thread(() -> {
            final DiscordClient client = DiscordClient.create(token);
            final var gateway = client.login().block();

            if (gateway == null) {
                Bukkit.getConsoleSender().sendMessage("&e OH SHIT! \n CANT CONNECT TO DISCORD!");
                this.onDisable();
            }

            new InfoCommand().init(gateway);

            if (usePermissions) new PermissionsCommand().init(gateway);
            if (useRating)  {
                new RatingCommand().init(gateway);
                new RatingAddCommand().init(gateway);
                new RatingRemoveCommand().init(gateway);
                new RatingListCommand().init(gateway);
            }

            }, "bot").start();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                "\033[1mNJUTILS disabled!"
        );

        instance = null;
        super.onDisable();
    }
}
