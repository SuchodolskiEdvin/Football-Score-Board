package pl.coconet.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTest {

	@Test
	void shouldCreateMatchWithZeroScore() {
		Match match = new Match("Germany", "France");

		assertEquals("Germany", match.getHomeTeam());
		assertEquals("France", match.getAwayTeam());
		assertEquals(0, match.getHomeScore());
		assertEquals(0, match.getAwayScore());
	}
}
