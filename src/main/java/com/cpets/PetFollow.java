package com.cpets;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static com.cpets.main.getCustomSkull;

public class PetFollow {

    ArmorStand pet;
    BukkitTask runnable;

    Pets petType;

    public Pets getPetType() {
        return petType;
    }


    void changePet(Pets petType) {
        this.petType = petType;
        switch (petType) {
            case BIG_BERRY:
                pet.getEquipment().setHelmet(getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA0MzA3NGUyMDllYzQ1Mzk0MWMzZTQ2MmIwYmQ1MTYwMmQ4OTg0Zjg2MDcwYWU1ZmIwY2FjMGE5NTg5MmQ5MCJ9fX0="));
                pet.setCustomName(ChatColor.GRAY + "Big Berry");
                break;
            case KING_PEPE:
                pet.getEquipment().setHelmet(getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZjNDMxYzMxNGM5OWE3MWI4M2I2NzczODlkMDFhZWQ3OThkYTQ4MzY2OTIwYmM5YjM3OTY4ZDg0ZGFlMDYwZiJ9fX0="));
                pet.setCustomName(ChatColor.GRAY + "King Pepe");
                break;
            case KING_DOGGO:
                pet.getEquipment().setHelmet(getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQyYjM5YzY3NGYyZTk3ODQ5ODc3ZjU2M2I3N2MxZTUyZDhlZDE0MDY0ZGJjM2Y2NTkxZDA5NTU1NWU5ZWY2MSJ9fX0="));
                pet.setCustomName(ChatColor.GRAY + "King Doggo");
                break;
            case BAG_OF_SEEDS:
                pet.getEquipment().setHelmet(getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhZGIzN2RkNTU2ZjllZWZiYjhlYTU0OTQzZTFmNjU3ZmFjNDU0MDllMzRjZDk5YzgxMGQ0ZGQ0NjFiYzYzMyJ9fX0="));
                pet.setCustomName(ChatColor.GRAY + "Bag Of Seeds");
                break;
            case KING_ENDERMAN:
                pet.getEquipment().setHelmet(getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJjOGM2ZGIyNzg4NzE2N2VlNjU5OWU2NTg3NGRiZDVmZTc1Mzk0YTA0ODJkZGI5NTBjNTA0YjkwN2M5NWVlOCJ9fX0="));
                pet.setCustomName(ChatColor.GRAY + "King Enderman");
                break;
        }
    }
    public void setPetType(Pets petType) {
        this.petType = petType;
    }

    public PetFollow(ArmorStand pet, Pets petType, BukkitTask runnable) {
        this.pet = pet;
        this.runnable = runnable;
        this.petType = petType;
    }

    public ArmorStand getPet() {
        return pet;
    }

    public BukkitTask getRunnable() {
        return runnable;
    }

}
