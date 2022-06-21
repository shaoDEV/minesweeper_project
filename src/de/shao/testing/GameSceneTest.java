package de.shao.testing;

import de.shao.driver.PictureController;
import de.shao.gameRefactor.GameScene;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameSceneTest {

    GameScene gameSceneToTest;

    @BeforeEach
    void setUp() {
        gameSceneToTest = new GameScene(new Point(66,72), new Point(732,702), 10,10, PictureController.getPictureController());
    }

    @Test
    void sceneInteraction() {
        gameSceneToTest.sceneInteraction(new Point(50,50), 1);
        Assertions.assertEquals(true, gameSceneToTest.isTestingGetPointClicked());
    }

    @Test
    void isActiveGame() {
        gameSceneToTest.setActiveGame(false);
        Assertions.assertEquals(false, gameSceneToTest.isActiveGame());
        gameSceneToTest.setActiveGame(true);
        Assertions.assertEquals(true, gameSceneToTest.isActiveGame());
    }

    @Test
    void isGameWon() {
        gameSceneToTest.setGameWon(true);
        Assertions.assertEquals(true, gameSceneToTest.isGameWon());
    }

    @Test
    void getTestingGetBombsPlaced() {
        Assertions.assertEquals(10, gameSceneToTest.getTestingGetBombsPlaced());
    }

    @Test
    void getTestingGetNumbersPlaced(){
        Assertions.assertEquals(100, gameSceneToTest.getTestingGetNumbersPlaced());
    }
}