package fr.vivicoubar.minesteam;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {

    /**
     * To show the players'team on chat
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player sender = event.getPlayer();
        String playerName = sender.getName();

        Team team = TeamManager.getTeamByPlayer(sender);
        String formattedMessage;
        if(team != null) {
            formattedMessage = ChatColor.YELLOW + "[" + team.getName() + "] " + ChatColor.WHITE + playerName + " : " + message;
        }
        else {
            formattedMessage = playerName + " : " + message;
        }
        event.setMessage(formattedMessage);
        event.setFormat(event.getMessage());
    }

}
