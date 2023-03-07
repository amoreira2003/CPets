package com.cpets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

public final class main extends JavaPlugin {

    public static main main;

    HashMap<Player,PetFollow> petfollowMap = new HashMap<>();

    @Override
    public void onEnable() {
        main = this;
        Bukkit.getLogger().info("cPets Plugin Enabled");
        PluginCommand petCommand = getCommand("pet");
        petCommand.setExecutor(new PetCMD());
        petCommand.setTabCompleter(new PetCMD());

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("cPets Plugin Disabled");
        for(PetFollow petFollow : petfollowMap.values()) {
            petFollow.pet.remove();
        }
    }

    @Override
    public void onLoad() {
        Bukkit.getLogger().info("cPets Plugin Loaded");
    }

    public static ItemStack getCustomSkull(String texture) {
        ItemStack customSkull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) customSkull.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(),null);
        gameProfile.getProperties().put("textures", new Property("texture",texture));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta,gameProfile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        customSkull.setItemMeta(skullMeta);
        return customSkull;
    }


    public boolean addPetFollow(Player player, PetFollow petFollow) {
       if(petfollowMap.containsKey(player)) return false;
        petfollowMap.put(player,petFollow);
        return true;
    }

    public boolean removePetFollow(Player player) {
        if(!petfollowMap.containsKey(player)) return false;
        petfollowMap.remove(player);
        return true;
    }



    public ArmorStand spawnArmorStand(Player challenger) {
        Location armorStandLocation = challenger.getLocation();
        ArmorStand armorStand = challenger.getWorld().spawn(armorStandLocation, ArmorStand.class);
        ItemStack skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJjOGM2ZGIyNzg4NzE2N2VlNjU5OWU2NTg3NGRiZDVmZTc1Mzk0YTA0ODJkZGI5NTBjNTA0YjkwN2M5NWVlOCJ9fX0=");
        armorStand.setSmall(true);
        armorStand.setCustomName(ChatColor.GRAY + "Enderman King [LVL.1]");
        armorStand.getEquipment().setHelmet(skull);
        armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        return armorStand;
    }
}
