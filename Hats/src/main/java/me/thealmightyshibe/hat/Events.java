package me.thealmightyshibe.hat;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Events implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerUseItem(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (event.getItem().getItemMeta().hasLore()) {
                //p.sendMessage("Line 0: " + event.getItem().getItemMeta().getLore().get(0));
                //p.sendMessage("Name: §6§lHAT");
                if (event.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§6§lHAT")) {
                    event.setCancelled(true);
                    //p.sendMessage("names equal");
                    ItemStack mainhand = event.getItem();
                    ItemMeta meta = event.getItem().getItemMeta();
                    Material type = mainhand.getType();
                    int amount = mainhand.getAmount();
                    ItemStack helmet = null;
                    if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() != Material.AIR) {
                        helmet = p.getInventory().getHelmet();
                    }
                    if (event.getHand() != null) {
                        //p.sendMessage("set item in hand");
                        if (amount-1>0) {
                            ItemStack handless = new ItemStack(type, amount-1);
                            handless.setItemMeta(meta);
                            p.getInventory().setItem(event.getHand(), handless);
                        } else {
                            p.getInventory().setItem(event.getHand(), null);
                        }

                    }
                    ItemStack hand = new ItemStack(type, 1);
                    hand.setItemMeta(meta);
                    p.getInventory().setHelmet(hand);
                    if (helmet != null) {
                        Location location = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()+1, p.getLocation().getZ());
                        p.getWorld().dropItem(location, helmet);
                    }



                }
                
            }

        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onInventoryClickEvent(InventoryClickEvent e) {
        InventoryType type = e.getInventory().getType();
        ItemStack air = new ItemStack(Material.AIR, 1);
        ItemStack cursor = e.getCursor();
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if (cursor != null && cursor.getType() != Material.AIR && cursor.getItemMeta().hasLore()) {
            //p.sendMessage("cursor has lore");
            if (cursor.getItemMeta().getLore().equals(ItemManager.headstrap.getItemMeta().getLore())) {
                //p.sendMessage("cursor is hatstrap");
                if (item.getType() != Material.AIR && item != null) {
                    //p.sendMessage("item not empty");


                    ItemMeta meta = item.getItemMeta();
                    if (meta.hasLore()) {
                        List<String> lore = meta.getLore();
                        lore.clear();
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        //p.sendMessage("Lore cleared!");
                    } else {
                        List<String> lore = new ArrayList<>();
                        lore.add("§6§lHAT");

                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        //p.sendMessage("Lore added!");
                    }

                    p.setItemOnCursor(cursor);
                    e.setCancelled(true);
                }


            }
        }


    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getHelmet() != null) {
            if (p.getInventory().getHelmet().getType().equals(Material.LAVA_BUCKET)) {
                p.getWorld().spawnParticle(Particle.FALLING_LAVA, p.getLocation().getX(), p.getLocation().getY()+2.25, p.getLocation().getZ(), 1);
            } else if (p.getInventory().getHelmet().getType().equals(Material.WATER_BUCKET) || p.getInventory().getHelmet().getType().equals(Material.PUFFERFISH_BUCKET) || p.getInventory().getHelmet().getType().equals(Material.SALMON_BUCKET) || p.getInventory().getHelmet().getType().equals(Material.COD_BUCKET) || p.getInventory().getHelmet().getType().equals(Material.TROPICAL_FISH_BUCKET)) {
                p.getWorld().spawnParticle(Particle.WATER_DROP, p.getLocation().getX(), p.getLocation().getY()+2.5, p.getLocation().getZ(), 1);
            } else if (p.getInventory().getHelmet().getType().equals(Material.MILK_BUCKET)) {
                p.getWorld().spawnParticle(Particle.SNOWBALL, p.getLocation().getX(), p.getLocation().getY()+2.5, p.getLocation().getZ(), 1);
            }
            else if (p.getInventory().getHelmet().getType().equals(Material.END_ROD)) {
                float yaw = p.getLocation().getYaw();
                int zoffset = 0;
                int xoffset = 0;
                p.sendMessage("Your yaw is: " + yaw);
                if (yaw >= -45 && yaw < 45) {
                    zoffset = 1;
                }
                if (yaw >= 135 && yaw < -135) {
                    zoffset = -1;
                }
                if (yaw >= 45 && yaw < 135) {
                    xoffset = -1;
                }
                if (yaw >= -135 && yaw < -45) {
                    xoffset = 1;
                }
                p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation().getX()+xoffset, p.getLocation().getY()+2, p.getLocation().getZ()+zoffset, 1);
            }
        }
    }


}
