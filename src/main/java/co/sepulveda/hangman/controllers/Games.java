package co.sepulveda.hangman.controllers;

import co.sepulveda.hangman.business.GameBusiness;
import co.sepulveda.hangman.db.DB;
import co.sepulveda.hangman.entities.Game;
import co.sepulveda.hangman.exception.NoWordsException;
import co.sepulveda.hangman.forms.GuessCharForm;
import co.sepulveda.hangman.presenters.GamePresenter;
import co.sepulveda.hangman.presenters.GuessResponsePresenter;
import co.sepulveda.pongee.annotations.GET;
import co.sepulveda.pongee.annotations.POST;
import co.sepulveda.pongee.annotations.Resource;
import co.sepulveda.pongee.servlet.http.Request;
import co.sepulveda.pongee.servlet.http.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlossepulveda
 */

@Resource(name="/games")
public class Games {

    private final GameBusiness gameBusiness;

    public Games() {
        this.gameBusiness = new GameBusiness();
    }

    @GET
    public Response index(Request request) {
        List<GamePresenter> games = getAllGames();

        return new Response()
                .withBody(games)
                .withStatus(200);
    }

    private List<GamePresenter> getAllGames() {
        List<Game> games = gameBusiness.getAllGames();
        List<GamePresenter> gamePresenters = new ArrayList();
        for (Game game : games) {
            GamePresenter gamePresenter = createPresenter(game);
            gamePresenters.add(gamePresenter);
        }

        return gamePresenters;
    }

    @POST
    public Response create(Request request) {
        try{
            GamePresenter game = createGame();

            return new Response()
                    .withBody(game)
                    .withStatus(201);

        } catch (NoWordsException nwe) {
            return new Response()
                    .withStatus(400)
                    .withBody("{\"message\": \"All words were used. Please reboot the server\"");
        }
    }

    private GamePresenter createGame() throws NoWordsException {
        Game game = gameBusiness.createGame();
        return createPresenter(game);
    }

    @GET(path=":id")
    public Response viewGame(Request request) {
        String id = request.getPathVar("id");
        Game game = gameBusiness.getGame(id);
        if (game == null) {
            return Response.notFound();
        }

        GamePresenter presenter = createPresenter(game);
        return new Response().withBody(presenter);
    }

    private GamePresenter createPresenter(Game game) {
        String id = game.getId();
        String word = game.getCurrentWord();
        String status = game.getStatus().name();
        int tries = game.getTriesLeft();

        return new GamePresenter(id, word, status, tries);
    }

    @POST(path=":id")
    public Response guessChar(Request request) {
        String id = request.getPathVar("id");
        Game game = gameBusiness.getGame(id);
        if (game == null) {
            return Response.notFound();
        }

        if (game.isFinished()) {
            return new Response().withStatus(400).withBody("The game is finished");
        }

        GuessCharForm form = request.parseBody(GuessCharForm.class);
        System.out.println(form);
        if (form == null) {
            return new Response().withStatus(400).withBody("Invalid data");
        }

        char char_ = form.getChar().toLowerCase().charAt(0);
        GuessResponsePresenter presenter = guessChar(game.getId(), char_);
        return new Response().withBody(presenter);
    }

    private GuessResponsePresenter guessChar(String gameId, char char_) {
        boolean ok = gameBusiness.guessChar(gameId, char_);
        return createGuessResponse(ok, gameId);
    }

    private GuessResponsePresenter createGuessResponse(boolean response, String gameId) {
        Game game = gameBusiness.getGame(gameId);
        GamePresenter gamePresenter = createPresenter(game);

        return new GuessResponsePresenter(response, gamePresenter);
    }
}
