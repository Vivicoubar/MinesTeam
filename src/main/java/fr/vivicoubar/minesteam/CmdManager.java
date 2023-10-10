package fr.vivicoubar.minesteam;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdManager {

    public static boolean teamcreate(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) { // Pas assez d'arguments rentrés
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamcreate <nom_de_l'équipe>");
            return true;
        }
        String teamName = args[0];
        Player leader = (Player) sender;
        if (MinesTeam.hasTeam(leader)) {
            leader.sendMessage(ChatColor.RED + "Vous avez déjà une équipe ! Quittez la pour créer la votre.");
            return true;
        }

        if (MinesTeam.playerTeams.get(teamName) != null) {
            leader.sendMessage(ChatColor.RED + "Une team avec ce nom existe déjà ! Changez de nom.");
            return true;
        }

        Team team = new Team(teamName, leader);
        MinesTeam.playerTeams.put(teamName, team);
        team.addSecond(leader, leader); // Le chef est aussi un sous-chef
        sender.sendMessage(ChatColor.GREEN + "L'équipe " + teamName + " a été créée avec succès. Le leader est " + leader.getName() + ".");
        return true;
    }

    public static boolean teamdelete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) { // Pas assez d'arguments rentrés
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamdelete <nom_de_l'équipe>");
            return true;
        }
        Player leader = (Player) sender;
        Team team = MinesTeam.playerTeams.get(args[0]);
        Player player = Bukkit.getPlayer(args[1]);

        if (team.getLeader() != leader) {
            sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
            return true;
        }
        MinesTeam.playerTeams.remove(team.getName());
        sender.sendMessage(ChatColor.BLUE + "L'équipe a bien été supprimée ! Tous les membres sont désormais sans équipe.");
        return true;
    }

    public static boolean teamaddsecond(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) { // Pas assez d'arguments rentrés
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamaddsecond <nom_de_l'équipe> <nom_du_joueur>");
            return true;
        }
        Player leader = (Player) sender;
        Team team = MinesTeam.playerTeams.get(args[0]);
        Player player = Bukkit.getPlayer(args[1]);

        if (team.getLeader() != leader) {
            sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
            return true;
        }
        team.addSecond(leader, player);
        return true;
    }

    public static boolean teamremovesecond(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) { // Pas assez d'arguments rentrés
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamremovesecond <nom_de_l'équipe> <nom_du_joueur>");
            return true;
        }
        Player leader = (Player) sender;
        Team team = MinesTeam.playerTeams.get(args[0]);
        Player player = Bukkit.getPlayer(args[1]);

        if (team.getLeader() != leader) {
            sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
            return true;
        }
        team.removeSecond(leader, player);
        return true;
    }

    public static boolean teaminvite(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 2) { // Pas assez d'arguments rentrés
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teaminvite <nom_de_l'équipe> <pseudo_du_joueur_à_inviter>");
            return true;
        }

        String teamName = args[0];
        Team team = MinesTeam.playerTeams.get(teamName);
        Player targetPlayer = Bukkit.getPlayer(args[1]);
        Player senderPlayer = (Player) sender;

        if (!team.getSecondList().contains(senderPlayer)) {
            sender.sendMessage(ChatColor.RED + "Vous n'êtes pas au moins sous-chef de cette équipe, vous ne pouvez pas inviter quelqu'un !");
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
        return true;
    }

}
