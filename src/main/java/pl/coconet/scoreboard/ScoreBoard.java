package pl.coconet.scoreboard;

import java.util.*;

public class ScoreBoard {

	private final List<Match> matches = new ArrayList<>();

	public void startGame(String homeTeam, String awayTeam) {
		matches.add(new Match(homeTeam, awayTeam));
	}

	public void finishGame(String homeTeam, String awayTeam) {
		// TODO
	}

	public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		// TODO
	}

	public List<String> getSummary() {
		List<String> summary = new ArrayList<>();
		for (Match match : matches) {
			summary.add(match.toString());
		}
		return summary;
	}
}

