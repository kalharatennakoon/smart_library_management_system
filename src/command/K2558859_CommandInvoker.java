package command;

import java.util.ArrayList;
import java.util.List;

/**
 * K2558859_CommandInvoker class for Command Pattern.
 * Manages and executes commands in the library system.
 */
public class K2558859_CommandInvoker {
    private List<K2558859_Command> commandHistory;

    /**
     * Constructor for K2558859_CommandInvoker.
     * Initializes the command history list.
     */
    public K2558859_CommandInvoker() {
        this.commandHistory = new ArrayList<>();
    }

    /**
     * Executes a command and adds it to the history.
     * @param command The command to execute
     */
    public void executeCommand(K2558859_Command command) {
        command.execute();
        commandHistory.add(command);
    }

    /**
     * Gets the command history.
     * @return List of executed commands
     */
    public List<K2558859_Command> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }

    /**
     * Clears the command history.
     */
    public void clearHistory() {
        commandHistory.clear();
        System.out.println("Command history cleared.");
    }
}
