package com.cpets;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public enum Pets {

    KING_ENDERMAN,KING_DOGGO,BIG_BERRY,BAG_OF_SEEDS,KING_PEPE;


    void spawnPet(Player p,Pets petType) {
        if(main.main.petfollowMap.containsKey(p)) {
            PetFollow follow = main.main.petfollowMap.get(p);
            follow.changePet(petType);
            return;
        }
        YamlConfiguration yamlConfiguration = new Config().getConfigYAML();
        ArmorStand pet = main.main.spawnArmorStand(p,petType);
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
        PetFollow petFollow = new PetFollow(pet,petType,runnable);
        main.main.addPetFollow(p,petFollow);
    }
}
