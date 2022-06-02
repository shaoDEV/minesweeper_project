package de.shao.gameRefactor;

import de.shao.driver.PictureController;

public class GameRunner {
    public static void main(String[] args) {
        new GameFrame(954,912, PictureController.getPictureController(1, 48));
    }
}
