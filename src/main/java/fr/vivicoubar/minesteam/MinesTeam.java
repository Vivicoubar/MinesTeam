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

    public static HashMap<String, Team> playerTeams = new HashMap<>();
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
            return CmdManager.teamcreate(sender, command, label, args);
        }
        if (command.getName().equalsIgnoreCase("teamdelete")) {
            return CmdManager.teaminvite(sender, command, label, args);
        }
        if (command.getName().equalsIgnoreCase("teamaddsecond")) {
            return CmdManager.teamaddsecond(sender, command, label, args);
        }
        if (command.getName().equalsIgnoreCase("teamremovesecond")) {
            return CmdManager.teamremovesecond(sender, command, label, args);
        }
        if (command.getName().equalsIgnoreCase("teaminvite")) {
            return CmdManager.teaminvite(sender, command, label, args);
        }

        //TODO : envoi d'une demande au joueur cible. Il doit accepter avec une commande (ou refuser)
        //TODO : possibilité de supprimer une équipe (avec demande d'une confirmation ?)
        //TODO : possibilité de quitter une équipe (avec demande d'une confirmation)

        return false;
    }

    public static boolean hasTeam(Player player) {
        for (Map.Entry<String, Team> mapEntry : playerTeams.entrySet()) {
            Team team = mapEntry.getValue();
            if (team.getPlayerList().contains(player)) {
                return true;
            }
        }
        return false;
    }
}
