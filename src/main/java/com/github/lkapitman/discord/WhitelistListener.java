package com.github.lkapitman.discord;

import com.github.lkapitman.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.bukkit.Bukkit;

public class WhitelistListener {

    public void init(GatewayDiscordClient gateway) {
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (message.getChannel().block().getId().asString().equalsIgnoreCase(App.getInstance().getConfig().getString("channelID")) && !message.getAuthor().get().isBot()) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "easywl add " + message.getContent());
                Bukkit.getConsoleSender().sendMessage(
                        "\033[1mUser was added!"
                );
            }
        });

        gateway.onDisconnect().block();
    }
}
