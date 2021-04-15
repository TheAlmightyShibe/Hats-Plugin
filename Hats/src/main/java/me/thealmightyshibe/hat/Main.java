package me.thealmightyshibe.hat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("Hat Plugin is enabled!");
        ItemManager.init();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("nick").setExecutor(new Commands());
        getCommand("unnick").setExecutor(new Commands());
        getCommand("hatinfo").setExecutor(new Commands());
        //getCommand("hat").setExecutor(new Commands());

        ItemStack hatstrap = ItemManager.headstrap;
        NamespacedKey key = new NamespacedKey(this, "hat_strap");
        ShapelessRecipe strap = new ShapelessRecipe(key, hatstrap);
        strap.addIngredient(Material.LEATHER_HELMET);
        strap.addIngredient(Material.SADDLE);
        getServer().addRecipe(strap);
    }

    @Override
    public void onDisable() {
        System.out.println("Hat Plugin is disabled.");
    }
}
