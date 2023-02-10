package pixum.task.entities;

import java.util.List;

/**
 * Represents a frame.
 */
public interface Frame {

    /**
     * Calculates points in the frame.
     *
     * @return calculated points
     */
    int calculatePoints();

    /**
     * Returns the list of throws.
     *
     * @return list of throws
     */
    List<Throw> getThrowsList();

    /**
     * Returns the frame type - failure, spare, strike - for informational purposes.
     *
     * @return the type
     */
    FrameType getFrameType();

    /**
     * Sets the next frame.
     *
     * @param nextFrame the next frame, played after this
     */
    default void setNextFrame(Frame nextFrame) {
    }

    /**
     * Returns the next frame.
     *
     * @return the next frame
     */
    Frame getNextFrame();

    /**
     * Used in spare/strike when it's the last frame, to add bonus throws.
     *
     * @param thrws bonus throw(s)
     */
    default void addBonusThrow(Throw... thrws) {
    }

    /**
     * Returns the bonus throws list.
     *
     * @return bonus throws list
     */
    default List<Throw> getBonusThrowsList() {
        return null;
    }
}
