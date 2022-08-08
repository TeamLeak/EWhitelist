package com.github.lkapitman;

import com.github.lkapitman.commands.WLCmd;
import com.github.lkapitman.events.WLEvent;
import com.github.lkapitman.util.Utility;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class App extends JavaPlugin {

    @Getter
    private static App instance;

    private final FileConfiguration config = getConfig();

    @Getter
    private static WLStorage storage;

    @Override
    public void onEnable() {
        // �������������� instance
        instance = this;

        this.getCommand("easywhitelist").setExecutor(new WLCmd());
        this.getServer().getPluginManager().registerEvents(new WLEvent(), this);
        storage = new WLStorage(this);
        Utility.sendConsole("&eE-Whitelist > &7Loaded!");

        // ������ � ��������
        config.options().copyDefaults(true);
        saveConfig();

        // �������� �� ��, ������� ������ ��� ���
        if (!config.getBoolean("enable"))
            onDisable();

        Thread myThread = new Thread(() -> {
            final DiscordClient client = DiscordClient.create(config.getString("token"));
            final GatewayDiscordClient gateway = client.login().block();


            gateway.on(MessageCreateEvent.class).subscribe(event -> {
                final Message message = event.getMessage();
                if (message.getChannel().block().getId().asString().equalsIgnoreCase(config.getString("channelID")) && !message.getAuthor().get().isBot()) {
                    storage.setWhitelist(Boolean.FALSE);
                    storage.addWhitelist(message.getContent());
                    storage.setWhitelist(Boolean.TRUE);
                    storage.reload();
                    Bukkit.getConsoleSender().sendMessage(
                            "\033[1mUser was added!"
                    );
                }
            });

            gateway.onDisconnect().block();
        }, "bot");

        myThread.start();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                "\033[1mWhitelistBot disabled!"
        );
        // ������ NULL ��� ����.
        instance = null;
        storage.saveWhitelists();
    }
}
