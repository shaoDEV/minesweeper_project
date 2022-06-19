package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenuScene extends MenuScenes {

    private int profilID;

    private SystemResources systemResources;
    private ProfilController profilController;

    private Rectangle customGame10, customGame16, customGame25;
    private boolean customGame10Hovered, customGame16Hovered, customGame25Hovered;
    private Rectangle skinSelectorRight, skinSelectorLeft;
    private Rectangle classicGame10, classicGame16, classicGame25;
    private boolean classicGame10Hovered, classicGame16Hovered, classicGame25Hovered;

    public MainMenuScene(int profilID, SystemResources systemResources) {
        this.profilID = profilID;
        this.systemResources = systemResources;
        this.profilController = new ProfilController();

        initAreas();
    }

    private void initAreas() {
        //SkinSelectors
        skinSelectorLeft = new Rectangle(240, 255, 32, 32);
        skinSelectorRight = new Rectangle(466, 255, 32, 32);

        //CustomGame
        customGame10 = new Rectangle(766, 662, 345, 50);
        customGame16 = new Rectangle(766, 722, 345, 50);
        customGame25 = new Rectangle(766, 780, 345, 50);

        //Classic Game
        classicGame10 = new Rectangle(91, 345, 337, 99);
        classicGame16 = new Rectangle(457, 345, 337, 99);
        classicGame25 = new Rectangle(824, 345, 337, 99);
    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        //region Highscore Frames
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 30));

        //Borders
        graphics2D.fillRect(94, 494, 178, 325);
        graphics2D.fillRect(286, 494, 178, 325);
        graphics2D.fillRect(478, 494, 178, 325);
        //Inneres Rechteck
        graphics2D.setColor(new Color(62, 78, 92));
        graphics2D.fillRect(96, 496, 174, 321);
        graphics2D.fillRect(288, 496, 174, 321);
        graphics2D.fillRect(480, 496, 174, 321);
        //Trennlinie
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.fillRect(100, 534, 165, 5);
        graphics2D.fillRect(292, 534, 165, 5);
        graphics2D.fillRect(484, 534, 165, 5);
        //Beschrifttung
        graphics2D.drawString("10x10", 100, 529);
        graphics2D.drawString("16x16", 292, 529);
        graphics2D.drawString("25x25", 484, 529);

        loadHighscores(graphics2D);
        //endregion
        //region Profil

        //Profilbild
        graphics2D.fillRect(91, 162, 130, 130);
        graphics2D.setColor(new Color(62, 78, 92));
        graphics2D.fillRect(92, 163, 128, 128);
        Profil tempProfil = profilController.getProfilByID(profilID);
        graphics2D.drawImage(systemResources.getSystemImage("profilIcon_" + tempProfil.getIconID()), 92, 163, null);
        //Profilname
        graphics2D.drawString(tempProfil.getName(), 239, 201);

        //endregion

        //Aktuelle Maus Position für Hover
        int mouseX = MouseInfo.getPointerInfo().getLocation().x - SystemResources.FramePoint.x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y - SystemResources.FramePoint.y;
        Point mouseEvent = new Point(mouseX, mouseY);

        //region newGameClassic
        if (classicGame10.contains(mouseEvent)) {
            graphics2D.drawImage(systemResources.getSystemImage("buttonHover"), 91, 345, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("button"), 91, 345, null);
        if (classicGame16.contains(mouseEvent)) {
            graphics2D.drawImage(systemResources.getSystemImage("buttonHover"), 457, 345, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("button"), 457, 345, null);
        if (classicGame25.contains(mouseEvent)) {
            graphics2D.drawImage(systemResources.getSystemImage("buttonHover"), 824, 345, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("button"), 824, 345, null);
        //endregion

        //region newGameCustom
        if (customGame10.contains(mouseEvent)){
            graphics2D.drawImage(systemResources.getSystemImage("customButtonHover"), 766, 662, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("customButton"), 766, 662, null);
        if (customGame16.contains(mouseEvent)){
            graphics2D.drawImage(systemResources.getSystemImage("customButtonHover"), 766, 722, null);
        } else  graphics2D.drawImage(systemResources.getSystemImage("customButton"), 766, 722, null);
        if (customGame25.contains(mouseEvent)){
            graphics2D.drawImage(systemResources.getSystemImage("customButtonHover"), 766, 780, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("customButton"), 766, 780, null);

        //Trenner
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.fillRect(680, 498, 529, 5);
        //Schriften
        graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 40));
        graphics2D.drawString("Benutzerdefiniertes Spiel:", 690, 540);
        graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 30));
        graphics2D.drawString("Anzahl schlechter Felder:", 690, 600);
        graphics2D.setColor(new Color(62, 78, 92));
        //Bombenanzahlfeld
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.fillRect(1070, 565, 75, 50);
        graphics2D.setColor(new Color(62, 78, 92));
        graphics2D.fillRect(1072, 567, 71, 46);
        //endregion

        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
    }

    @Override
    void keyInteraction(KeyEvent keyEvent) {
    }

    @Override
    MenuScenes getFollowUpScene() {
        return null;
    }

    private void loadHighscores(Graphics2D graphics2D) {

    }
}
