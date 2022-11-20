package com.github.leanfe.discord.rating;

import com.github.leanfe.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.ReactiveEventAdapter;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class RatingAddCommand {

    public void init(GatewayDiscordClient client) {
        var ratingCommand = ApplicationCommandRequest.builder()
                .name("ratingAdd")
                .description("Add user some rating!")
                .build();

        client.on(new ReactiveEventAdapter() {

            @Override
            public @NotNull Publisher<?> onChatInputInteraction(@NotNull ChatInputInteractionEvent event) {
                if (!event.getCommandName().equals("ratingAdd")) return Mono.empty();
                if (event.getOptions().isEmpty()) return Mono.empty();

                //TODO:
                return event.reply(result());
            }
        }).blockLast();
    }

    private static String result() {
        return "✓ Added!";
    }
}
