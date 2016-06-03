package edu.kit.informatik;

/**
 * Helper class to determine pitch type.
 *
 * @author John Dederer
 * @version 1.0
 */
public final class PitchParser {
    /**
     * Private constructor to avoid instantiation.
     */
    private PitchParser() {

    }

    /**
     * Returns the pitch type provided in program parameter.
     * Idea for this method came from example solutions of the Übungsaufgaben. Credits go to : Martin Löper.
     *
     * @param pitchType the type provided
     * @return the pitch type requested
     */
    public static Pitch getPitchType(String pitchType) {
        switch (pitchType) {
            case Pitch.TYPE_STD:
                return new Pitch();
            case Pitch.TYPE_TORUS:
                return new TorusPitch();
            default:
                Terminal.printLine("Error, the pitch type \"" + pitchType + "\" is invalid.");
                System.exit(1);
                return null;
        }
    }
}
