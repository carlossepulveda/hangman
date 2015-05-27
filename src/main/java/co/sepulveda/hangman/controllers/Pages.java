package co.sepulveda.hangman.controllers;

import co.sepulveda.pongee.annotations.GET;
import co.sepulveda.pongee.annotations.Resource;
import co.sepulveda.pongee.servlet.http.Request;
import co.sepulveda.pongee.servlet.http.Response;

/**
 *
 * @author carlossepulveda
 */
@Resource(name="/home")
public class Pages {

    @GET
    public Response index(Request request) {
        return Response.redirect("/static/index.html");
    }
}
