package PACKAGE_NAME;

/**
 * Created by Khiem on 1/9/2017.
 */
public class Arc {
    private static Arc ourInstance = new Arc();

    public static Arc getInstance() {
        return ourInstance;
    }

    private Arc() {
    }
}
