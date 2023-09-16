import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Main class for the Duke application.
 * This class initialises a new Duke instance that handles the
 * control and flow of the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs a new Duke object, along with a Storage object and an Ui object.
     */
    public Duke() {
        storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * The response the Duke application sends after receiving a user input.
     *
     * @param input The user input.
     * @return The message to be displayed.
     */
    public String getResponse(String input) {
        Command c;
        Parser parser = new Parser();
        c = parser.parse(input);

        String response = c.execute(tasks);
        storage.save(tasks.getTaskList());
        return response;
    }
}



