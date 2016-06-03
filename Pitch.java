package edu.kit.informatik;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The standard type Pitch.
 *
 * @author John Dederer
 * @version 1.0
 */
public class Pitch {

    /**
     * Identifier for type of pitch "standard".
     */
    public static final String TYPE_STD = "standard";

    /**
     * Identifier for type of pitch "torus".
     */
    public static final String TYPE_TORUS = "torus";
    /**
     * The pitch 2D matrix.
     */
    private Token[][] pitch;

    /**
     * Instantiates a new Pitch.
     */
    public Pitch() {
        this.pitch = new Token[6][6];
        for (int i = 0; i < 6; i++) {
            for (int b = 0; b < 6; b++) {
                this.pitch[i][b] = new Token("", "", "", "", 16);
            }
        }
    }

    /**
     * Get pitch string [ ] [ ].
     *
     * @return the string [ ] [ ]
     */
    public Token[][] getPitch() {
        return pitch;
    }

    /**
     * Row print command.
     * @see CommandLine#ROWPRINT
     *
     * @param row the row
     */
    public void rowPrint(int row) {
            String output = "";
            if (row > 5 || row < 0) {
                Terminal.printLine("Error, row number must be between 0 and 5 (0 and 5 included).");
            } else {
                for (int i = 0; i < 6; i++) {
                    if (pitch[row][i].getNumber() == 16) {
                        if (i == 5) {
                            String a = "#" + "";
                            output = output + a;
                        } else {
                            String a = "#" + " ";
                            output = output + a;
                        }
                    }

                    if (pitch[row][i].getNumber() != 16) {
                        if (i == 5) {
                            String temp = pitch[row][i].getNumber() + "";
                            output = output + temp;
                        } else {
                            String temp = pitch[row][i].getNumber() + " ";
                            output = output + temp;
                        }
                    }
                }
            }
        int index = output.lastIndexOf(" ");
            Terminal.printLine(output);
    }

    /**
     * Col print command.
     * @see CommandLine#COLPRINT
     *
     * @param column the column
     */
    public void colPrint(int column) {
        String output = "";
        if (column > 5 || column < 0) {
            Terminal.printLine("Error, column number must be between 0 and 5 (0 and 5 included).");
        } else {
            for (int i = 0; i < 6; i++) {
                if (pitch[i][column].getNumber() == 16) {
                    if (i == 5) {
                        String a = "#" + "";
                        output = output + a;
                    } else {
                        String a = "#" + " ";
                        output = output + a;
                    }
                }

                if (pitch[i][column].getNumber() != 16) {
                    if (i == 5) {
                        String temp = pitch[i][column].getNumber() + "";
                        output = output + temp;
                    } else {
                        String temp = pitch[i][column].getNumber() + " ";
                        output = output + temp;
                    }
                }
            }
        }
        Terminal.printLine(output);
    }

    /**
     * Select command.
     * @see CommandLine#SELECT
     *
     * @param token the token
     * @param bag the bag containing the tokens
     * @return the selected token
     */
    public Token select(int token, Bag bag) {
        List<Token> tempList = bag.getBag().stream().filter(p -> p.getNumber() == token).collect(Collectors.toList());

        if (token < 0 || (token > 15)) {
            Terminal.printLine("Error, token has to be an integer number between 0 and 15 (0 and 15 included).");
            return null;
        } else if (tempList.isEmpty()) {
            Terminal.printLine("Error, token is not available.");
            return null;
        } else if (tempList.size() == 1) {
            Terminal.printLine("OK");
            bag.removeToken(tempList.get(0));
            return tempList.get(0);
        }
        return null;
    }

    /**
     * Place command.
     * @see CommandLine#PLACE
     *
     * @param row    the row to place on
     * @param column the column to place on
     * @param bag    the bag containing the tokens
     * @param token the token to place
     * @return the boolean
     * @see CommandLine#PLACE CommandLine#PLACE
     */
    public boolean place(int row, int column, Bag bag, Token token) {
        String[] split = getCell(row, column).split(";");
        if (split[0].equals("false")) {
            return false;
        } else if (pitch[Integer.parseInt(split[0])][Integer.parseInt(split[1])].getNumber() == 16) {
            pitch[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = token;
            return true;
        } else if ((pitch[Integer.parseInt(split[0])][Integer.parseInt(split[1])].getNumber() != 16)) {
            Terminal.printLine("Error, cell is already taken.");
            return false;
        }
      return false;
    }

    /**
     * Returns the cell position
     * @param row the row index
     * @param column the column index
     * @return the cell coordinates
     */
    public String getCell(int row, int column) {
        if (row < 0 || row > 5 || column < 0 || column > 5) {
            Terminal.printLine("Error, invalid numbers entered. Try an integer from 0 to 5.");
            return "false";
        } else {
            String temp = row + ";" + column;
            return temp;
        }
    }

    /**
     * Checks rows for win
     * @return if won or not
     */
    public String checkRows() {
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (pitch[i][j].getColor().equals("black") && pitch[i][j + 1].getColor().equals("black")
                        && pitch[i][j + 2].getColor().equals("black") && pitch[i][j + 3].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[i][j + 1].getColor().equals("white")
                        && pitch[i][j + 2].getColor().equals("white") && pitch[i][j + 3].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[i][j + 1].getShape().equals("cornered")
                        && pitch[i][j + 2].getShape().equals("cornered")
                        && pitch[i][j + 3].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[i][j + 1].getShape().equals("cylindrical")
                        && pitch[i][j + 2].getShape().equals("cylindrical")
                        && pitch[i][j + 3].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[i][j + 1].getSize().equals("small")
                        && pitch[i][j + 2].getSize().equals("small") && pitch[i][j + 3].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[i][j + 1].getSize().equals("big")
                        && pitch[i][j + 2].getSize().equals("big") && pitch[i][j + 3].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[i][j + 1].getInner().equals("hollow")
                        && pitch[i][j + 2].getInner().equals("hollow") && pitch[i][j + 3].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[i][j + 1].getInner().equals("solid")
                        && pitch[i][j + 2].getInner().equals("solid") && pitch[i][j + 3].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";
    }

    /**
     * Check columns for win
     * @return if win or not
     */
    public String checkColumns() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 5; j >= 3; j--) {
                if (pitch[j][i].getColor().equals("black") && pitch[j - 1][i].getColor().equals("black")
                        && pitch[j - 2][i].getColor().equals("black") && pitch[j - 3][i].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[j][i].getColor().equals("white") && pitch[j - 1][i].getColor().equals("white")
                        && pitch[j - 2][i].getColor().equals("white") && pitch[j - 3][i].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[j][i].getShape().equals("cornered") && pitch[j - 1][i].getShape().equals("cornered")
                        && pitch[j - 2][i].getShape().equals("cornered")
                        && pitch[j - 3][i].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[j][i].getShape().equals("cylindrical") && pitch[j - 1][i].getShape().equals("cylindrical")
                        && pitch[j - 2][i].getShape().equals("cylindrical")
                        && pitch[j - 3][i].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[j][i].getSize().equals("small") && pitch[j - 1][i].getSize().equals("small")
                        && pitch[j - 2][i].getSize().equals("small") && pitch[j - 3][i].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[j][i].getSize().equals("big") && pitch[j - 1][i].getSize().equals("big")
                        && pitch[j - 2][i].getSize().equals("big") && pitch[j - 3][i].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[j][i].getInner().equals("hollow") && pitch[j - 1][i].getInner().equals("hollow")
                        && pitch[j - 2][i].getInner().equals("hollow") && pitch[j - 3][i].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[j][i].getInner().equals("solid") && pitch[j - 1][i].getInner().equals("solid")
                        && pitch[j - 2][i].getInner().equals("solid") && pitch[j - 3][i].getInner().equals("solid")) {
                    return "won";
                }

            }
        } return "NA";
    }

    /**
     * Check diagonal for win
     * @return if win or not
     */
    public String checkDiagonalOne() {
        for (int i = 5; i >= 3; i--) {
            for (int j = 0; j <= 2; j++) {
                if (pitch[i][j].getColor().equals("black") && pitch[i - 1][j + 1].getColor().equals("black")
                        && pitch[i - 2][j + 2].getColor().equals("black")
                        && pitch[i - 3][j + 3].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[i][j + 1].getColor().equals("white")
                        && pitch[i][j + 2].getColor().equals("white") && pitch[i][j + 3].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[i][j + 1].getShape().equals("cornered")
                        && pitch[i][j + 2].getShape().equals("cornered")
                        && pitch[i][j + 3].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[i][j + 1].getShape().equals("cylindrical")
                        && pitch[i][j + 2].getShape().equals("cylindrical")
                        && pitch[i][j + 3].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[i][j + 1].getSize().equals("small")
                        && pitch[i][j + 2].getSize().equals("small") && pitch[i][j + 3].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[i][j + 1].getSize().equals("big")
                        && pitch[i][j + 2].getSize().equals("big") && pitch[i][j + 3].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[i][j + 1].getInner().equals("hollow")
                        && pitch[i][j + 2].getInner().equals("hollow") && pitch[i][j + 3].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[i][j + 1].getInner().equals("solid")
                        && pitch[i][j + 2].getInner().equals("solid") && pitch[i][j + 3].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";
    }

    /**
     * Check diagonal for win
     * @return if win or not
     */
    public String checkDiagonalTwo() {
        for (int i = 5; i >= 3; i--) {
            for (int j = 5; j >= 3; j--) {
                if (pitch[i][j].getColor().equals("black") && pitch[i - 1][j - 1].getColor().equals("black")
                        && pitch[i - 2][j - 2].getColor().equals("black")
                        && pitch[i - 3][j - 3].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[i - 1][j - 1].getColor().equals("white")
                        && pitch[i - 2][j - 2].getColor().equals("white")
                        && pitch[i - 3][j - 3].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[i - 1][j - 1].getShape().equals("cornered")
                        && pitch[i - 2][j - 2].getShape().equals("cornered")
                        && pitch[i - 3][j - 3].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[i - 1][j - 1].getShape().equals("cylindrical")
                        && pitch[i - 2][j - 2].getShape().equals("cylindrical")
                        && pitch[i - 3][j - 3].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[i - 1][j - 1].getSize().equals("small")
                        && pitch[i - 2][j - 2].getSize().equals("small")
                        && pitch[i - 3][j - 3].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[i - 1][j - 1].getSize().equals("big")
                        && pitch[i - 2][j - 2].getSize().equals("big") && pitch[i - 3][j - 3].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[i - 1][j - 1].getInner().equals("hollow")
                        && pitch[i - 2][j - 2].getInner().equals("hollow")
                        && pitch[i - 3][j - 3].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[i - 1][j - 1].getInner().equals("solid")
                        && pitch[i - 2][j - 2].getInner().equals("solid")
                        && pitch[i - 3][j - 3].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";
    }

    /**
     * Helper method to ease process of determining winner
     * @return if win or not
     */
    public String checkWin() {
        if (checkRows().contentEquals("won") || checkColumns().contentEquals("won")
                || checkDiagonalOne().contentEquals("won") || checkDiagonalTwo().contentEquals("won")) {
            return "win";
        }

        if (checkRows().contentEquals("NA") || checkColumns().contentEquals("NA")
                || checkDiagonalOne().contentEquals("NA") || checkDiagonalTwo().contentEquals("NA")) {
            return "NA";
        }
        return "default";
    }
}
