package commands;

import fr.vivicoubar.minesteam.Team;
import fr.vivicoubar.minesteam.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teamcreate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teamcreate")) {
            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamcreate <nom_de_l'équipe>");
                return true;
            }

            String teamName = args[0];
            Player leader = (Player) sender;

            if (TeamManager.getTeamByPlayer(leader) != null) {
                leader.sendMessage(ChatColor.RED + "Vous avez déjà une équipe ! Quittez la avec " + ChatColor.BLUE + "/teamleave" + ChatColor.RED + " pour créer la votre.");
                return true;
            }
            if (TeamManager.getTeams().get(teamName) != null) {
                leader.sendMessage(ChatColor.RED + "Une team avec ce nom existe déjà ! Changez de nom.");
                return true;
            }
            int maxLength = 30; // just in case...
            if (teamName.length() > maxLength) {
                leader.sendMessage(ChatColor.RED + "Ce nom est trop long ! Changez de nom (maximum 30 caractères).");
                return true;
            }

            Team team = new Team(teamName, leader);
            TeamManager.getTeams().put(teamName, team);
            sender.sendMessage(ChatColor.GREEN + "L'équipe " + teamName + " a été créée avec succès. Le leader est " + leader.getName() + ".");
            team.getSecondList().add(leader); // Le chef est aussi un sous-chef. On utilise pas la fonction de Team car sinon un message est envoyé...
            return true;
        }
        return false;
    }

}



