package co.sepulveda.hangman.presenters;

/**
 *
 * @author carlossepulveda
 */
public class GamePresenter {

    private final String id;
    private final String word;
    private final int tries;
    private final String status;

    public GamePresenter(String id, String word, String status, int tries) {
        this.id = id;
        this.word = word;
        this.tries = tries;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public int getTries() {
        return tries;
    }

    public String getStatus() {
        return status;
    }
}
