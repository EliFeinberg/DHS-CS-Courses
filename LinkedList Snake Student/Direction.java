/**
 * Defines the direction of the snake and allows for only valid changes of direction
 *
 */
public enum Direction {
    LEFT, RIGHT, UP, DOWN;
    /**
     * Checks to see if the direction entered is a valid change (e.g. pressing the down arrow
     *      if you're going up would cause the snake to die and is therefore invalid)
     * @param Direction the new direction to switch to
     */
    public boolean compatibleWith(Direction newDirection) {
        if (this.equals(LEFT) || this.equals(RIGHT)) {
            return UP.equals(newDirection) || DOWN.equals(newDirection); 
        } else {
            return LEFT.equals(newDirection) || RIGHT.equals(newDirection);
        }
    }
}