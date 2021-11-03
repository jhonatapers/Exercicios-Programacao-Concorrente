import java.util.concurrent.Semaphore;

public class ReadersAndWirters {

    static final int NW = 50;

    static final int NR = 500;

    public static void main(String[] args) {

        Resource resource = new Resource();
        
        Lightswitch readSwitch = new Lightswitch();

        Semaphore roomEmpty = new Semaphore(1);

        Semaphore turnstile = new Semaphore(1);

        //NW = Number of Writers
        for(int i = 0; i < NW; i++) {
            (new Writer(resource, roomEmpty, turnstile)).start();
        }

        //NR = Number of Readers
        for(int i = 0; i < NR; i++) {
            (new Reader(resource, readSwitch, roomEmpty, turnstile)).start();
        }
                
    }

}








