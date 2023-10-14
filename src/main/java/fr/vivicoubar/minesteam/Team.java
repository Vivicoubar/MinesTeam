package fr.vivicoubar.minesteam;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    /**
     * Name of the team
     */
    String name;
    /**
     * ArrayList of the players of the team
     */
    ArrayList<Player> playerList = new ArrayList<>();
    /**
     * Leader of the team, the only one who can delete it
     */
    Player leader;
    /**
     * The ones who can invite players to the team
     */
    ArrayList<Player> secondList = new ArrayList<>(); // Liste des seconds d'équipe
    /**
     * Players currently invited to the team who have to answer
     */
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

    public ArrayList<Player> getInvitedList() {
        return invitedList;
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

    /**
     * Makes an invited player a member of the team
     * @param invitedPlayer - the one invited to the team
     */
    public void addPlayer(Player invitedPlayer) {
        playerList.add(invitedPlayer);
        invitedList.remove(invitedPlayer);
    }

    /**
     * In case the invited one refuses to join the team
     * @param  - the one invited to the team
     */
    public void removeInvitedPlayer(Player invitedPlayer) {
        invitedList.remove(invitedPlayer);
    }

    /**
     * To remove someone from the team
     * @param player the removed one
     */
    public void removePlayer(Player player) {
        playerList.remove(player);
        secondList.remove(player);
    }

    /**
     * Change the leader of the team
     * @param oldLeader the previous leader, now  he's just a second of the team
     * @param targetPlayer becomes the new leader
     */
    public void changeLeader(Player oldLeader, Player targetPlayer) {
        leader = targetPlayer;
        if(!secondList.contains(targetPlayer)) {
            secondList.add(targetPlayer);
        }
    }

    /**
     * To send a message to all the team's players
     * @param message the string to send to everyone in the team currently connected
     */
    public void tellToEveryone(String message) {
        for(Player player : getPlayerList()) {
            if (player.isOnline()) {
                player.sendMessage(message);
            }
        }
    }
}
