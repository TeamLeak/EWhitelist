package com.github.leanfe.discord.rating;

import com.github.leanfe.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.ReactiveEventAdapter;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class RatingCommand {

    public void init(GatewayDiscordClient client) {
        var ratingCommand = ApplicationCommandRequest.builder()
                .name("rating")
                .description("Print help message for rating!")
                .build();

        client.on(new ReactiveEventAdapter() {

            @Override
            public @NotNull Publisher<?> onChatInputInteraction(@NotNull ChatInputInteractionEvent event) {
                if (!event.getCommandName().equals("rating")) return Mono.empty();
                String result = result();
                return event.reply(result);
            }
        }).blockLast();
    }

    private static String result() {
        return App.getInstance().getConfig().getString("rating_description") +
                "\n here are the available commands: " +
                "\n1) /ratingAdd" +
                "\n2) /ratingRemove" +
                "\n3) /ratingList";
    }

}
