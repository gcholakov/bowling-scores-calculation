package pixum.task;

import pixum.task.entities.*;

import java.util.Arrays;
import java.util.List;

/**
 * Factory class for frame creation.
 */
public class FrameFactory {

    /**
     * Creates a frame, which type depends on the number of throws and pins fallen.
     *
     * @param thrs throws made in that frame
     * @return new frame
     */
    public Frame createFrame(Throw... thrs) {
        if (thrs == null || thrs.length == 0) {
            throw new IllegalArgumentException("Invalid throw!");
        } else if (thrs.length > 2) {
            throw new IllegalArgumentException("Invalid throws number!");
        }

        if (thrs.length == 2) {
            if (Arrays.stream(thrs).mapToInt(Throw::getPins).sum() < 10) {
                return new FailureFrame(List.of(thrs));
            } else {
                return new SpareFrame(List.of(thrs));
            }
        } else {
            return new StrikeFrame(List.of(thrs));
        }
    }
}
