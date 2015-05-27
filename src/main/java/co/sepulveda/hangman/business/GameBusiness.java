package co.sepulveda.hangman.business;

import co.sepulveda.hangman.db.DB;
import co.sepulveda.hangman.entities.Game;
import co.sepulveda.hangman.exception.NoWordsException;
import java.util.List;

/**
 *
 * @author carlossepulveda
 */
public class GameBusiness {

    private final DB db;
    private static int counter = 0;

    private static final String[] words = new String[] {
        "guess", "miwi", "developer", "test", "server", "smartphone",
        "technology", "software", "country", "skill", "book", "board",
        "girl", "boy", "headphones", "body", "computer", "browser"
    };

    public GameBusiness() {
        this.db = new DB();
    }

    public Game createGame() throws NoWordsException {
        String id = String.valueOf(System.currentTimeMillis());
        String word = getWord();

        Game game = new Game(id, word);
        db.save(Game.class, id, game);
        return game;
    }

    private static String getWord() throws NoWordsException {
        if (counter < words.length) {
            counter ++;
            return words[counter];
        } else {
            throw new NoWordsException("There are not available words");
        }
    }

    public List<Game> getAllGames() {
        return (List<Game>) db.list(Game.class);
    }

    public Game getGame(String id) {
        return (Game) db.get(Game.class, id);
    }

    public boolean guessChar(String gameId, char char_) {
        Game game = (Game) db.get(Game.class, gameId);
        boolean response = game.guess(char_);
        db.save(Game.class, gameId, game);

        return response;
    }
}
