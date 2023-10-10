package fr.vivicoubar.minesteam;

import java.util.ArrayList;
import javax.annotation.Nullable;
import org.bukkit.entity.Player;

public class TeamManager {

  static ArrayList<Team> teams;

  public TeamManager(MinesTeam team) {
    loadTeams();
  }

  public static ArrayList<Team> getTeams() {
    return teams;
  }

  public static void loadTeams() {
    //TODO CHARGER LES EQUIPES
  }

  @Nullable
  public static Team getTeamByPlayer(Player toFindPlayer) {
    for(Team team : teams) {
      for (Player player : team.getPlayerList()) {
        if (player.getUniqueId().equals(toFindPlayer.getUniqueId())) {
          return team;
        }
      }
    }
    return null;
  }

  public static void addTeam(Team team) {
    teams.add(team);
  }
  
}
