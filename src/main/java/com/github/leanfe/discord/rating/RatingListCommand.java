package com.github.leanfe.discord.rating;

import com.github.leanfe.App;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.ReactiveEventAdapter;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

public class RatingListCommand {
    public void init(GatewayDiscordClient client) {
        var ratingCommand = ApplicationCommandRequest.builder()
                .name("ratingList")
                .description("List all ratings!")
                .build();

        client.on(new ReactiveEventAdapter() {

            @Override
            public @NotNull Publisher<?> onChatInputInteraction(@NotNull ChatInputInteractionEvent event) {
                if (!event.getCommandName().equals("ratingList")) return Mono.empty();

                return event.reply(result());
            }
        }).blockLast();
    }


    private static String result() {
        AtomicReference<String> result = new AtomicReference<>("Total List: ");
        App.getInstance().getUserManager().getUserData().getUsers().forEach(user -> result.set(result + "\n" + user.getUsername() + ": " + user.getExp()));

        return result.get();
    }
}
