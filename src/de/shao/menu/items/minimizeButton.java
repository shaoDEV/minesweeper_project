package de.shao.menu.items;

import de.shao.menu.blueprint.MenuObject;

import javax.swing.*;
import java.awt.*;


public class minimizeButton extends MenuObject {

    Frame masterFrame;

    public minimizeButton(int x, int y, int width, int height, String image, String imageHovered, JFrame masterFrame) {
        super(x, y, width, height, image, imageHovered);
        this.masterFrame = masterFrame;
    }

    @Override
    protected void objectAction() {
        masterFrame.setState(Frame.ICONIFIED);
    }
}
