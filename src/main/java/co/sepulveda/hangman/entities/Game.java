package co.sepulveda.hangman.entities;

import java.util.Arrays;

/**
 *
 * @author carlossepulveda
 */
public class Game {

    private static final int MAX_TRIES = 11;
    public enum STATUS {
        BUSY, FAILED, SUCCESS
    }

    private final String id;
    private char[] originalWord;
    private char[] currentWord;
    private STATUS status;
    private int triesLeft;

    public Game(String id) {
        this.id = id;
    }

    public Game(String id, String word) {
        this.id = id;
        this.originalWord = word.toCharArray();
        this.currentWord = word.replaceAll("[A-Za-z0-9]", "\\.").toCharArray();
        triesLeft = MAX_TRIES;
        status = STATUS.BUSY;
    }

    public String getId() {
        return id;
    }

    public String getOriginalWord() {
        return String.valueOf(originalWord);
    }

    public String getCurrentWord() {
        return String.valueOf(currentWord);
    }

    public STATUS getStatus() {
        return status;
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    public boolean guess(char char_) {
        String hint = char_ + "";
        //It's trying to guess a char that was already guessed
        if (getCurrentWord().contains(hint )) {
            return true;
        }

        
        boolean response = guessChar(char_);
        if (!response) {
            triesLeft --;
        }
        updateStatus();

        return response;
    }

    private boolean guessChar(char char_) {
        boolean response = false;
        for (int i = 0; i < originalWord.length; i++) {
            char originalChar = originalWord[i];
            if (originalChar == char_) {
                response = true;
                currentWord[i] = char_;
            }
        }

        return response;
    }

    private void updateStatus() {
        if (Arrays.equals(currentWord, originalWord)) {
            status = STATUS.SUCCESS;
        } else if (triesLeft == 0) {
            status = STATUS.FAILED;
        }
    }

    public boolean isFinished() {
        return getStatus().equals(Game.STATUS.FAILED)
                || getStatus().equals(Game.STATUS.SUCCESS);
    }
}
