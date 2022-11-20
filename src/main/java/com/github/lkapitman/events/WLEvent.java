package com.github.lkapitman.events;

import com.github.lkapitman.App;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class WLEvent implements Listener {

    @EventHandler
    public void onConnect(PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (App.getStorage().isWhitelisting()) {
            if (!App.getStorage().isWhitelisted(p.getName())) {
                String msg = App.getStorage().getNoWhitelistMsg();
                e.disallow(Result.KICK_WHITELIST, App.getStorage().getNoWhitelistMsg());
            }
        }
    }
}

