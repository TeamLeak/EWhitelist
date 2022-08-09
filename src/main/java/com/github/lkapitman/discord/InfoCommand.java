package com.github.lkapitman.discord;

import com.github.lkapitman.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.ReactiveEventAdapter;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class InfoCommand {

    public void init(GatewayDiscordClient client) {
        ApplicationCommandRequest randomCommand = ApplicationCommandRequest.builder()
                .name("info")
                .description("Return info about server")
                .build();

        client.on(new ReactiveEventAdapter() {

            @Override
            public @NotNull Publisher<?> onChatInputInteraction(@NotNull ChatInputInteractionEvent event) {
                if (event.getCommandName().equals("info")) {
                    String result = result();
                    return event.reply(result);
                }
                return Mono.empty();
            }
        }).blockLast();
    }

    private static String result() {
        return "===== " + App.getInstance().getConfig().getString("servername") + " ===== \n"
                + "IP ADDRESS: " + App.getInstance().getConfig().getString("ip") + " \n"
                + "VERSION: " + App.getInstance().getConfig().getString("version");
    }
}
