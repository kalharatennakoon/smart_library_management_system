package command;

/**
 * CommandInvoker class for the Command Pattern.
 * Acts as the invoker that executes commands without knowing their implementation details.
 * This class decouples the requester of an action from the object that performs the action.
 */
public class CommandInvoker {
    private Command command;

    /**
     * Sets the command to be executed.
     * @param command The command object to be stored and executed later
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the currently set command.
     * This method acts as a unified interface to trigger any command action.
     * Throws a runtime exception if no command has been set.
     */
    public void pressButton() {
        if (command == null) {
            throw new IllegalStateException("No command has been set. Please set a command before executing.");
        }
        command.execute();
    }
}