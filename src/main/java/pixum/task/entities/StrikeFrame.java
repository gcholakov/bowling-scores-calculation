package pixum.task.entities;

import java.util.List;

/**
 * Represents a strike frame.
 */
public class StrikeFrame extends SpareFrame {
    public StrikeFrame(List<Throw> throwsList) {
        super(throwsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculatePoints() {
        if (nextFrame != null) {
            if (nextFrame.getFrameType() == FrameType.STRIKE) {
                if (nextFrame.getNextFrame() != null) {
                    return TEN_PINS + TEN_PINS + nextFrame.getNextFrame().getThrowsList().get(0).getPins();
                } else {
                    return TEN_PINS + TEN_PINS + nextFrame.getBonusThrowsList().get(0).getPins();
                }
            } else {
                return TEN_PINS + nextFrame.getThrowsList().stream().mapToInt(Throw::getPins).sum();
            }
        } else {
            // last frame is strike, calculate with all bonus throws if such
            return TEN_PINS + (bonusThrowsList != null ? bonusThrowsList.stream().mapToInt(Throw::getPins).sum() : 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBonusThrow(Throw... thrws) {
        bonusThrowsList = List.of(thrws);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FrameType getFrameType() {
        return FrameType.STRIKE;
    }
}
