package edu.kit.informatik;

/**
 * @author John Dederer
 * @version 1.0
 */
public final class Main {

    private Main() {
    }

    /**
     * The entry point of the program.
     * For a full list of commands:
     * @see CommandLine#SELECT
     * @see CommandLine#PLACE
     * @see CommandLine#BAG
     * @see CommandLine#ROWPRINT
     * @see CommandLine#COLPRINT
     * @see CommandLine#QUIT
     * @param args the type of pitch
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            Terminal.printLine("Error, one parameter is required to start the game.");
            System.exit(1);
        }

        Pitch pitch = PitchParser.getPitchType(args[0]); //generates pitch based on parameter type provided
        Bag bag = new Bag();
        bag.fillBag(); //fills bag with 16 unique tokens

        CommandLine.startInteractiveSequence(pitch, bag);

    }
}
