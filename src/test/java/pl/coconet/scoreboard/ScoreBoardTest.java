package pl.coconet.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreBoardTest {

	// ScoreBoard.startGame() TESTS --------------------------------------------------------------------------------

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

	// ScoreBoard.updateScore() TESTS ------------------------------------------------------------------------------

	@Test
	void shouldUpdateScoreOfExistingMatch() {
		ScoreBoard board = new ScoreBoard();
		board.startGame("Mexico", "Canada");
		board.updateScore("Mexico", "Canada", 3, 1);

		List<String> summary = board.getSummary();
		assertEquals(1, summary.size());
		assertEquals("Mexico 3 - Canada 1", summary.get(0));
	}

	@Test
	void shouldThrowWhenUpdatingNonExistentMatch() {
		ScoreBoard board = new ScoreBoard();

		assertThrows(NoSuchElementException.class, () ->
				board.updateScore("Mexico", "Canada", 2, 2)
		);
	}

	@Test
	void shouldThrowWhenUpdatingWithNegativeScores() {
		ScoreBoard board = new ScoreBoard();
		board.startGame("Mexico", "Canada");

		assertThrows(IllegalArgumentException.class, () ->
				board.updateScore("Mexico", "Canada", -1, 2)
		);

		assertThrows(IllegalArgumentException.class, () ->
				board.updateScore("Mexico", "Canada", 2, -3)
		);
	}

	// ScoreBoard.finishGame() TESTS -------------------------------------------------------------------------------

	// ScoreBoard.getSummary() TESTS -------------------------------------------------------------------------------

}
