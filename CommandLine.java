package edu.kit.informatik;


import java.util.Stack;

/**
 * The interactive CommandLine to control the program flow.
 *
 * @author John Dederer
 * @version 1.0
 */
public final class CommandLine {

    /**
     * The select command.
     * Allows the player to take a token from the bag.
     * Accepts only one argument.
     * Prints out "OK" if draw was successful. Otherwise throws error.
     */
    public static final String SELECT = "select";

    /**
     * The place command.
     * Allows the player to place token if select command has been issued before.
     * If draw is not successful, token will be put back into the bag.
     * Accepts two arguments.
     * Prints out "OK" if successful, "draw" if no token left and draw, "P1 wins" if P1 wins and for P2 the same.
     */
    public static final String PLACE = "place";

    /**
     * The bag command.
     * Prints out the available tokens in the bag.
     * Does not accept any arguments.
     */
    public static final String BAG = "bag";

    /**
     * The rowprint command.
     * Prints out a row of the board.
     * Accepts only one argument.
     */
    public static final String ROWPRINT = "rowprint";

    /**
     * The colprint command.
     * Prints out a column of the board.
     * Accepts only one argument.
     */
    public static final String COLPRINT = "colprint";

    /**
     * The quit command.
     * Terminates the program without output.
     * Does not accept any arguments.
     */
    public static final String QUIT = "quit";

    private static int draw = 0;
    private static Stack<Token> tokenStack = new Stack<>();
    private static Stack<String> stringStack = new Stack<>();
    private static Stack<String> playerStack = new Stack<>();
    private static Stack<String> state = new Stack<>();

    /**
     * Private constructor to avoid object generation.
     */
    private CommandLine() {
    }

    /**
     * Helper method which checks the number of given to the number of required arguments.
     * Idea for this method came from example solutions of the Übungsaufgaben. Credits go to : Martin Löper.
     *
     * @param given    the number of arguments given
     * @param required the number of arguments required
     */
    private static int checkParameterNumber(int given, int required) {
        try {
            if (given != required && required == 0) {
                Terminal.printLine("Error, you provided " + given + " parameters but none are allowed.");
                return 0;
            } else if (given != required && required == 1) {
                Terminal.printLine("Error, you provided " + given + " parameters but only one is allowed.");
                return 0;
            } else if (given != required) {
                Terminal.printLine("Error, you provided " + given + " parameters but only two is allowed.");
                return 0;
            }
        } catch (NumberFormatException e) {
            Terminal.printLine("Error, " + e);
        }
        return 1;
    }

    /**
     * Reads commands from command-line and executes them if they are valid.
     * Method runs as long until quit command is issued.
     * Idea for this method came from example solutions of the Übungsaufgaben. Credits go to : Martin Löper.
     *
     * @param pitch the pitch to execute the commands on
     * @param bag   the bag containing all tokens
     */
    public static void startInteractiveSequence(Pitch pitch, Bag bag) {
        while (true) {
            String command = Terminal.readLine();
            if (command == null) {
                quit();
            }
            String[] split = command.replaceAll("\\s", ";").split(";");
            switch (split[0]) {
                case CommandLine.BAG:
                    if (checkParameterNumber(split.length - 1, 0) != 0) {
                        CommandLine.bag(bag);
                    }
                    break;
                case CommandLine.SELECT:
                        if (checkParameterNumber(split.length - 1, 1) != 0) {
                                CommandLine.select(split[1], bag, pitch);
                        }
                    break;
                case CommandLine.PLACE:
                        if (checkParameterNumber(split.length - 1, 2) != 0) {
                                CommandLine.place(split[1], split[2], bag, pitch);
                        }
                    break;
                case CommandLine.ROWPRINT:
                    try {
                        if (checkParameterNumber(split.length - 1, 1) != 0) {
                            CommandLine.rowPrint(pitch, Integer.parseInt(split[1]));
                        }
                    } catch (NumberFormatException e) {
                        Terminal.printLine("Error, row number has to be an integer value.");
                    }
                    break;
                case CommandLine.COLPRINT:
                    try {
                        if (checkParameterNumber(split.length - 1, 1) != 0) {
                            CommandLine.colPrint(pitch, Integer.parseInt(split[1]));
                        }
                    } catch (NumberFormatException e) {
                        Terminal.printLine("Error, column number has to be an integer value.");
                    }
                    break;
                case CommandLine.QUIT:
                    checkParameterNumber(split.length - 1, 0);
                    CommandLine.quit();
                    break;
                default:
                    if (command.trim().length() == 0) {
                        Terminal.printLine("Error, please enter a command.");
                    } else {
                        Terminal.printLine("Error, command \"" + split[0] + "\" not found. "
                                + "Only the following are valid: " + QUIT + ", " + BAG + ", "
                                + SELECT + ", " + PLACE + ", " + ROWPRINT + ", " + COLPRINT + ".");
                    }
            }
        }
    }

    /**
     * The select command.
     *
     * @see CommandLine#SELECT
     */
    private static void select(String token, Bag bag, Pitch pitch) {
        if (!state.isEmpty() && state.peek().contentEquals("done")) {
            Terminal.printLine("Error, game is finished. This command is not available now.");
        } else {
            try {
                if (stringStack.isEmpty()) {
                    Token temp = pitch.select(Integer.parseInt(token), bag);
                    if (temp != null) {
                        tokenStack.push(temp);
                        stringStack.push("select");
                    }

                    if (playerStack.isEmpty()) {
                        playerStack.push("P1");
                    }

                    if (playerStack.peek().contentEquals("P2")) {
                        playerStack.push("P1");
                    }

                    if (playerStack.peek().contentEquals("P1")) {
                        playerStack.push("P2");
                    }
                } else {
                    Terminal.printLine("Error, \"select\" command is not allowed.");
                }
            } catch (NumberFormatException e) {
                Terminal.printLine("Error, you have to provide a valid integer number.");
            }
        }
    }

    /**
     * The place command.
     *
     * @see CommandLine#PLACE
     */
    private static void place(String row, String column, Bag bag, Pitch pitch) {
        int drawTemp = draw;
        if (!state.isEmpty() && state.peek().contentEquals("done")) {
            Terminal.printLine("Error, game is finished. This command is not available now.");
        } else {
            try {
                if (!stringStack.isEmpty()) {
                    if (!stringStack.isEmpty() && playerStack.peek().contentEquals("P1")) {
                        playerStack.push("P2");
                    }

                    if (!stringStack.isEmpty() && playerStack.peek().contentEquals("P2")) {
                        playerStack.push("P1");
                    }

                    if (pitch.place(Integer.parseInt(row), Integer.parseInt(column), bag, tokenStack.peek())) {
                        stringStack.clear();
                        tokenStack.clear();
                        draw++;
                        if (!pitch.checkWin().contentEquals("win") && !bag.getBag().isEmpty()) {
                            Terminal.printLine("OK");
                        }
                    }



                    if (!pitch.checkWin().contentEquals("win") && bag.getBag().isEmpty()) {
                        Terminal.printLine("draw");
                        state.push("done");
                    }

                    if (pitch.checkWin().contentEquals("win")) {
                        Terminal.printLine(playerStack.peek() + " wins");
                        Terminal.printLine(String.valueOf(draw - 1));
                        state.push("done");
                    }

                    if (drawTemp == draw) {
                        bag.addToken(tokenStack.peek());
                        stringStack.clear();
                        tokenStack.clear();
                    }
                } else {
                    Terminal.printLine("Error, \"place\" command is not allowed.");
                }

            } catch (NumberFormatException e) {
                Terminal.printLine("Error, no valid integer number has been provided.");
            }
        }
    }

    /**
     * The bag command.
     *
     * @see CommandLine#BAG
     */
    private static void bag(Bag bag) {
        String output = "";
        for (Token token : bag.getBag()) {
            String temp = token.getNumber() + " ";
            output = output + temp;
        }
        Terminal.printLine(output);

    }

    /**
     * The rowprint command.
     *
     * @see CommandLine#ROWPRINT
     */
    private static void rowPrint(Pitch pitch, int row) {
        pitch.rowPrint(row);
    }

    /**
     * The colprint command.
     *
     * @see CommandLine#COLPRINT
     */
    private static void colPrint(Pitch pitch, int column) {
        pitch.colPrint(column);
    }

    /**
     * The quit command.
     *
     * @see #QUIT
     */
    private static void quit() {
        System.exit(0);
    }


}
