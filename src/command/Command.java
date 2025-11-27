package command;

/**
 * Command interface for Command Pattern.
 * All concrete commands (borrow, return, reserve, cancel reservation)
 * will implement this interface.
 */
public interface Command {
    void execute();
}
