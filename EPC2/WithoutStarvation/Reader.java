import java.util.concurrent.Semaphore;

public class Reader extends Thread {

    Resource resource;
    Lightswitch readSwitch;
    Semaphore roomEmpty;
    Semaphore turnstile;

    public Reader(Resource resource, Lightswitch readSwitch, Semaphore roomEmpty, Semaphore turnstile) {
        this.resource = resource;
        this.readSwitch = readSwitch;
        this.roomEmpty = roomEmpty;
        this.turnstile = turnstile;
    }
    
    @Override
    public void run() {
        while(true) {

            try { turnstile.acquire(); } 
            catch(InterruptedException ie) { }
            turnstile.release();

            readSwitch.lock(roomEmpty);
            System.out.println(resource.value());
            readSwitch.unlock(roomEmpty);            
        }
    }
}