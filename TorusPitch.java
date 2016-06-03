package edu.kit.informatik;

/**
 * The type Torus pitch.
 * Special edge interaction
 *
 * @author John Dederer
 * @version 1.0
 */
public class TorusPitch extends Pitch {

    /**
     * Instantiates a new Torus pitch.
     */
    public TorusPitch() {
        super();
    }

    @Override
    public String getCell(int row, int column) {
        int x = row;
        int y = column;
        if (row > 5) {
            x = row % 6;
        }

        if (column > 5) {
            y = column % 6;
        }

        if (row < 0) {
            x = 5 - (Math.abs(row + 1) % 6);
        }

        if (column < 0) {
            y = 5 - (Math.abs(column + 1) % 6);
        }

        String temp = x + ";" + y;
        return temp;
    }

    /**
     * Check special edges for win
     * @return if won or not
     */
    public String checkRowsTorus() {
        Token[][] pitch = super.getPitch();
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j <= 5; j++) {
                // special behavior
                int one = j + 1;
                int two = j + 2;
                int three = j + 3;
                if (j + 1 > 5) {
                    one = getToken(j + 1);
                }
                if (j + 2 > 5) {
                    two = getToken(j + 2);
                }
                if (j + 3 > 5) {
                    three = getToken(j + 3);
                }

                if (pitch[i][j].getColor().equals("black") && pitch[i][one].getColor().equals("black")
                        && pitch[i][two].getColor().equals("black") && pitch[i][three].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[i][one].getColor().equals("white")
                        && pitch[i][two].getColor().equals("white") && pitch[i][three].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[i][one].getShape().equals("cornered")
                        && pitch[i][two].getShape().equals("cornered")
                        && pitch[i][three].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[i][one].getShape().equals("cylindrical")
                        && pitch[i][two].getShape().equals("cylindrical")
                        && pitch[i][three].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[i][one].getSize().equals("small")
                        && pitch[i][two].getSize().equals("small") && pitch[i][three].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[i][one].getSize().equals("big")
                        && pitch[i][two].getSize().equals("big") && pitch[i][three].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[i][one].getInner().equals("hollow")
                        && pitch[i][two].getInner().equals("hollow") && pitch[i][three].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[i][one].getInner().equals("solid")
                        && pitch[i][two].getInner().equals("solid") && pitch[i][three].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";
    }

    /**
     * Helper method to determine cell
     * @param cell the cell to look at
     * @return the converted cell
     */
    public int getToken(int cell) {
        int special = 0;
        if (cell == 6) {
            special = 0;
        }
        if (cell == 7) {
            special = 1;
        }
        if (cell == 8) {
            special = 2;
        }
        if (cell == -1) {
            special = 5;
        }
        if (cell == -2) {
            special = 4;
        }
        if (cell == -3) {
            special = 3;
        }
        return special;
    }

    /**
     * Check special edges for win
     * @return if won or not
     */
    public String checkColumnsTorus() {
        Token[][] pitch = super.getPitch();
        for (int i = 0; i <= 5; i++) {
            for (int j = 5; j >= 0; j--) {
                // special behavior
                int one = j - 1;
                int two = j - 2;
                int three = j - 3;
                if (j - 1 < 0) {
                    one = getToken(j - 1);
                }
                if (j - 2 < 0) {
                    two = getToken(j - 2);
                }
                if (j - 3 < 0) {
                    three = getToken(j - 3);
                }

                if (pitch[j][i].getColor().equals("black") && pitch[one][i].getColor().equals("black")
                        && pitch[two][i].getColor().equals("black") && pitch[three][i].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[j][i].getColor().equals("white") && pitch[one][i].getColor().equals("white")
                        && pitch[two][i].getColor().equals("white") && pitch[three][i].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[j][i].getShape().equals("cornered") && pitch[one][i].getShape().equals("cornered")
                        && pitch[two][i].getShape().equals("cornered")
                        && pitch[three][i].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[j][i].getShape().equals("cylindrical") && pitch[one][i].getShape().equals("cylindrical")
                        && pitch[two][i].getShape().equals("cylindrical")
                        && pitch[three][i].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[j][i].getSize().equals("small") && pitch[one][i].getSize().equals("small")
                        && pitch[two][i].getSize().equals("small") && pitch[three][i].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[j][i].getSize().equals("big") && pitch[one][i].getSize().equals("big")
                        && pitch[two][i].getSize().equals("big") && pitch[three][i].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[j][i].getInner().equals("hollow") && pitch[one][i].getInner().equals("hollow")
                        && pitch[two][i].getInner().equals("hollow") && pitch[three][i].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[j][i].getInner().equals("solid") && pitch[one][i].getInner().equals("solid")
                        && pitch[two][i].getInner().equals("solid") && pitch[three][i].getInner().equals("solid")) {
                    return "won";
                }

            }
        } return "NA";

    }

    /**
     *  Check special edges for win
     * @return if won or not
     */
    public String checkDiagonalOneTorus() {
        Token[][] pitch = super.getPitch();
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j <= 5; j++) {

                // special behavior
                int oneX = i - 1;
                int oneY = j + 1;
                int twoX = i - 2;
                int twoY = j + 2;
                int threeX = i - 3;
                int threeY = j + 3;
                if (i - 1 < 0) {
                    oneX = getToken(i - 1);
                }
                if (j + 1 > 5) {
                    oneY = getToken(j + 1);
                }
                if (i - 2 < 0) {
                    twoX = getToken(i - 2);
                }
                if (j + 2 > 5) {
                    twoY = getToken(j + 2);
                }
                if (i - 3 < 0) {
                    threeX = getToken(i - 3);
                }
                if (j + 3 > 5) {
                    threeY = getToken(j + 3);
                }

                if (pitch[i][j].getColor().equals("black") && pitch[oneX][oneY].getColor().equals("black")
                        && pitch[twoX][twoY].getColor().equals("black")
                        && pitch[threeX][threeY].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[oneX][oneY].getColor().equals("white")
                        && pitch[twoX][twoY].getColor().equals("white")
                        && pitch[threeX][threeY].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[oneX][oneY].getShape().equals("cornered")
                        && pitch[twoX][twoY].getShape().equals("cornered")
                        && pitch[threeX][threeY].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[oneX][oneY].getShape().equals("cylindrical")
                        && pitch[twoX][twoY].getShape().equals("cylindrical")
                        && pitch[threeX][threeY].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[oneX][oneY].getSize().equals("small")
                        && pitch[twoX][twoY].getSize().equals("small")
                        && pitch[threeX][threeY].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[oneX][oneY].getSize().equals("big")
                        && pitch[twoX][twoY].getSize().equals("big") && pitch[threeX][threeY].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[oneX][oneY].getInner().equals("hollow")
                        && pitch[twoX][twoY].getInner().equals("hollow")
                        && pitch[threeX][threeY].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[oneX][oneY].getInner().equals("solid")
                        && pitch[twoX][twoY].getInner().equals("solid")
                        && pitch[threeX][threeY].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";

    }

    /**
     *  Check special edges for win
     * @return if won or not
     */
    public String checkDiagonalTwoTorus() {
        Token[][] pitch = super.getPitch();
        for (int i = 5; i >= 0; i--) {
            for (int j = 5; j >= 0; j--) {

                // special behavior
                int oneX = i - 1;
                int oneY = j - 1;
                int twoX = i - 2;
                int twoY = j - 2;
                int threeX = i - 3;
                int threeY = j - 3;
                if (i - 1 < 0) {
                    oneX = getToken(i - 1);
                }
                if (j - 1 < 0) {
                    oneY = getToken(j - 1);
                }
                if (i - 2 < 0) {
                    twoX = getToken(i - 2);
                }
                if (j - 2 < 0) {
                    twoY = getToken(j - 2);
                }
                if (i - 3 < 0) {
                    threeX = getToken(i - 3);
                }
                if (j - 3 < 0) {
                    threeY = getToken(j - 3);
                }

                if (pitch[i][j].getColor().equals("black") && pitch[oneX][oneY].getColor().equals("black")
                        && pitch[twoX][twoY].getColor().equals("black")
                        && pitch[threeX][threeY].getColor().equals("black")) {
                    return "won";
                }
                if (pitch[i][j].getColor().equals("white") && pitch[oneX][oneY].getColor().equals("white")
                        && pitch[twoX][twoY].getColor().equals("white")
                        && pitch[threeX][threeY].getColor().equals("white")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cornered") && pitch[oneX][oneY].getShape().equals("cornered")
                        && pitch[twoX][twoY].getShape().equals("cornered")
                        && pitch[threeX][threeY].getShape().equals("cornered")) {
                    return "won";
                }
                if (pitch[i][j].getShape().equals("cylindrical") && pitch[oneX][oneY].getShape().equals("cylindrical")
                        && pitch[twoX][twoY].getShape().equals("cylindrical")
                        && pitch[threeX][threeY].getShape().equals("cylindrical")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("small") && pitch[oneX][oneY].getSize().equals("small")
                        && pitch[twoX][twoY].getSize().equals("small")
                        && pitch[threeX][threeY].getSize().equals("small")) {
                    return "won";
                }
                if (pitch[i][j].getSize().equals("big") && pitch[oneX][oneY].getSize().equals("big")
                        && pitch[twoX][twoY].getSize().equals("big") && pitch[threeX][threeY].getSize().equals("big")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("hollow") && pitch[oneX][oneY].getInner().equals("hollow")
                        && pitch[twoX][twoY].getInner().equals("hollow")
                        && pitch[threeX][threeY].getInner().equals("hollow")) {
                    return "won";
                }
                if (pitch[i][j].getInner().equals("solid") && pitch[oneX][oneY].getInner().equals("solid")
                        && pitch[twoX][twoY].getInner().equals("solid")
                        && pitch[threeX][threeY].getInner().equals("solid")) {
                    return "won";
                }
            }
        } return "NA";

    }

    @Override
    public String checkWin() {
        if (checkRowsTorus().contentEquals("won") || checkColumnsTorus().contentEquals("won")
                || checkDiagonalOneTorus().contentEquals("won") || checkDiagonalTwoTorus().contentEquals("won")) {
            return "win";
        }

        if (checkRowsTorus().contentEquals("NA") || checkColumnsTorus().contentEquals("NA")
                || checkDiagonalOneTorus().contentEquals("NA") || checkDiagonalTwoTorus().contentEquals("NA")) {
            return "NA";
        }
        return "default";

    }
}
