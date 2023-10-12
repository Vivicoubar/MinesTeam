package fr.vivicoubar.minesteam;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinesTeam extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("teamcreate").setExecutor(this);
        this.getCommand("inviteteam").setExecutor(this);
        this.getCommand("teamaddsecond").setExecutor(this);
        this.getCommand("teamremovesecond").setExecutor(this);
        this.getCommand("teamaccept").setExecutor(this);
        this.getCommand("teamrefuse").setExecutor(this);
        //loadTeams();
    }

    @Override
    public void onDisable() {
        //saveTeams();
        // Plugin shutdown logic
    }
       // TODO : 1 - vérifier les commandes existantes
        // TODO : 2 - faire les commandes teamaccept et teamrefuse.
        // TODO : 3 - commande pour quitter une équipe
        // TODO : 4 - créer une confirmation pour supprimer l'équipe (flemme enft)
        // TODO : 5 - rajouter un chat de l'équipe
        // TODO : 6 - enlever le friendly fire pour les membres d'une même équipe. Mettre une commande pour l'activer / désactiver si le CHEF le veut ou non.


}
