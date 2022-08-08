package com.github.lkapitman;

import java.util.ArrayList;

import com.github.lkapitman.util.Utility;
import org.bukkit.configuration.file.FileConfiguration;

public class WLStorage {
    private App m;
    private ArrayList<String> whitelists = new ArrayList<>();
    private boolean WhitelistEnabled = false;
    private String nowhitelistmsg = "";

    public WLStorage(App m) {
        this.m = m;
        this.reload();
    }

    public void reload() {
        this.m.reloadConfig();
        FileConfiguration config = this.m.getConfig();
        this.whitelists = new ArrayList<>(config.getStringList("whitelisted"));
        this.WhitelistEnabled = config.getBoolean("whitelist");
        this.nowhitelistmsg = Utility.TransColor(config.getString("no_whitelisted"));
        Utility.sendConsole("&e&lEasyWhitelist > &7Config reloaded.");
    }

    public void saveWhitelists() {
        FileConfiguration config = this.m.getConfig();
        config.set("whitelisted", this.whitelists);
        config.set("whitelist", this.isWhitelisting());
        this.m.saveConfig();
    }

    public boolean isWhitelisted(String name) {
        return this.whitelists.contains(name.toLowerCase());
    }

    public void addWhitelist(String name) {
        if (!this.whitelists.contains(name.toLowerCase())) {
            this.whitelists.add(name.toLowerCase());
            this.saveWhitelists();
        }
    }

    public void removeWhitelist(String name) {
        if (this.whitelists.contains(name.toLowerCase())) {
            this.whitelists.remove(name.toLowerCase());
            this.saveWhitelists();
        }
    }

    public void setWhitelist(Boolean onoff) {
        this.WhitelistEnabled = onoff;
        this.saveWhitelists();
    }

    public ArrayList<String> getWhiteLists() {
        return this.whitelists;
    }

    public boolean isWhitelisting() {
        return this.WhitelistEnabled;
    }

    public String getNoWhitelistMsg() {
        return this.nowhitelistmsg;
    }
}
