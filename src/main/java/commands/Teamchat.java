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

public class Teamchat implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamchat")) {

            Player player = (Player) sender;
            Team team = TeamManager.getTeamByPlayer(player);

            if (team == null) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                return true;
            }
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Message vide.");
                return true;
            }

            String message = ChatColor.YELLOW + "CHAT D'ÉQUIPE - " + ChatColor.DARK_GRAY + player.getName() + ":";
            for (String word : args) {
                message += " " + word;
            }
            team.tellToEveryone(message);

            return true;
        }
        return false;
    }

}



