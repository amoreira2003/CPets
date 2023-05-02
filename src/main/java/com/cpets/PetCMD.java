package com.cpets;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.yaml.snakeyaml.Yaml;

import java.util.List;

public class PetCMD implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args
    ){
        if(!cmd.getName().equalsIgnoreCase("pet")) return false;
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        main.main.openPetInventory(p);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
