package commands;

import fr.vivicoubar.minesteam.Team;
import fr.vivicoubar.minesteam.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Tchat implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("tchat")) {

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

            String message = ChatColor.BLUE + "Chat d'équipe - " + ChatColor.WHITE + player.getName() + " :";
            for (String word : args) {
                message += " " + word;
            }
            team.tellToEveryone(message);

            return true;
        }
        return false;
    }

}



