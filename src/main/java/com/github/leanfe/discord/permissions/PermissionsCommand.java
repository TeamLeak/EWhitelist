package com.github.leanfe.discord.permissions;

import com.github.leanfe.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.ReactiveEventAdapter;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class PermissionsCommand {

    public void init(GatewayDiscordClient client) {
        var permissionCommand = ApplicationCommandRequest.builder()
                .name("permission")
                .description("Add permission to player")
                .build();

        client.on(new ReactiveEventAdapter() {

            @Override
            public @NotNull Publisher<?> onChatInputInteraction(@NotNull ChatInputInteractionEvent event) {
                if (event.getCommandName().equals("permission")) return Mono.empty();
                if (event.getOptions().isEmpty()) return event.reply(resultToString());

                String username = event.getOptions().get(0).getValue().get().asString();

                //LP user <ник_игрока> permission set VIP true/false
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + username + " permission set " + App.getInstance().getConfig().getString("permission_for_give") + " true");

                return event.reply("✓ Added!");

            }
        }).blockLast();
    }

    private static String resultToString() {
        return App.getInstance().getConfig().getString("permissions_description");
    }

}
