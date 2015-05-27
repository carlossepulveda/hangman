package co.sepulveda.hangman;

import co.sepulveda.pongee.Configuration;
import co.sepulveda.pongee.ServerPG;

/**
 *
 * @author carlossepulveda
 */
public class Server {

    private static final int PORT = 9090;

    public Server() { }

    public void start() {
        Configuration conf = Configuration.create()
                .withControllersPackage("co.sepulveda.hangman")
                .withPort(PORT);
        new ServerPG(conf).listen();
    }
}
