package fr.vivicoubar.minesteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.bukkit.entity.Player;

public class TeamManager {

  static HashMap<String, Team> teams = new HashMap<>();

  public TeamManager(MinesTeam team) {
    loadTeams();
  }

  public static HashMap<String, Team> getTeams() {
    return teams;
  }

  public static void loadTeams() {
    //TODO CHARGER LES EQUIPES
  }

  @Nullable
  public static Team getTeamByPlayer(Player toFindPlayer) {
    for (Map.Entry<String, Team> mapEntry : teams.entrySet()) {
      Team team = mapEntry.getValue();
      if (team.getPlayerList().contains(toFindPlayer)) {
        return team;
      }
    }
    return null;
  }

  public static void addTeam(Team team) {
    teams.put(team.getName(), team);
  }
  
}
