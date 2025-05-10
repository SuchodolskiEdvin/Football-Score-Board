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

	@Test
	void shouldHandleMultipleGamesAndSortByScore() {
		ScoreBoard board = new ScoreBoard();

		// Start and update games
		board.startGame("Mexico", "Canada");
		board.updateScore("Mexico", "Canada", 0, 5);

		board.startGame("Spain", "Brazil");
		board.updateScore("Spain", "Brazil", 10, 2);

		board.startGame("Germany", "France");
		board.updateScore("Germany", "France", 2, 2);

		board.startGame("Uruguay", "Italy");
		board.updateScore("Uruguay", "Italy", 6, 6);

		board.startGame("Argentina", "Australia");
		board.updateScore("Argentina", "Australia", 3, 1);

		// Get the summary and check the sorting order
		List<String> summary = board.getSummary();

		// Assert correct order by total score, and recency
		assertEquals(5, summary.size());
		assertEquals("Uruguay 6 - Italy 6", summary.get(0));
		assertEquals("Spain 10 - Brazil 2", summary.get(1));
		assertEquals("Mexico 0 - Canada 5", summary.get(2));
		assertEquals("Argentina 3 - Australia 1", summary.get(3));
		assertEquals("Germany 2 - France 2", summary.get(4));
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

	@Test
	void shouldRemoveFinishedGame() {
		ScoreBoard board = new ScoreBoard();
		board.startGame("Mexico", "Cadana");
		board.updateScore("Mexico", "Cadana", 2, 1);

		// Finish the game
		board.finishGame("Mexico", "Cadana");

		// Assert that the game is removed from the summary
		List<String> summary = board.getSummary();
		assertEquals(0, summary.size());
	}


	// ScoreBoard.getSummary() TESTS -------------------------------------------------------------------------------

}
