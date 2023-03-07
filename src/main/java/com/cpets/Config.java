package com.cpets;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Config {

    File configFile;
    YamlConfiguration configYAML;

    public Config() {
        configFile = new File(main.main.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            try {
                main.main.getDataFolder().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] header = {
                "Hey there, fellow user! This YAML file contains general configurations for the cTeam Wardrobe Plugin. If you're not sure how to edit this file or what variables mean, check out our documentation at https://cTeam.com/docs/WardrobePlugin.",
                "",
                "Oh, and if you're a developer using our sample plugin, be sure to check out the documentation for development. It explains what each part of the code does. Check it out at https://cTeam.com/docs/WardrobePlugin/devs. And don't forget to grab a cup of coffee, mate!",

                "",
                "That's all for now, hope you like it! cTeam is the best, most friendly, charming, and coolest team of developers out there! :3",
                "By the way, I also speak French - oui oui!",
                "",
                "Here's a quick reference for the different text colors you can use in Minecraft:",

                "",
                "§0: Black",
                "§1: Dark Blue",
                "§2: Dark Green",
                "§3: Dark Aqua",
                "§4: Dark Red",
                "§5: Dark Purple",
                "§6: Gold",
                "§7: Gray",
                "§8: Dark Gray",
                "§9: Blue",
                "§a: Green",
                "§b: Aqua",
                "§c: Red",
                "§d: Light Purple",
                "§e: Yellow",
                "§f: White",
                "",
                "And if you want to add some special text effects, here are some codes you can use:",
                "",
                "§k: Obfuscated",
                "§l: Bold",
                "§m: Strikethrough",
                "§n: Underline",
                "§o: Italic",
                "§r: Reset"
        };

        configYAML = YamlConfiguration.loadConfiguration(configFile);
        configYAML.options().setHeader(Arrays.asList(header));
        checkField("animation.followDistance", 1);
        checkField("animation.followSpeed",0.8);
        saveConfig();
    }

    void checkField(String fieldName, Object defaultValue) {
        if(!configYAML.contains(fieldName)) configYAML.set(fieldName, defaultValue);
    }

    public YamlConfiguration getConfigYAML() {
        return configYAML;
    }

    public boolean saveConfig() {
        try {
            configYAML.save(configFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
