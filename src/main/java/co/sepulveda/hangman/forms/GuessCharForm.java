package co.sepulveda.hangman.forms;

/**
 *
 * @author carlossepulveda
 */
public class GuessCharForm {

    private String char_;

    public GuessCharForm() { }

    public GuessCharForm(String char_) {
        this.char_ = char_;
    }

    public String getChar() {
        return char_;
    }

    public void setChar_(String char_) {
        this.char_ = char_;
    }
}
