package de.shao.menu;

import java.io.Serializable;

public class Profil implements Serializable {

    private int skinsUnlocked = 0;
    private int aquiredPercentage = 0;
    private String name = "";

    public Profil(String username){
        name = username;
    }
}
