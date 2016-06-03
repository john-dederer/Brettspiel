package edu.kit.informatik;

/**
 * The type Token.
 * A token is needed to play the game.
 *
 * @author John Dederer
 * @version 1.0
 */
public class Token {
    /**
     * The Size attribute.
     */
    private String size;
    /**
     * The Color attribute.
     */
    private String color;
    /**
     * The Shape attribute.
     */
    private String shape;
    /**
     * The Inner attribute.
     */
    private String inner;
    /**
     * The Number.
     */
    private int number;

    /**
     * Constructor for unique token.
     *
     * @param size   the size
     * @param color  the color
     * @param shape  the shape
     * @param inner  the inner attribute
     * @param number the number to identify
     */
    public Token(String color, String shape, String size, String inner, int number) {
        this.size = size;
        this.color = color;
        this.shape = shape;
        this.inner = inner;
        this.number = number;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets shape.
     *
     * @return the shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * Gets inner.
     *
     * @return the inner
     */
    public String getInner() {
        return inner;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }
}
