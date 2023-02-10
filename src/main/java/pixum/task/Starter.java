package pixum.task;

import lombok.extern.slf4j.Slf4j;
import pixum.task.entities.Frame;
import pixum.task.entities.Game;
import pixum.task.entities.Throw;

/**
 * Simple demo of the score calculation with two players.
 */
@Slf4j
public class Starter {

    private final FrameFactory frameFactory = new FrameFactory();

    public static void main(String[] args) {
        Starter starter = new Starter();
        Game playerOneGame = starter.createPlayerOneGame();
        Game playerTwoGame = starter.createPlayerTwoGame();

        int playerOneScores = playerOneGame.calculateTotalScore();
        int playerTwoScores = playerTwoGame.calculateTotalScore();

        log.info("Player one has score {}", playerOneScores);
        log.info("Player two has score {}", playerTwoScores);
    }

    private Game createPlayerOneGame() {
        Game game = new Game();

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

        return game;
    }

    private Game createPlayerTwoGame() {
        Game game = new Game();

        Frame frame1 = frameFactory.createFrame(new Throw(2), new Throw(5));
        Frame frame2 = frameFactory.createFrame(new Throw(1), new Throw(4));
        Frame frame3 = frameFactory.createFrame(new Throw(9), new Throw(1));
        Frame frame4 = frameFactory.createFrame(new Throw(10));
        Frame frame5 = frameFactory.createFrame(new Throw(5), new Throw(5));
        Frame frame6 = frameFactory.createFrame(new Throw(3), new Throw(5));
        Frame frame7 = frameFactory.createFrame(new Throw(5), new Throw(4));
        Frame frame8 = frameFactory.createFrame(new Throw(1), new Throw(9));
        Frame frame9 = frameFactory.createFrame(new Throw(10));
        Frame frame10 = frameFactory.createFrame(new Throw(9), new Throw(1));

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

        return game;
    }
}