package resources;

import java.io.InputStream;

public abstract class ResHelper {

    //Original Autor Arthur, last edited Sep 2, 2018 at 0:36
    //https://stackoverflow.com/questions/52132803/resource-loading-using-classloader

    public static InputStream getResourcenStream(String resName) {
        return ResHelper.class.getResourceAsStream("/resources/" + resName);
    }
}