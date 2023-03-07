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
        YamlConfiguration yamlConfiguration = new Config().getConfigYAML();
        Player p = (Player) sender;
        ArmorStand pet = main.main.spawnArmorStand(p);
        BukkitTask runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location newLocation = pet.getLocation();
                Vector toPlayerDirection = p.getLocation().toVector().subtract(pet.getLocation().toVector());
                newLocation.setDirection(toPlayerDirection);
                if (pet.getLocation().toVector().distance(p.getLocation().toVector()) > yamlConfiguration.getDouble("animation.followDistance")) {
                    Vector followVector = toPlayerDirection.normalize().multiply(yamlConfiguration.getDouble("animation.followSpeed"));
                    followVector.setY(0);
                    newLocation.add(followVector);
                    newLocation.setY(p.getLocation().getY()+1.1);
                }
                pet.teleport(newLocation);
            }
        }.runTaskTimer(main.main,0,1);
        PetFollow petFollow = new PetFollow(pet,runnable);
        main.main.addPetFollow(p,petFollow);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
