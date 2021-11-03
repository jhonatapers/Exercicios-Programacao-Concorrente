import java.util.concurrent.Semaphore;

public class ReadersAndWirters {

    static final int NW = 50;

    static final int NR = 1;

    public static void main(String[] args) {

        Resource resource = new Resource();
        
        Lightswitch readLightswitch = new Lightswitch();

        Semaphore roomEmpty = new Semaphore(1);

        //NW = Number of Writers
        for(int i = 0; i < NW; i++) {
            (new Writer(resource, roomEmpty)).start();
        }

        //NR = Number of Readers
        for(int i = 0; i < NR; i++) {
            (new Reader(resource, readLightswitch, roomEmpty)).start();
        }
                
    }

}








