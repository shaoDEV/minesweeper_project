package de.shao.menu;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Profil implements Serializable {

    private int skinsUnlocked = 1;
    private int aquiredPercentage = 0;
    private String name = "";
    private int iconID;
    private int profilID;

    public Profil(String username, int iconID){
        name = username;
        this.iconID = iconID;
        //https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        profilID = ThreadLocalRandom.current().nextInt(100000, 999999+1);
    }

    public String getName() {
        return name;
    }

    public int getIconID() {
        return iconID;
    }

    public int getProfilID() {
        return profilID;
    }

    public int getSkinsUnlocked() {
        return skinsUnlocked;
    }

    public int getAquiredPercentage() {
        return aquiredPercentage;
    }
}
