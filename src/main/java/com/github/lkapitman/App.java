package com.github.lkapitman;

import com.github.lkapitman.commands.WLCmd;
import com.github.lkapitman.discord.InfoCommand;
import com.github.lkapitman.discord.WhitelistListener;
import com.github.lkapitman.events.WLEvent;
import com.github.lkapitman.util.Utility;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class App extends JavaPlugin {

    @Getter
    private static App instance;

    private final FileConfiguration config = getConfig();

    @Getter
    private static WLStorage storage;

    @Override
    public void onEnable() {
        // Инициализируем instance
        instance = this;

        this.getCommand("easywhitelist").setExecutor(new WLCmd());
        this.getServer().getPluginManager().registerEvents(new WLEvent(), this);
        storage = new WLStorage(this);
        Utility.sendConsole("&eE-Whitelist > &7Loaded!");

        // Работа с конфигом
        config.options().copyDefaults(true);
        saveConfig();

        // Проверка на то, включён плагин или нет
        if (!config.getBoolean("enable"))
            onDisable();

        Thread myThread = new Thread(() -> {
            final DiscordClient client = DiscordClient.create(config.getString("token"));
            final GatewayDiscordClient gateway = client.login().block();

            new InfoCommand().init(gateway);
            new WhitelistListener().init(gateway);

        }, "bot");

        myThread.start();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                "\033[1mWhitelistBot disabled!"
        );
        // Делаем NULL все поля.
        instance = null;
        storage.saveWhitelists();
    }
}
