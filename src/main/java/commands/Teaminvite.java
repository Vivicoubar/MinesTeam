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

public class Teaminvite implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command,@NotNull String label,@NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("teaminvite")) {

            if (args.length != 1) { // Nombre d'arguments rentrés incorrect
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teaminvite <pseudo_du_joueur_à_inviter>");
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            Player senderPlayer = (Player) sender;
            Team team = TeamManager.getTeamByPlayer(senderPlayer);

            if (team == null || !team.getSecondList().contains(senderPlayer)) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas au moins sous-chef d'une équipe, vous ne pouvez pas inviter quelqu'un !");
                return true;
            }
            if (team.getPlayerList().contains(targetPlayer)) {
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié est déjà dans l'équipe.");
                return true;
            }
            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'est pas en ligne.");
                return true;
            }

            if (!team.getInvitedList().contains(targetPlayer)) {team.getInvitedList().add(targetPlayer);}
            // Comme ça on peut a priori inviter plusieurs fois un même joueur, sans l'ajouter plusieurs fois à la liste des invitations
            targetPlayer.sendMessage(ChatColor.BLUE + "Vous avez été invité à rejoindre l'équipe " + ChatColor.YELLOW + team.getName() + ChatColor.BLUE +  ". Faites :\n"
                    + ChatColor.YELLOW + "/teamaccept "+ team.getName() + ChatColor.BLUE + " pour accepter."
                    + ChatColor.YELLOW + "/teamrefuse "+ team.getName() + ChatColor.BLUE + " pour refuser.");
            sender.sendMessage(ChatColor.BLUE + "Invitation envoyée.");
            return true;
        }
        return false;
    }

}



