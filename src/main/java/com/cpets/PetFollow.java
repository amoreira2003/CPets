package com.cpets;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PetFollow {

    ArmorStand pet;
    BukkitTask runnable;

    public PetFollow(ArmorStand pet, BukkitTask runnable) {
        this.pet = pet;
        this.runnable = runnable;
    }

    public ArmorStand getPet() {
        return pet;
    }

    public BukkitTask getRunnable() {
        return runnable;
    }

}
