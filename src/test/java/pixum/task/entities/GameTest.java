package pixum.task.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pixum.task.FrameFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Game class.
 */
class GameTest {

    private Game game;
    private final FrameFactory frameFactory = new FrameFactory();

    @BeforeEach
    void setUp() {
        game = new pixum.task.entities.Game();
    }

    /**
     * Tests the calculation after two failure frames.
     */
    @Test
    void calculateTotalScoreSoFar_AfterTwoFailureFrames() {
        Frame frame = frameFactory.createFrame(new Throw(4), new Throw(5));
        assertEquals(FrameType.FAILURE, frame.getFrameType());
        game.addFrame(frame);

        frame = frameFactory.createFrame(new Throw(1), new Throw(0));
        assertEquals(FrameType.FAILURE, frame.getFrameType());
        game.addFrame(frame);

        assertEquals(2, game.getPlayedFramesList().size());
        assertEquals(10, game.calculateTotalScoreSoFar());
    }

    /**
     * Tests the calculation with four frames, 1 spare and 2 strikes.
     */
    @Test
    void calculateTotalScoreSoFar_AfterFourWithSpareAndStrike() {
        Frame frame1 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(10));
        Frame frame3 = frameFactory.createFrame(new Throw(10));
        Frame frame4 = frameFactory.createFrame(new Throw(3), new Throw(5));
        frame1.setNextFrame(frame2);
        frame2.setNextFrame(frame3);
        frame3.setNextFrame(frame4);
        game.addFrame(frame1);
        game.addFrame(frame2);
        game.addFrame(frame3);
        game.addFrame(frame4);

        assertFalse(game.isGameOver());
        assertEquals(4, game.getPlayedFramesList().size());
        assertEquals(69, game.calculateTotalScoreSoFar());
    }

    /**
     * Tests the calculation with four frames, ending with strike.
     */
    @Test
    void calculateTotalScoreSoFar_EndingWithStrike() {
        Frame frame1 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(10));
        Frame frame3 = frameFactory.createFrame(new Throw(3), new Throw(5));
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        frame1.setNextFrame(frame2);
        frame2.setNextFrame(frame3);
        frame3.setNextFrame(frame4);
        game.addFrame(frame1);
        game.addFrame(frame2);
        game.addFrame(frame3);
        game.addFrame(frame4);

        assertFalse(game.isGameOver());
        assertEquals(4, game.getPlayedFramesList().size());
        assertEquals(56, game.calculateTotalScoreSoFar());
    }

    /**
     * Tests calculation after 10 frames with all kinds.
     */
    @Test
    void calculateTotalScoreSoFar_After10AllKinds() {
        Frame frame1 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(10));
        Frame frame3 = frameFactory.createFrame(new Throw(10));
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        Frame frame5 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame6 = frameFactory.createFrame(new Throw(2), new Throw(5));
        Frame frame7 = frameFactory.createFrame(new Throw(2), new Throw(4));
        Frame frame8 = frameFactory.createFrame(new Throw(1), new Throw(9));
        Frame frame9 = frameFactory.createFrame(new Throw(10));
        Frame frame10 = frameFactory.createFrame(new Throw(4), new Throw(6));

        frame1.setNextFrame(frame2);
        frame2.setNextFrame(frame3);
        frame3.setNextFrame(frame4);
        frame4.setNextFrame(frame5);
        frame5.setNextFrame(frame6);
        frame6.setNextFrame(frame7);
        frame7.setNextFrame(frame8);
        frame8.setNextFrame(frame9);
        frame9.setNextFrame(frame10);

        game.addFrame(frame1);
        game.addFrame(frame2);
        game.addFrame(frame3);
        game.addFrame(frame4);
        game.addFrame(frame5);
        game.addFrame(frame6);
        game.addFrame(frame7);
        game.addFrame(frame8);
        game.addFrame(frame9);
        game.addFrame(frame10);
        frame10.addBonusThrow(new Throw(5));

        assertEquals(175, game.calculateTotalScoreSoFar());
        assertTrue(game.isGameOver());
    }

    /**
     * Tests calculation when the last frame is spare.
     */
    @Test
    void calculateTotalScoreSoFar_LastIsSpare() {
        Frame frame1 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(10));
        Frame frame3 = frameFactory.createFrame(new Throw(10));
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        Frame frame5 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame6 = frameFactory.createFrame(new Throw(2), new Throw(5));
        Frame frame7 = frameFactory.createFrame(new Throw(2), new Throw(4));
        Frame frame8 = frameFactory.createFrame(new Throw(1), new Throw(9));
        Frame frame9 = frameFactory.createFrame(new Throw(10));
        Frame frame10 = frameFactory.createFrame(new Throw(4), new Throw(6));

        frame1.setNextFrame(frame2);
        frame2.setNextFrame(frame3);
        frame3.setNextFrame(frame4);
        frame4.setNextFrame(frame5);
        frame5.setNextFrame(frame6);
        frame6.setNextFrame(frame7);
        frame7.setNextFrame(frame8);
        frame8.setNextFrame(frame9);
        frame9.setNextFrame(frame10);

        game.addFrame(frame1);
        game.addFrame(frame2);
        game.addFrame(frame3);
        game.addFrame(frame4);
        game.addFrame(frame5);
        game.addFrame(frame6);
        game.addFrame(frame7);
        game.addFrame(frame8);
        game.addFrame(frame9);
        game.addFrame(frame10);
        frame10.addBonusThrow(new Throw(5));

        assertEquals(175, game.calculateTotalScoreSoFar());
        assertTrue(game.isGameOver());
    }

    /**
     * Tests calculation when the last frame is strike.
     */
    @Test
    void calculateTotalScoreSoFar_LastIsStrike() {
        Frame frame1 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(10));
        Frame frame3 = frameFactory.createFrame(new Throw(10));
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        Frame frame5 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame6 = frameFactory.createFrame(new Throw(2), new Throw(5));
        Frame frame7 = frameFactory.createFrame(new Throw(2), new Throw(4));
        Frame frame8 = frameFactory.createFrame(new Throw(1), new Throw(9));
        Frame frame9 = frameFactory.createFrame(new Throw(10));
        Frame frame10 = frameFactory.createFrame(new Throw(10));

        frame1.setNextFrame(frame2);
        frame2.setNextFrame(frame3);
        frame3.setNextFrame(frame4);
        frame4.setNextFrame(frame5);
        frame5.setNextFrame(frame6);
        frame6.setNextFrame(frame7);
        frame7.setNextFrame(frame8);
        frame8.setNextFrame(frame9);
        frame9.setNextFrame(frame10);

        game.addFrame(frame1);
        game.addFrame(frame2);
        game.addFrame(frame3);
        game.addFrame(frame4);
        game.addFrame(frame5);
        game.addFrame(frame6);
        game.addFrame(frame7);
        game.addFrame(frame8);
        game.addFrame(frame9);
        game.addFrame(frame10);
        frame10.addBonusThrow(new Throw(5), new Throw(4));

        assertEquals(184, game.calculateTotalScoreSoFar());
        assertTrue(game.isGameOver());
    }

    /**
     * Tests when all frames are spares.
     */
    @Test
    void calculateTotalScoreSoFar_AllSpares() {
        Frame prevFrame = null;

        for (int i = 0; i < 10; i++) {
            Frame frame = frameFactory.createFrame(new Throw(5), new Throw(5));

            if (prevFrame != null)
                prevFrame.setNextFrame(frame);

            prevFrame = frame;

            game.addFrame(frame);
        }

        game.getPlayedFramesList().get(9).addBonusThrow(new Throw(7));

        assertEquals(152, game.calculateTotalScoreSoFar());
    }

    /**
     * Tests when all frames are spares.
     */
    @Test
    void calculateTotalScoreSoFar_AllStrikes() {
        Frame prevFrame = null;

        for (int i = 0; i < 10; i++) {
            Frame frame = frameFactory.createFrame(new Throw(10));

            if (prevFrame != null)
                prevFrame.setNextFrame(frame);

            prevFrame = frame;

            game.addFrame(frame);
        }

        assertFalse(game.isGameOver());

        game.getPlayedFramesList().get(9).addBonusThrow(new Throw(10), new Throw(10));

        assertTrue(game.isGameOver());
        assertEquals(300, game.calculateTotalScoreSoFar());
    }

    /**
     * Tests new frame registration.
     */
    @Test
    void addFrame_WhenFailure() {
        Frame frame = frameFactory.createFrame(new Throw(4), new Throw(5));
        assertEquals(FrameType.FAILURE, frame.getFrameType());

        game.addFrame(frame);
        assertEquals(1, game.getPlayedFramesList().size());
    }

    /**
     * Checks if the game is over when played frames are less than 10.
     */
    @Test
    void isGameOver_WhenLessThan10_ThenFalse() {
        Frame frame = frameFactory.createFrame(new Throw(4), new Throw(5));
        game.addFrame(frame);
        assertFalse(game.isGameOver());
    }
}