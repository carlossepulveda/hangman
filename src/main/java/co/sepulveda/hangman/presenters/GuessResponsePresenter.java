package co.sepulveda.hangman.presenters;

/**
 *
 * @author carlossepulveda
 */
public class GuessResponsePresenter {

    private boolean ok;
    private GamePresenter game;

    public GuessResponsePresenter(boolean ok, GamePresenter game) {
        this.ok = ok;
        this.game = game;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public GamePresenter getGame() {
        return game;
    }

    public void setGame(GamePresenter game) {
        this.game = game;
    }
}
