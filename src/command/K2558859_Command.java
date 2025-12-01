package command;

/**
 * K2558859_Command interface for Command Pattern.
 * All concrete commands (borrow, return, reserve, cancel reservation)
 * will implement this interface.
 */
public interface K2558859_Command {
    void execute();
}
