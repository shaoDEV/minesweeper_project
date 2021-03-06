package de.shao.menu;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Diese Klasse handelt wie Profile abgespeichert und gelesen werden sollen und stellt dieses einfach zugänglich zur Verfügung
 */
public class ProfilController {

    private Profil firstProfil = null, secondProfil = null, thirdProfil = null;
    private final boolean DEBUG = true;

    public static final String SPIELER_SPEICHER_PFAD = System.getProperty("user.home") + "\\starsweeper\\savegame\\";

    public ProfilController() {
        loadProfiles();
    }

    private void loadProfiles() {
        File file;
        //Try to load First Profil
        file = new File(SPIELER_SPEICHER_PFAD + "profil_1.bin");
        if (file.exists()){
            try (
                    FileInputStream profil_1_file = new FileInputStream(file);
                    BufferedInputStream profil_1_bufferedStream = new BufferedInputStream(profil_1_file);
                    ObjectInputStream profil_1_objectStream = new ObjectInputStream(profil_1_bufferedStream);

            ) {
                firstProfil = (Profil) profil_1_objectStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //Try to load Second Profil
        file = new File(SPIELER_SPEICHER_PFAD + "profil_2.bin");
        if (file.exists()){
            try (
                    FileInputStream profil_2_file = new FileInputStream(file);
                    BufferedInputStream profil_2_bufferedStream = new BufferedInputStream(profil_2_file);
                    ObjectInputStream profil_2_objectStream = new ObjectInputStream(profil_2_bufferedStream);

            ) {
                secondProfil = (Profil) profil_2_objectStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //Try to load Third Profil
        file = new File(SPIELER_SPEICHER_PFAD + "profil_3.bin");
        if (file.exists()){
            try (
                    FileInputStream profil_3_file = new FileInputStream(file);
                    BufferedInputStream profil_3_bufferedStream = new BufferedInputStream(profil_3_file);
                    ObjectInputStream profil_3_objectStream = new ObjectInputStream(profil_3_bufferedStream);

            ) {
                thirdProfil = (Profil) profil_3_objectStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createProfil(String name, int profilIconID, int profilID) {
        try (
                FileOutputStream fileOut = new FileOutputStream(SPIELER_SPEICHER_PFAD + "profil_" + profilID + ".bin");
                BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
                ObjectOutputStream objectOut = new ObjectOutputStream(bufferedOut);
        ) {
            Profil newCreatedProfil = new Profil(name, profilIconID);
            objectOut.writeObject(newCreatedProfil);
        } catch (IOException e) {
        }
    }

    public void deleteProfil(int profilID){
        File file = new File(SPIELER_SPEICHER_PFAD + "profil_" + profilID + ".bin");
        file.delete();
    }

    public Profil getProfilByID(int profilID){
        switch (profilID){
            case 1 -> {return firstProfil;}
            case 2 -> {return secondProfil;}
            case 3 -> {return thirdProfil;}
        }
        return null;
    }
}
