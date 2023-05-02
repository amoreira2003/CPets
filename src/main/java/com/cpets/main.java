package com.cpets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

public final class main extends JavaPlugin implements Listener {

    public static main main;

    HashMap<Player,PetFollow> petfollowMap = new HashMap<>();

    @Override
    public void onEnable() {
        main = this;
        Bukkit.getLogger().info("cPets Plugin Enabled");
        Bukkit.getPluginManager().registerEvents(this,this);
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


    public void openPetInventory(Player player) {
        Inventory petInventory = Bukkit.createInventory(player,9*5,player.getName()+"'s Pets");
        for(Pets pet : Pets.values()) {
            ItemStack skull = null;
            SkullMeta skullMeta = null;
            NamespacedKey holdType = new NamespacedKey(main,"CPetsPetType");
            switch (pet) {
                case BIG_BERRY:
                    skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA0MzA3NGUyMDllYzQ1Mzk0MWMzZTQ2MmIwYmQ1MTYwMmQ4OTg0Zjg2MDcwYWU1ZmIwY2FjMGE5NTg5MmQ5MCJ9fX0=");
                    skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.GRAY + "Big Berry");
                    break;
                case KING_PEPE:
                    skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZjNDMxYzMxNGM5OWE3MWI4M2I2NzczODlkMDFhZWQ3OThkYTQ4MzY2OTIwYmM5YjM3OTY4ZDg0ZGFlMDYwZiJ9fX0=");
                    skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.GRAY + "King Pepe");
                    break;
                case KING_DOGGO:
                    skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQyYjM5YzY3NGYyZTk3ODQ5ODc3ZjU2M2I3N2MxZTUyZDhlZDE0MDY0ZGJjM2Y2NTkxZDA5NTU1NWU5ZWY2MSJ9fX0=");
                    skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.GRAY + "King Doggo") ;
                    break;
                case BAG_OF_SEEDS:
                    skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhZGIzN2RkNTU2ZjllZWZiYjhlYTU0OTQzZTFmNjU3ZmFjNDU0MDllMzRjZDk5YzgxMGQ0ZGQ0NjFiYzYzMyJ9fX0=");
                    skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.GRAY + "Bag Of Seeds");
                    break;
                case KING_ENDERMAN:
                    skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJjOGM2ZGIyNzg4NzE2N2VlNjU5OWU2NTg3NGRiZDVmZTc1Mzk0YTA0ODJkZGI5NTBjNTA0YjkwN2M5NWVlOCJ9fX0=");
                    skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setDisplayName(ChatColor.GRAY + "King Enderman");
                    break;
            }
            skullMeta.getPersistentDataContainer().set(holdType, PersistentDataType.STRING,pet.name());
            skull.setItemMeta(skullMeta);
            petInventory.addItem(skull);
        }
        player.openInventory(petInventory);
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();
        InventoryView clickedView = e.getView();
        if(!clickedView.getTitle().equalsIgnoreCase(p.getName()+"'s Pets")) return;
        e.setCancelled(true);
        ItemStack cl = e.getCurrentItem();
        if(cl.getType().isAir() || cl == null) return;
        if(!cl.getType().equals(Material.PLAYER_HEAD)) return;
        ItemMeta clMeta = cl.getItemMeta();
        NamespacedKey holdType = new NamespacedKey(main,"CPetsPetType");
        if(!clMeta.getPersistentDataContainer().getKeys().contains(holdType)) return;
        Pets petType = Pets.valueOf(clMeta.getPersistentDataContainer().get(holdType, PersistentDataType.STRING));
        petType.spawnPet(p,petType);
        p.closeInventory();
    }


    @EventHandler
    public void onCropHarvest(PlayerHarvestBlockEvent e) {
        Player p = e.getPlayer();
        if(!main.petfollowMap.containsKey(p)) {e.setCancelled(true); return;}
        if(!main.petfollowMap.get(p).petType.equals(Pets.BAG_OF_SEEDS)) {e.setCancelled(true);}
    }

    public ArmorStand spawnArmorStand(Player challenger,Pets petType) {
        Location armorStandLocation = challenger.getLocation();
        ArmorStand armorStand = challenger.getWorld().spawn(armorStandLocation, ArmorStand.class);
        ItemStack skull = null;
        switch (petType) {
            case BIG_BERRY:
                skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA0MzA3NGUyMDllYzQ1Mzk0MWMzZTQ2MmIwYmQ1MTYwMmQ4OTg0Zjg2MDcwYWU1ZmIwY2FjMGE5NTg5MmQ5MCJ9fX0=");
                armorStand.setCustomName(ChatColor.GRAY + "Big Berry");
                break;
            case KING_PEPE:
                skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZjNDMxYzMxNGM5OWE3MWI4M2I2NzczODlkMDFhZWQ3OThkYTQ4MzY2OTIwYmM5YjM3OTY4ZDg0ZGFlMDYwZiJ9fX0=");
                armorStand.setCustomName(ChatColor.GRAY + "King Pepe");
                break;
            case KING_DOGGO:
                skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQyYjM5YzY3NGYyZTk3ODQ5ODc3ZjU2M2I3N2MxZTUyZDhlZDE0MDY0ZGJjM2Y2NTkxZDA5NTU1NWU5ZWY2MSJ9fX0=");
                armorStand.setCustomName(ChatColor.GRAY + "King Doggo") ;
                break;
            case BAG_OF_SEEDS:
                skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhZGIzN2RkNTU2ZjllZWZiYjhlYTU0OTQzZTFmNjU3ZmFjNDU0MDllMzRjZDk5YzgxMGQ0ZGQ0NjFiYzYzMyJ9fX0=");
                armorStand.setCustomName(ChatColor.GRAY + "Bag Of Seeds");
                break;
            case KING_ENDERMAN:
                skull = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJjOGM2ZGIyNzg4NzE2N2VlNjU5OWU2NTg3NGRiZDVmZTc1Mzk0YTA0ODJkZGI5NTBjNTA0YjkwN2M5NWVlOCJ9fX0=");
                armorStand.setCustomName(ChatColor.GRAY + "King Enderman");
                break;
        }
        armorStand.setSmall(true);
        armorStand.getEquipment().setHelmet(skull);
        armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        return armorStand;
    }
}
