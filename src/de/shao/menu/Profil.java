package de.shao.menu;

import java.io.Serializable;

public class Profil implements Serializable {

    private int skinsUnlocked = 1;
    private int aquiredPercentage = 0;
    private String name = "";
    private int iconID;

    public Profil(String username, int iconID){
        name = username;
        this.iconID = iconID;
    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return iconID;
    }

    public int getSkinsUnlocked() {
        return skinsUnlocked;
    }

    public int getAquiredPercentage() {
        return aquiredPercentage;
    }
}
