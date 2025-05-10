package pl.coconet.scoreboard;

import java.util.*;

public class ScoreBoard {

	private final List<Match> matches = new ArrayList<>();

	public void startGame(String homeTeam, String awayTeam) {
		if (homeTeam == null || awayTeam == null || homeTeam.isBlank() || awayTeam.isBlank() || homeTeam.equals(awayTeam)) {
			throw new IllegalArgumentException("Invalid team names");
		}

		// Check if match already exists
		for (Match match : matches) {
			if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam) ||
					match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam)) {
				throw new IllegalArgumentException("Match already started");
			}
		}

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

