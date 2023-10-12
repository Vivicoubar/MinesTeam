package commands;

import fr.vivicoubar.minesteam.Team;
import fr.vivicoubar.minesteam.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teamdelete implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamdelete")) {
            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamdelete <nom_de_l'équipe>");
                return true;
            }
            Player leader = (Player) sender;
            Team team = TeamManager.getTeams().get(args[0]);

            if (team.getLeader() != leader) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
                return true;
            }
            TeamManager.getTeams().remove(team.getName());
            sender.sendMessage(ChatColor.BLUE + "L'équipe a bien été supprimée ! Tous les membres sont désormais sans équipe.");
            return true;
        }
        return false;
    }

}



