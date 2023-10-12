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

public class Teamremovesecond implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamremovesecond")) {
            if (args.length != 2) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamremovesecond <nom_du_joueur>");
                return true;
            }
            Player leader = (Player) sender;
            Team team = TeamManager.getTeamByPlayer(leader);
            Player player = Bukkit.getPlayer(args[0]);

            if (team == null || team.getLeader() != leader) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas un chef d'équipe !");
                return true;
            }

            team.removeSecond(leader, player);
            sender.sendMessage(ChatColor.BLUE + "Joueur supprimé de la liste des sous-chefs.");
            return true;
        }
        return false;
    }

}



