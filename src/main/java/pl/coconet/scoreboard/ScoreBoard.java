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

	public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
		if (homeScore < 0 || awayScore < 0) {
			throw new IllegalArgumentException("Scores must be non-negative.");
		}

		for (Match match : matches) {
			if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
				match.updateScore(homeScore, awayScore);
				return;
			}
		}
		throw new NoSuchElementException("Match not found");
	}

	public void finishGame(String homeTeam, String awayTeam) {
		boolean matchRemoved = matches.removeIf(match ->
				match.getHomeTeam().equals(homeTeam) &&
						match.getAwayTeam().equals(awayTeam)
		);

		if (!matchRemoved) {
			throw new NoSuchElementException("Match not found.");
		}
	}

	public List<String> getSummary() {
		return matches.stream()
				.sorted(Comparator
						.comparingInt(Match::totalScore).reversed()
						.thenComparing(Match::getStartTime, Comparator.reverseOrder()))
				.map(Match::toString)
				.toList();
	}

}

