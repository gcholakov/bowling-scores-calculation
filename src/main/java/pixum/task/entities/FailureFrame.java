package pixum.task.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a frame without full success in fallen pins.
 */
public class FailureFrame implements Frame {

    /**
     * List with throws for the frame.
     */
    @Getter
    private final List<Throw> throwsList;

    /**
     * Reference to the next frame, used for calculations with spare/strike.
     */
    @Setter
    @Getter
    protected Frame nextFrame;

    public FailureFrame(List<Throw> throwsList) {
        this.throwsList = throwsList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculatePoints() {
        return throwsList.stream().mapToInt(Throw::getPins).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FrameType getFrameType() {
        return FrameType.FAILURE;
    }
}
