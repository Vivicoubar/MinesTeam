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

public class Teamsetleader implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamsetleader")) {
            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamsetleader <nom_du_joueur>");
                return true;
            }

            Player leader = (Player) sender;
            Team team = TeamManager.getTeamByPlayer(leader);
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (team == null || team.getLeader() != leader) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef d'une équipe !");
                return true;
            }
            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'est pas en ligne.");
                return true;
            }

            team.changeLeader(leader, targetPlayer);
            sender.sendMessage(ChatColor.GREEN + "Le joueur " + ChatColor.YELLOW + args[0] + ChatColor.GREEN + " est désormais chef de l'équipe " + ChatColor.YELLOW + team.getName() + ChatColor.GREEN + " !" +
                    ChatColor.BLUE + "\nVous n'êtes plus qu'un sous-chef de cette dernière.");
            return true;
        }
        return false;
    }

}



