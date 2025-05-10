package pl.coconet.scoreboard;

public class Match {
	private final String homeTeam;
	private final String awayTeam;
	private int homeScore;
	private int awayScore;

	public Match(String homeTeam, String awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = 0;
		this.awayScore = 0;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void updateScore(int home, int away) {
		this.homeScore = home;
		this.awayScore = away;
	}

	public int totalScore() {
		return homeScore + awayScore;
	}


	@Override
	public String toString() {
		return homeTeam + " " + homeScore + " - " + awayTeam + " " + awayScore;
	}
}
