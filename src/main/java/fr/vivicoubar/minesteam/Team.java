package fr.vivicoubar.minesteam;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    String name;
    ArrayList<Player> playerList = new ArrayList<>();
    Player leader;
    ArrayList<Player> secondList = new ArrayList<>(); // Liste des seconds d'équipe
    ArrayList<Player> invitedList = new ArrayList<>(); // Liste des personnes invitées

    public Team(String name, Player leader) {
        this.name = name;
        this.leader = leader;
        this.playerList.add(leader);
    }

    public String getName(){
        return name;
    }

    public Player getLeader() {
        return leader;
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public ArrayList<Player> getSecondList() {
        return secondList;
    }

    /**
     * makes the player "player" a second of the team
     *
     * @param sender asking to put player as a second
     * @param player going (or not) to be a second
     */
    public void addSecond(Player sender, Player player){
        if(!this.playerList.contains(player)) {
            sender.sendMessage(ChatColor.RED + player.getName() + " n'est pas dans l'équipe " + this.name +" ! Vous ne pouvez pas l'ajouter en tant que sous-chef...");
            return;
        }
        if(!this.secondList.contains(player)) {
            sender.sendMessage(ChatColor.GREEN + player.getName() + " a bien été ajouté en tant que sous-chef de l'équipe " + this.name +".");
            this.secondList.add(player);
            return;
        }
        sender.sendMessage(ChatColor.RED + player.getName() + " est déjà sous-chef de l'équipe " + this.name + " !");
    }

    /**
     * removes the player "player" from the second list
     *
     * @param sender asking to remove
     * @param player going (or not) to be a removed
     */
    public void removeSecond(Player sender, Player player){
        if(!this.playerList.contains(player)) {
            sender.sendMessage(ChatColor.RED + player.getName() + " n'est pas dans l'équipe !");
            return;
        }
        if(this.secondList.contains(player)) {
            sender.sendMessage(ChatColor.GREEN + player.getName() + " a bien été enlevé des sous-chefs de l'équipe " + this.name +".");
            this.secondList.remove(player);
            return;
        }
        sender.sendMessage(ChatColor.RED + player.getName() + " n'est pas sous-chef de l'équipe " + this.name + " !");
    }
}
