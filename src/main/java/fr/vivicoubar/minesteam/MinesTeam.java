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
        this.getCommand("teamleave").setExecutor(this);
        this.getCommand("teamsetleader").setExecutor(this);
        this.getCommand("teamchat").setExecutor(this);

        PlayerListener playerListener = new PlayerListener();
        getServer().getPluginManager().registerEvents(playerListener, this);

        //loadTeams();
    }

    @Override
    public void onDisable() {
        //saveTeams();
        // Plugin shutdown logic
    }
        // TODO : 1 - vérifier les commandes existantes OK
        // TODO : 2 - faire les commandes teamaccept et teamrefuse OK
        // TODO : 3 - commande pour quitter une équipe OK
        // TODO : (4) - créer une confirmation pour supprimer l'équipe (flemme enft)
        // TODO : 5 - Rajouter genre [NOM EQUIPE] devant le pseudo des joueurs dans le chat général OK
        // TODO 5.1 - Commande pour changer de leader de team OK
        // TODO : 6 - load() et save()
        // TODO : 7 - rajouter un chat de l'équipe OK
        // TODO : 8 - enlever le friendly fire pour les membres d'une même équipe. Mettre une commande pour l'activer / désactiver si le CHEF le veut ou non.


}
