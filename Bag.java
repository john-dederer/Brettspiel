package edu.kit.informatik;

import java.util.ArrayList;

/**
 * The type Bag containing all tokens.
 *
 * @author John Dederer
 * @version 1.0
 */
public class Bag {

    /**
     * The Bag.
     */
    private ArrayList<Token> bag;

    /**
     * Instantiates a new Bag.
     */
    public Bag() {
        this.bag = new ArrayList<>();
    }

    /**
     * Gets bag.
     *
     * @return the bag
     */
    public ArrayList<Token> getBag() {
        return bag;
    }

    /**
     * Sets bag.
     *
     * @param bag the bag
     */
    public void setBag(ArrayList<Token> bag) {
        this.bag = bag;
    }

    /**
     * Fills bag with 16 unique tokens.
     */
    public void fillBag() {
        this.bag.add(new Token("black", "cornered", "small", "hollow", 0));
        this.bag.add(new Token("black", "cornered", "small", "solid", 1));
        this.bag.add(new Token("black", "cornered", "big", "hollow", 2));
        this.bag.add(new Token("black", "cornered", "big", "solid", 3));
        this.bag.add(new Token("black", "cylindrical", "small", "hollow", 4));
        this.bag.add(new Token("black", "cylindrical", "small", "solid", 5));
        this.bag.add(new Token("black", "cylindrical", "big", "hollow", 6));
        this.bag.add(new Token("black", "cylindrical", "big", "solid", 7));
        this.bag.add(new Token("white", "cornered", "small", "hollow", 8));
        this.bag.add(new Token("white", "cornered", "small", "solid", 9));
        this.bag.add(new Token("white", "cornered", "big", "hollow", 10));
        this.bag.add(new Token("white", "cornered", "big", "solid", 11));
        this.bag.add(new Token("white", "cylindrical", "small", "hollow", 12));
        this.bag.add(new Token("white", "cylindrical", "small", "solid", 13));
        this.bag.add(new Token("white", "cylindrical", "big", "hollow", 14));
        this.bag.add(new Token("white", "cylindrical", "big", "solid", 15));
    }

    /**
     * Remove token from bag.
     *
     * @param token to remove
     */
    public void removeToken(Token token) {
        this.bag.remove(token);

    }

    /**
     * Add token to bag.
     *
     * @param token the token
     */
    public void addToken(Token token) {
        this.bag.add(token);
    }
}
