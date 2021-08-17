package bowling.controller;

import bowling.domain.Player;
import bowling.domain.common.Name;
import bowling.domain.frame.Frames;
import bowling.domain.frame.PitchResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void main(final String[] args) {
        final Player player = Player.of(Name.of(InputView.inputPlayerName()));
        final Frames frames = Frames.of();

        while (!frames.isEnd()) {
            final String pitch = InputView.inputPitch(frames.current());
            frames.pitch(PitchResult.of(pitch));
            ResultView.printResultBoard(player, frames);
        }
    }
}
