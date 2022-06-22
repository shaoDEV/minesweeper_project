package de.shao.menu;

import de.shao.driver.SystemResources;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class CreateNewProfilScene {

    private final int ICON_COUNT = 5;

    private int position = 0;
    private boolean fadeIn = true;
    private boolean animationComplete = false;
    private boolean profilCreationDone = false;

    SystemResources systemResources;
    ProfilController profilController;
    int selectedProfilID;

    private int iconID;
    private String name = "";

    private Rectangle iconInteractionRectangle;
    private Rectangle createProfileInteractionRectangle;


    public CreateNewProfilScene(SystemResources systemResources, ProfilController profilController, int selectedProfil) {
        this.systemResources = systemResources;
        this.profilController = profilController;
        this.selectedProfilID = selectedProfil;

        iconID = 0;
        position = 1157;
        iconInteractionRectangle = new Rectangle(428, 386, 129, 129);
        createProfileInteractionRectangle = new Rectangle(769, 485, 70, 19);
    }

    public boolean drawScene(Graphics2D graphics2D) {
        if (fadeIn) {
            if (position > 323) position -= 10;
            else {
                position = 323;
                animationComplete = true;
            }
        }
        if (!fadeIn) {
            if (position < 1200) position += 10;
            else {
                profilCreationDone = true;
            }
        }

        graphics2D.drawImage(systemResources.getSystemImage("neuesProfil"), 373, position, null);
        if (animationComplete) {
            graphics2D.drawImage(systemResources.getSystemImage("profilIcon_" + iconID), 429, 387, null);
            graphics2D.setColor(new Color(58, 254, 245));
            graphics2D.setFont(new Font("Minecraft", Font.PLAIN, 30));
            graphics2D.drawString(name, 596,469);
        }

        return profilCreationDone;
    }

    public void mouseInteraction(MouseEvent mouseEvent) {
        if (animationComplete) {
            if (iconInteractionRectangle.contains(mouseEvent.getPoint())) {
                if (iconID < ICON_COUNT) iconID = iconID + 1;
                else iconID = 0;
            }
            if (createProfileInteractionRectangle.contains(mouseEvent.getPoint())) {
                createProfil();
            }
        }
    }

    private void createProfil(){
        profilController.createProfil( name, iconID, selectedProfilID);
        fadeIn = false;
        animationComplete = false;
    }

    public void keyInteraction(KeyEvent keyEvent) {
        if (animationComplete) {
            if (keyEvent.getKeyCode() >= 65 && keyEvent.getKeyCode() <= 90) name = name + keyEvent.getKeyChar();
            if (keyEvent.getKeyChar() == 8 && name.length() > 0){
                StringBuilder stringBuilder = new StringBuilder(name);
                stringBuilder.deleteCharAt(name.length() -1);
                name = stringBuilder.toString();
            }
            if(keyEvent.getKeyCode() == 10){
                createProfil();
            }
        }
    }
}
