package pixum.task;

import org.junit.jupiter.api.Test;
import pixum.task.entities.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for FrameFactory.
 */
class FrameFactoryTest {

    private final FrameFactory frameFactory = new FrameFactory();

    /**
     * Tests creation of FailureFrame, SpareFrame, StrikeFrame.
     */
    @Test
    void createFrame_WhenAllKinds() {
        Frame frame = frameFactory.createFrame(new Throw(4), new Throw(5));
        assertInstanceOf(FailureFrame.class, frame);
        assertEquals(FrameType.FAILURE, frame.getFrameType());
        assertEquals(9, frame.calculatePoints());

        Frame frame2 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame3 = frameFactory.createFrame(new Throw(2), new Throw(4));
        frame2.setNextFrame(frame3);
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        Frame frame5 = frameFactory.createFrame(new Throw(3), new Throw(6));
        frame4.setNextFrame(frame5);
        Frame frame6 = frameFactory.createFrame(new Throw(10));
        Frame frame7 = frameFactory.createFrame(new Throw(10));
        frame6.setNextFrame(frame7);
        Frame frame8 = frameFactory.createFrame(new Throw(3), new Throw(6));
        frame7.setNextFrame(frame8);

        assertInstanceOf(SpareFrame.class, frame2);
        assertEquals(FrameType.SPARE, frame2.getFrameType());
        assertEquals(12, frame2.calculatePoints());

        assertInstanceOf(FailureFrame.class, frame3);
        assertEquals(FrameType.FAILURE, frame3.getFrameType());
        assertEquals(6, frame3.calculatePoints());

        assertInstanceOf(StrikeFrame.class, frame4);
        assertEquals(FrameType.STRIKE, frame4.getFrameType());
        assertEquals(19, frame4.calculatePoints());

        assertEquals(23, frame6.calculatePoints());
    }

    /**
     * Tests creation of FailureFrame.
     */
    @Test
    void createFrame_WhenFailureFrame() {
        Frame frame = frameFactory.createFrame(new Throw(4), new Throw(5));
        assertInstanceOf(FailureFrame.class, frame);
        assertEquals(9, frame.calculatePoints());
    }

    /**
     * Tests creation with invalid number of throws.
     */
    @Test
    void createFrame_WhenInvalidThrows_ThrowsException() {
        assertThrows(IllegalArgumentException.class, frameFactory::createFrame);
        assertThrows(IllegalArgumentException.class, () ->
                frameFactory.createFrame(new Throw(4), new Throw(5), new Throw(5)));
    }
}