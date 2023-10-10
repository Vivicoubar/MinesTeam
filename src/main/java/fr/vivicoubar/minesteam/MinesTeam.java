package fr.vivicoubar.minesteam;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class MinesTeam extends JavaPlugin {

    private HashMap<String, Team> playerTeams = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("teamcreate").setExecutor(this);
        this.getCommand("inviteteam").setExecutor(this);
        this.getCommand("teamaddsecond").setExecutor(this);
        this.getCommand("teamremovesecond").setExecutor(this);
        //loadTeams();
    }

    @Override
    public void onDisable() {
        //saveTeams();
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("teamcreate")) {
            if (args.length != 1) { // Pas assez d'arguments rentrés
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamcreate <nom_de_l'équipe>");
                return true;
            }
            String teamName = args[0];
            Player leader = (Player) sender;
            if (this.hasTeam(leader)) {
                leader.sendMessage(ChatColor.RED + "Vous avez déjà une équipe ! Quittez la pour créer la votre.");
                return true;
            }

            if (playerTeams.get(teamName) != null){
                leader.sendMessage(ChatColor.RED + "Une team avec ce nom existe déjà ! Changez de nom.");
                return true;
            }

            Team team = new Team(teamName, leader);
            playerTeams.put(teamName, team);
            team.addSecond(leader, leader); // Le chef est aussi un sous-chef
            sender.sendMessage(ChatColor.GREEN + "L'équipe " + teamName + " a été créée avec succès. Le leader est " + leader.getName() + ".");
            return true;
        }
        if (command.getName().equalsIgnoreCase("teamaddsecond")) {
            if (args.length != 2) { // Pas assez d'arguments rentrés
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamaddsecond <nom_de_l'équipe> <nom_du_joueur>");
                return true;
            }
            Player leader = (Player) sender;
            Team team = playerTeams.get(args[0]);
            Player player = Bukkit.getPlayer(args[1]);

            if(team.getLeader() != leader) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
                return true;
            }
            team.addSecond(leader, player);
        }
        if (command.getName().equalsIgnoreCase("teamremovesecond")) {
            if (args.length != 2) { // Pas assez d'arguments rentrés
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teamremovesecond <nom_de_l'équipe> <nom_du_joueur>");
                return true;
            }
            Player leader = (Player) sender;
            Team team = playerTeams.get(args[0]);
            Player player = Bukkit.getPlayer(args[1]);

            if(team.getLeader() != leader) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de cette équipe !");
                return true;
            }
            team.removeSecond(leader, player);
        }
        if (command.getName().equalsIgnoreCase("teaminvite")) {
            if (args.length < 2) { // Pas assez d'arguments rentrés
                sender.sendMessage(ChatColor.RED + "Utilisation incorrecte. Utilisez /teaminvite <nom_de_l'équipe> <pseudo_du_joueur_à_inviter>");
                return true;
            }

            String teamName = args[0];
            Team team = playerTeams.get(teamName);
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            Player senderPlayer = (Player) sender;

            if (!team.getSecondList().contains(senderPlayer)) {
                sender.sendMessage(ChatColor.RED + "Vous n'êtes pas au moins sous-chef de cette équipe, vous ne pouvez pas inviter quelqu'un !");
                return true;
            }
            if (team.getPlayerList().contains(targetPlayer)){
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié est déjà dans l'équipe.");
                return true;
            }
            if (targetPlayer == null || !targetPlayer.isOnline()){
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'est pas en ligne.");
                return true;
            }


            //TODO : envoi d'une demande au joueur cible. Il doit accepter avec une commande (ou refuser)
            //TODO : possibilité de supprimer une équipe (avec demande d'une confirmation)
            //TODO : possibilité de quitter une équipe (avec demande d'une confirmation)

            return true;
        }

        return false;
    }

    public boolean hasTeam(Player player) {
        for (Map.Entry<String, Team> mapEntry : this.playerTeams.entrySet()) {
            Team team = mapEntry.getValue();
            if (team.getPlayerList().contains(player)) {
                return true;
            }
        }
        return false;
    }
}
