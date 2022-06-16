package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ProfilFadeScene extends MenuScenes {

    private boolean fadeIn = true;
    private int firstX = 0, secondX = 0, thirdX = 0;
    private int firstY = 0, secondY = 0, thirdY = 0;
    private final int STEPSPEED = 12;
    SystemResources systemResources;

    ProfilFadeScene(boolean fadeIn, SystemResources systemResources) {
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
        this.systemResources = systemResources;
    }

    @Override
    boolean drawScene(Graphics2D graphics2D) {
        //region FadeIn
        if (fadeIn) {
            //FadeIN von links
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

                graphics2D.setColor(new Color(58, 254, 245));
                graphics2D.fillRect(121, firstY, 290, 773 - firstY);
                graphics2D.fillRect(491, firstY, 290, 773 - firstY);
                graphics2D.fillRect(861, firstY, 290, 773 - firstY);

                graphics2D.setColor(new Color(62, 78, 92));
                graphics2D.fillRect(123, firstY, 286, 773 - firstY);
                graphics2D.fillRect(493, firstY, 286, 773 - firstY);
                graphics2D.fillRect(863, firstY, 286, 773 - firstY);
            }
        }
        //endregion

        //region FadeOut
        if (!fadeIn) {
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
                graphics2D.setColor(new Color(58, 254, 245));
                graphics2D.fillRect(121, firstY, 290, 773 - firstY);
                graphics2D.fillRect(491, firstY, 290, 773 - firstY);
                graphics2D.fillRect(861, firstY, 290, 773 - firstY);

                graphics2D.setColor(new Color(62, 78, 92));
                graphics2D.fillRect(123, firstY, 286, 773 - firstY);
                graphics2D.fillRect(493, firstY, 286, 773 - firstY);
                graphics2D.fillRect(863, firstY, 286, 773 - firstY);
            }


        }
        //endregion

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, 773, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, 773, null);

        graphics2D.drawImage(systemResources.getSystemImage("safebar_1"), firstX, firstY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_2"), secondX, secondY, null);
        graphics2D.drawImage(systemResources.getSystemImage("safebar_3"), thirdX, thirdY, null);

        if ((fadeIn && firstY == 164) || (!fadeIn && firstX == -345)) isSceneActive = false;
        return isSceneActive;
    }

    @Override
    void mouseInteraction(MouseEvent mouseEvent) {

    }
}
