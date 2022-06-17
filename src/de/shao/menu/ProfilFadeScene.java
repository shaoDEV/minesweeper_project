package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProfilFadeScene extends MenuScenes {

    private boolean fadeIn = true;
    private boolean fadeComplete = false;
    private int firstX = 0, secondX = 0, thirdX = 0;
    private int firstY = 0, secondY = 0, thirdY = 0;
    private final int STEPSPEED = 12;
    SystemResources systemResources;

    private Rectangle firstProfilArea, secondProfilArea, thirdProfilArea;
    private ProfilController profilController = null;
    private int selectedProfilID = 0;


    ProfilFadeScene(SystemResources systemResources) {
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

        firstProfilArea = new Rectangle(148,240, 236, 508);
        secondProfilArea = new Rectangle(518,240, 236, 508);
        thirdProfilArea = new Rectangle(888,240, 236, 508);

        this.systemResources = systemResources;

        profilController = new ProfilController();
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {
        if (fadeComplete){
            if (firstProfilArea.contains(mouseEvent.getPoint())){
                if (profilController.getProfilByID(1) != null) {
                    selectedProfilID = 1;
                    fadeComplete = false;
                    fadeIn = false;
                } else {
                    profilController.createProfil("Torsten", 1);
                }
            }
            if (secondProfilArea.contains(mouseEvent.getPoint())){
                if (profilController.getProfilByID(2) != null) {
                    selectedProfilID = 2;
                    fadeComplete = false;
                    fadeIn = false;
                }
            }
            if (thirdProfilArea.contains(mouseEvent.getPoint())){
                if (profilController.getProfilByID(3) != null) {
                    selectedProfilID = 3;
                    fadeComplete = false;
                    fadeIn = false;
                }
            }
        }

    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        if (!fadeComplete){
            if (fadeIn) fadeIn(graphics2D);
            else fadeOut(graphics2D);

            if ((fadeIn && firstY == 164) || (!fadeIn && firstX == -345)) fadeComplete = true;
        }

        if (fadeComplete){
            drawractangles(graphics2D);

            graphics2D.setColor(new Color(58, 254, 245));

            graphics2D.drawRect(148,240, 236, 508);
            graphics2D.drawRect(149,241, 234, 506);

            graphics2D.drawRect(518,240, 236, 508);
            graphics2D.drawRect(519,241, 234, 506);

            graphics2D.drawRect(888,240, 236, 508);
            graphics2D.drawRect(889,241, 234, 506);

        }

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, 773, null);

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, firstY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, secondY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, thirdY, null);

        if (!fadeIn && fadeComplete) isSceneActive = false;
        return isSceneActive;
    }



    private void fadeIn(Graphics2D graphics2D){
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

    private void fadeOut(Graphics2D graphics2D){
        //FadeOut nach links
        if (firstY == 722) {
            if (secondX <= 95){
                if (firstX > -345) firstX -= STEPSPEED;
                else firstX = -345;
            }

            if (thirdX <= 465){
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

        if (firstY < 722){
            drawractangles(graphics2D);
        }
    }

    private void drawractangles(Graphics2D graphics2D){
        graphics2D.setColor(new Color(58, 254, 245));
        graphics2D.fillRect(121, firstY, 290, 773 - firstY);
        graphics2D.fillRect(491, firstY, 290, 773 - firstY);
        graphics2D.fillRect(861, firstY, 290, 773 - firstY);

        graphics2D.setColor(new Color(62, 78, 92));
        graphics2D.fillRect(123, firstY, 286, 773 - firstY);
        graphics2D.fillRect(493, firstY, 286, 773 - firstY);
        graphics2D.fillRect(863, firstY, 286, 773 - firstY);
    }

    public MenuScenes getFollowUpScene(){
        return new MainMenuScene(selectedProfilID);
    }
}
