package de.shao.menu.items;

import de.shao.menu.blueprint.MenuObject;

public class CloseButton extends MenuObject {

    public CloseButton(int x, int y, int width, int height, String image, String imageHovered){
        super(x, y, width, height, image, imageHovered);
    }
    @Override
    protected void objectAction() {
        System.exit(0);
    }
}
