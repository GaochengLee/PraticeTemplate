package algo;

/**
 * @author 李高丞
 * @version 1.0
 */
public class Singleton {
    public static volatile Singleton instance;

    private Singleton(){

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
