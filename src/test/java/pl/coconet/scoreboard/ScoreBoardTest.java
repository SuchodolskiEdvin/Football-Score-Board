package pl.coconet.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreBoardTest {

	@Test
	void shouldStartGameWithInitialZeroScore() {
		ScoreBoard board = new ScoreBoard();
		board.startGame("Mexico", "Canada");

		List<String> summary = board.getSummary();
		assertEquals(1, summary.size());
		assertEquals("Mexico 0 - Canada 0", summary.get(0));
	}

	@Test
	void shouldNotAllowNullTeamNames() {
		ScoreBoard board = new ScoreBoard();

		assertThrows(IllegalArgumentException.class, () ->
				board.startGame(null, "Canada")
		);

		assertThrows(IllegalArgumentException.class, () ->
				board.startGame("Mexico", null)
		);
	}

	@Test
	void shouldNotAllowSameTeamToPlayAgainstItself() {
		ScoreBoard board = new ScoreBoard();

		assertThrows(IllegalArgumentException.class, () ->
				board.startGame("Mexico", "Mexico")
		);
	}

	@Test
	void shouldNotAllowDuplicateMatches() {
		ScoreBoard board = new ScoreBoard();
		board.startGame("Germany", "France");

		assertThrows(IllegalArgumentException.class, () ->
				board.startGame("France", "Germany")
		);
	}

}
