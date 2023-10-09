package fr.vivicoubar.minesteam;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    String nom;
    ArrayList<Player> playerList = new ArrayList<>();
    Player leader;

    public Team(String nom, Player leader) {
        this.nom = nom;
        this.leader = leader;
        this.playerList.add(leader);
    }



}
