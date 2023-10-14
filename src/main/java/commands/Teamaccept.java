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

public class Teamaccept implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamaccept")) {

            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamaccept <nom_de_l'équipe>");
                return true;
            }

            Player invitedPlayer = (Player) sender;
            Team team = TeamManager.getTeams().get(args[0]);

            if (team == null || !team.getInvitedList().contains(invitedPlayer)) {
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas été invité par cette équipe !");
                return true;
            }

            team.addPlayer(invitedPlayer);
            sender.sendMessage(ChatColor.GREEN + "Vous avez bien rejoint l'équipe " + ChatColor.YELLOW + team.getName() + ChatColor.GREEN + " !");
            return true;
        }
        return false;
    }

}



