package me.thealmightyshibe.hat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack headstrap;

    public static void init() {
        createHeadstrap();
    }

    private static void createHeadstrap() {
        ItemStack item = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Hat Strap");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "§lHATSTRAP");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        headstrap = item;
    }
}
