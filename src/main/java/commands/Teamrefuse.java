package commands;

import fr.vivicoubar.minesteam.Team;
import fr.vivicoubar.minesteam.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teamrefuse implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamrefuse")) {
            return true;
        }
        return false;
    }

}



