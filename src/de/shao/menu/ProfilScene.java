package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ProfilScene extends MenuScenes {

    private final int SKIN_COUNT = 2;

    private boolean fadeIn = true;
    private boolean fadeComplete = false;
    private boolean createProfileInteraction = false;

    private boolean createNewProfileFlag = false;

    private int firstX = 0, secondX = 0, thirdX = 0;
    private int firstY = 0, secondY = 0, thirdY = 0;
    private final int STEPSPEED = 12;
    SystemResources systemResources;

    private Rectangle firstProfilArea, secondProfilArea, thirdProfilArea;
    private Rectangle deleteFirstProfile, deleteSecondProfile, deleteThirdProfile;

    private ProfilController profilController = null;
    private int selectedProfilID = 0;

    private CreateNewProfilScene createNewProfilScene;


    ProfilScene(SystemResources systemResources) {
        this.fadeIn = fadeIn;
        if (fadeIn) {

            firstX = -345;
            secondX = -345;
            thirdX = -345;

            firstY = 722;
            secondY = 722;
            thirdY = 722;
        } else {
            firstX = 95;
            secondX = 465;
            thirdX = 835;

            firstY = 164;
            secondY = 164;
            thirdY = 164;
        }

        firstProfilArea = new Rectangle(148, 240, 236, 508);
        secondProfilArea = new Rectangle(518, 240, 236, 508);
        thirdProfilArea = new Rectangle(888, 240, 236, 508);


        this.systemResources = systemResources;

        profilController = new ProfilController();
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
        if (!createProfileInteraction) {
            if (fadeComplete && !createNewProfileFlag) {
                if (firstProfilArea.contains(mouseEvent.getPoint())) {
                    if (profilController.getProfilByID(1) != null) {
                        if (deleteFirstProfile != null && deleteFirstProfile.contains(mouseEvent.getPoint())){
                            profilController.deleteProfil(1);
                            profilController = new ProfilController();
                            return;
                        }
                        selectedProfilID = 1;
                        fadeComplete = false;
                        fadeIn = false;
                    } else {
                        createNewProfilScene = new CreateNewProfilScene(systemResources, profilController, 1);
                        createProfileInteraction = true;
                    }
                }
                if (secondProfilArea.contains(mouseEvent.getPoint())) {
                    if (profilController.getProfilByID(2) != null) {
                        if (deleteSecondProfile != null && deleteSecondProfile.contains(mouseEvent.getPoint())){
                            profilController.deleteProfil(2);
                            profilController = new ProfilController();
                            return;
                        }
                        selectedProfilID = 2;
                        fadeComplete = false;
                        fadeIn = false;
                    } else {
                        createNewProfilScene = new CreateNewProfilScene(systemResources, profilController, 2);
                        createProfileInteraction = true;
                    }
                }
                if (thirdProfilArea.contains(mouseEvent.getPoint())) {
                    if (profilController.getProfilByID(3) != null) {
                        if (deleteThirdProfile != null && deleteThirdProfile.contains(mouseEvent.getPoint())){
                            profilController.deleteProfil(3);
                            profilController = new ProfilController();
                            return;
                        }
                        selectedProfilID = 3;
                        fadeComplete = false;
                        fadeIn = false;
                    } else {
                        createNewProfilScene = new CreateNewProfilScene(systemResources, profilController, 3);
                        createProfileInteraction = true;
                    }
                }
            }
        } else {
            createNewProfilScene.mouseInteraction(mouseEvent);
        }

    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        if (!fadeComplete) {
            if (fadeIn) fadeIn(graphics2D);
            else fadeOut(graphics2D);

            if ((fadeIn && firstY == 164) || (!fadeIn && firstX == -345)) fadeComplete = true;
        }

        if (fadeComplete) {
            drawractangles(graphics2D);

            graphics2D.setColor(new Color(58, 254, 245));

            graphics2D.drawRect(148, 240, 236, 508);
            graphics2D.drawRect(149, 241, 234, 506);

            graphics2D.drawRect(518, 240, 236, 508);
            graphics2D.drawRect(519, 241, 234, 506);

            graphics2D.drawRect(888, 240, 236, 508);
            graphics2D.drawRect(889, 241, 234, 506);

            graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 20));

            //Profil 1
            if (profilController.getProfilByID(1) != null) {
                graphics2D.drawRect(201, 293, 129, 129);
                Profil tempProfil = profilController.getProfilByID(1);
                graphics2D.drawImage(systemResources.getSystemImage("profilIcon_" + tempProfil.getIconID()), 202, 294, null);
                graphics2D.drawString("Profil 1", 203, 280);
                graphics2D.drawString(tempProfil.getName(), 200, 450);
                graphics2D.drawString("Skins: " + tempProfil.getSkinsUnlocked() + " / " + SKIN_COUNT, 200, 500);
                graphics2D.drawString("" + tempProfil.getAquiredPercentage() + "%", 200, 540);
                graphics2D.drawImage(systemResources.getSystemImage("deleteProfil"), 214, 722, null);
                if (deleteFirstProfile == null) deleteFirstProfile = new Rectangle(214,722,164,20);
            } else {
                graphics2D.drawString("NEUES PROFIL", 190, 450);
            }
            //Profil 2
            if (profilController.getProfilByID(2) != null) {
                graphics2D.drawRect(571, 293, 129, 129);
                Profil tempProfil = profilController.getProfilByID(2);
                graphics2D.drawImage(systemResources.getSystemImage("profilIcon_" + tempProfil.getIconID()), 572, 294, null);
                graphics2D.drawString("Profil 2", 573, 280);
                graphics2D.drawString(tempProfil.getName(), 570, 450);
                graphics2D.drawString("Skins: " + tempProfil.getSkinsUnlocked() + " / " + SKIN_COUNT, 570, 500);
                graphics2D.drawString("" + tempProfil.getAquiredPercentage() + "%", 570, 540);
                graphics2D.drawImage(systemResources.getSystemImage("deleteProfil"), 584, 722, null);
                if (deleteSecondProfile == null) deleteSecondProfile = new Rectangle(584,722,164,20);
            } else {
                graphics2D.drawString("NEUES PROFIL", 560, 450);
            }
            //Profil 3
            if (profilController.getProfilByID(3) != null) {
                graphics2D.drawRect(941, 293, 129, 280);
                Profil tempProfil = profilController.getProfilByID(3);
                graphics2D.drawImage(systemResources.getSystemImage("profilIcon_" + tempProfil.getIconID()), 942, 294, null);
                graphics2D.drawString("Profil 3", 943, 273);
                graphics2D.drawString(tempProfil.getName(), 940, 450);
                graphics2D.drawString("Skins: " + tempProfil.getSkinsUnlocked() + " / " + SKIN_COUNT, 940, 500);
                graphics2D.drawString("" + tempProfil.getAquiredPercentage() + "%", 940, 540);
                graphics2D.drawImage(systemResources.getSystemImage("deleteProfil"), 954, 722, null);
                if (deleteThirdProfile == null) deleteThirdProfile = new Rectangle(954,722,164,20);
            } else {
                graphics2D.drawString("NEUES PROFIL", 930, 450);
            }
        }

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, 773, null);

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, firstY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, secondY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, thirdY, null);

        if (createProfileInteraction) drawCreateNewProfile(graphics2D);

        if (!fadeIn && fadeComplete) isSceneActive = false;
        return isSceneActive;
    }

    private void drawCreateNewProfile(Graphics2D graphics2D) {
        if (createNewProfilScene.drawScene(graphics2D)) {
            profilController = new ProfilController();
            createProfileInteraction = false;
        }
    }


    private void fadeIn(Graphics2D graphics2D) {
        if (firstX < 95) firstX += STEPSPEED;
        else firstX = 95;

        if (secondX < 465) secondX += STEPSPEED;
        else secondX = 465;

        if (thirdX < 835) thirdX += STEPSPEED;
        else thirdX = 835;

        //FadeUP der Felder
        if (thirdX == 835) {
            if (firstY > 164) firstY -= STEPSPEED;
            else firstY = 164;

            if (secondY > 164) secondY -= STEPSPEED;
            else secondY = 164;

            if (thirdY > 164) thirdY -= STEPSPEED;
            else thirdY = 164;

            drawractangles(graphics2D);
        }
    }

    private void fadeOut(Graphics2D graphics2D) {
        //FadeOut nach links
        if (firstY == 722) {
            if (secondX <= 95) {
                if (firstX > -345) firstX -= STEPSPEED;
                else firstX = -345;
            }

            if (thirdX <= 465) {
                if (secondX > -345) secondX -= STEPSPEED;
                else secondX = -345;
            }

            if (thirdX > -345) thirdX -= STEPSPEED;
            else thirdX = -345;
        }


        //FadeDown der Felder
        if (firstY < 722) firstY += STEPSPEED;
        else firstY = 722;

        if (secondY < 722) secondY += STEPSPEED;
        else secondY = 722;

        if (thirdY < 722) thirdY += STEPSPEED;
        else thirdY = 722;

        if (firstY < 722) {
            drawractangles(graphics2D);
        }
    }

    private void drawractangles(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.fillRect(121, firstY, 290, 773 - firstY);
        graphics2D.fillRect(491, firstY, 290, 773 - firstY);
        graphics2D.fillRect(861, firstY, 290, 773 - firstY);

        graphics2D.setColor(new Color(62, 78, 92));
        graphics2D.fillRect(123, firstY, 286, 773 - firstY);
        graphics2D.fillRect(493, firstY, 286, 773 - firstY);
        graphics2D.fillRect(863, firstY, 286, 773 - firstY);
    }

    public MenuScenes getFollowUpScene() {
        return new MainMenuScene(selectedProfilID);
    }
}
