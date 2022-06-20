package de.shao.menu;

import de.shao.driver.PictureController;
import de.shao.driver.SystemResources;
import de.shao.gameRefactor.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenuScene extends MenuScenes {

    private int profilID;
    private int selectedSkinID = 1;
    private final int SKINCOUNT = 3;
    private String customBombCount = "";
    private int customBombCountNummeric = 0;

    private SystemResources systemResources;
    private ProfilController profilController;
    private MenuScenes followUpScene;

    private Rectangle customGame10, customGame16, customGame25;
    private Rectangle skinSelectorRight, skinSelectorLeft;
    private Rectangle classicGame10, classicGame16, classicGame25;
    private Rectangle backToProfile;

    public MainMenuScene(int profilID, SystemResources systemResources) {
        this.profilID = profilID;
        this.systemResources = systemResources;
        this.profilController = new ProfilController();

        initAreas();
    }

    private void initAreas() {
        //SkinSelectors
        skinSelectorLeft = new Rectangle(244, 255, 28, 32);
        skinSelectorRight = new Rectangle(466, 255, 28, 32);

        //CustomGame
        customGame10 = new Rectangle(766, 662, 345, 50);
        customGame16 = new Rectangle(766, 722, 345, 50);
        customGame25 = new Rectangle(766, 780, 345, 50);

        //Classic Game
        classicGame10 = new Rectangle(91, 345, 337, 99);
        classicGame16 = new Rectangle(457, 345, 337, 99);
        classicGame25 = new Rectangle(824, 345, 337, 99);

        //Back to Profile
        backToProfile = new Rectangle(1059,126,145,41);
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
        graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 26));
        graphics2D.drawString("Einfach - 10x10", 155, 402);
        if (classicGame16.contains(mouseEvent)) {
            graphics2D.drawImage(systemResources.getSystemImage("buttonHover"), 457, 345, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("button"), 457, 345, null);
        graphics2D.drawString("Mittel - 16x16", 540, 402);
        if (classicGame25.contains(mouseEvent)) {
            graphics2D.drawImage(systemResources.getSystemImage("buttonHover"), 824, 345, null);
        } else graphics2D.drawImage(systemResources.getSystemImage("button"), 824, 345, null);
        graphics2D.drawString("Schwer - 25x25", 890, 402);
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

        //region Skinselector
        //Selector
        if (skinSelectorLeft.contains(mouseEvent)) graphics2D.drawImage(systemResources.getSystemImage("arrowLeftHover"),244,255,null);
        else graphics2D.drawImage(systemResources.getSystemImage("arrowLeft"),244,255,null);
        if (skinSelectorRight.contains(mouseEvent)) graphics2D.drawImage(systemResources.getSystemImage("arrowRightHover"),466,255,null);
        else graphics2D.drawImage(systemResources.getSystemImage("arrowRight"),466,255,null);
        graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 30));
        graphics2D.drawString("Skin:", 327, 250);
        graphics2D.drawString(skinSetName(selectedSkinID), 288,283);
        //endregion

        //Back To Profile
        if (backToProfile.contains(mouseEvent)) graphics2D.drawImage(systemResources.getSystemImage("backToProfileHover"), 1059, 126, null);
        else graphics2D.drawImage(systemResources.getSystemImage("backToProfile"), 1059, 126, null);

        return isSceneActive;
    }

    private String skinSetName(int skinSetID){
        switch (skinSetID){
            case 0 -> {return "Classic";}
            case 1 -> {return "Weltraum";}
            case 2 -> {return "Antike";}
        }
        return "Fehler";
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
        if (skinSelectorRight.contains(mouseEvent.getPoint())){
            if (selectedSkinID < SKINCOUNT - 1) selectedSkinID += 1;
            else selectedSkinID = 0;
        }
        if (skinSelectorLeft.contains(mouseEvent.getPoint())){
            if (selectedSkinID > 0) selectedSkinID -= 1;
            else selectedSkinID = SKINCOUNT -1;
        }
        if (classicGame10.contains(mouseEvent.getPoint())){
            if (SystemResources.isGameActive == false){
                SystemResources.isGameActive = true;
                new GameFrame(systemResources.getFieldWidthBySize(10),
                        systemResources.getFieldHeightBySize(10),
                        PictureController.getPictureController(selectedSkinID,48),
                        10,
                        10);
            }

        }
        if (classicGame16.contains(mouseEvent.getPoint())){
            if (SystemResources.isGameActive == false){
                SystemResources.isGameActive = true;
                new GameFrame(systemResources.getFieldWidthBySize(16),
                        systemResources.getFieldHeightBySize(16),
                        PictureController.getPictureController(selectedSkinID,48),
                        40,
                        16);
            }

        }
        if (classicGame25.contains(mouseEvent.getPoint())){
            if (SystemResources.isGameActive == false){
                SystemResources.isGameActive = true;
                new GameFrame(systemResources.getFieldWidthBySize(25),
                        systemResources.getFieldHeightBySize(25),
                        PictureController.getPictureController(selectedSkinID,48),
                        150,
                        25);
            }

        }
        if (backToProfile.contains(mouseEvent.getPoint())){
            followUpScene = new ProfilScene(systemResources);
            isSceneActive = false;
        }
    }

    @Override
    void keyInteraction(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() >= 48 && keyEvent.getKeyCode() <= 57) customBombCount += keyEvent.getKeyChar();
        if (keyEvent.getKeyChar() == 8 && customBombCount.length() > 0){
            StringBuilder stringBuilder = new StringBuilder(customBombCount);
            stringBuilder.deleteCharAt(customBombCount.length() -1);
            customBombCount = stringBuilder.toString();
        }
    }

    @Override
    MenuScenes getFollowUpScene() {
        if (followUpScene != null){
            return followUpScene;
        }
        return null;
    }

    private void loadHighscores(Graphics2D graphics2D) {

    }
}
