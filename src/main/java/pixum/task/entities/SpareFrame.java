package pixum.task.entities;

import lombok.Getter;

import java.util.List;

/**
 * Represents a spare frame.
 */
public class SpareFrame extends FailureFrame {

    /**
     * Pins fallen.
     */
    protected static final int TEN_PINS = 10;

    /**
     * Bonus throws list for the case when such frame is last.
     */
    @Getter
    protected List<Throw> bonusThrowsList;

    public SpareFrame(List<Throw> throwsList) {
        super(throwsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculatePoints() {
        if (nextFrame != null) {
            return TEN_PINS + nextFrame.getThrowsList().get(0).getPins();
        } else {
            // last frame is spare, calculate with one throw bonus
            return getThrowsList().stream().mapToInt(Throw::getPins).sum() + bonusThrowsList.get(0).getPins();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBonusThrow(Throw... thrws) {
        bonusThrowsList = List.of(thrws[0]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FrameType getFrameType() {
        return FrameType.SPARE;
    }
}
