package me.thealmightyshibe.hat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("yea no these commands are for players only L");
            return true;
        }
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("nick")) {
            if (p.isOp() || p == Bukkit.getPlayer(UUID.fromString("767dd697-6fbd-4c14-8d47-cdda803374c5"))) {
                if (args.length >= 1) {
                    p.setPlayerListName(args[0]);
                    p.sendMessage("Player nick set to " + args[0]);
                }

            } else {
                p.sendMessage("a-NO-gus");
            }

        }
        if(cmd.getName().equalsIgnoreCase("unnick")) {
            p.setPlayerListName(p.getName());
            p.sendMessage("Player unnicked!");
        }

        if(cmd.getName().equalsIgnoreCase("hatinfo")) {


            if (args.length >= 1) {
                try {
                    int i = Integer.valueOf(args[0]);
                    if (i == 1) {
                        p.sendMessage("h");
                    } else if (i == 2) {
                        p.sendMessage("a");
                    } else if (i == 3) {
                        p.sendMessage("t");
                    } else if (i >= 4) {
                        p.sendMessage("ok its hat lol");
                    }
                } catch (IllegalArgumentException e) {
                    p.sendMessage("Please type in a number.");
                }
            } else {
                p.sendMessage("<Hat>: You can wear blocks and items.  This plugin should be self-explanatory. Right click the item to put it on your head if you have the golden HAT lore on it.  You can do this by crafting a hatstrap which requires a saddle and leather helmet.  Just click the item with the hatstrap and it will get the golden HAT lore!");
            }

        }

        return true;
    }
}
