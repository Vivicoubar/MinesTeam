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

public class Teamleave implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamleave")) {

            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamleave <nom_de_l'équipe>");
                return true;
            }

            Player player = (Player) sender;
            Team team = TeamManager.getTeams().get(args[0]);

            if (team == null || !team.getPlayerList().contains(player)) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas dans cette équipe !");
                return true;
            }
            if(team.getLeader() == player) {
                sender.sendMessage(ChatColor.RED + "Vous êtes le chef de cette équipe, vous ne pouvez pas la quitter !" +
                        "\nSupprimez-la si vous souhaitez vraiment la quitter.");
                return true;
            }
            team.removePlayer(player);
            sender.sendMessage(ChatColor.BLUE + "Vous avez bien quitté l'équipe " + ChatColor.YELLOW + team.getName() + ChatColor.BLUE + " !");
            return true;
        }
        return false;
    }

}



