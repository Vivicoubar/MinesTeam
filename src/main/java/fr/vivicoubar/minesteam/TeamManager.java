package fr.vivicoubar.minesteam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.bukkit.entity.Player;

public class TeamManager {

  /**
   * HashMap containing all the team of the server
   * keys : (string) names of the team
   * values : (Team) the teams themselves
   */
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

  /**
   * To get the team of a Player.
   * @param toFindPlayer the player we want to know the team
   * @return toFindPlayer's team, or NULL if he has no team
   */
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

  /**
   * To be used when a team is created
   * Adds it to the HashMap containing all the teams of the server
   * @param team the new team
   */
  public static void addTeam(Team team) {
    teams.put(team.getName(), team);
  }
  
}
