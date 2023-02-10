package pixum.task.entities;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a game with exactly 10 frames.
 */
@Slf4j
public class Game {

    /**
     * Maximum number of frames per game.
     */
    private static final int MAX_FRAMES = 10;

    /**
     * List with all frames.
     */
    private final List<Frame> frames;

    public Game() {
        frames = Arrays.asList(new Frame[MAX_FRAMES]);
    }

    /**
     * Check if the game is over.
     */
    public boolean isGameOver() {
        if (getPlayedFramesList().size() < MAX_FRAMES) {
            return false;
        }

        Frame lastFrame = getPlayedFramesList().get(9);

        if (lastFrame.getFrameType() == FrameType.SPARE &&
                (lastFrame.getBonusThrowsList() == null || lastFrame.getBonusThrowsList().size() != 1)) {
            return false;
        }

        if (lastFrame.getFrameType() == FrameType.STRIKE &&
                (lastFrame.getBonusThrowsList() == null || lastFrame.getBonusThrowsList().size() != 2)) {
            return false;
        }

        return true;
    }

    /**
     * Add a frame with throws and fallen pins.
     */
    public void addFrame(Frame frame) {
        int index = getPlayedFramesList().size();
        frames.set(index, frame);
    }

    /**
     * Get the total score so far.
     */
    public int calculateTotalScore() {
        int totalScore = 0;
        List<Frame> playedFrames = getPlayedFramesList();

        for (int i = 0; i < playedFrames.size(); i++) {
            Frame frame = playedFrames.get(i);
            int frameScore = frame.calculatePoints();
            totalScore += frameScore;
            log.info("Frame {} is {} and has score {}, fallen pins by throw {}", i + 1, frame.getFrameType(),
                    frameScore,
                    frame.getThrowsList() + (frame.getBonusThrowsList() != null ? " " + frame.getBonusThrowsList() : ""));
        }

        log.info("Total score so far is {}", totalScore);
        return totalScore;
    }

    /**
     * Getting a list of all frames of the game recorded so far.
     *
     * @return the list with frames played
     */
    public List<Frame> getPlayedFramesList() {
        return frames.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
