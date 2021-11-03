import java.util.concurrent.Semaphore;

public class Writer extends Thread {
    
    private Resource r;
    private Semaphore roomEmpty;
    private Semaphore turnstile;

    public Writer(Resource resource, Semaphore roomEmpty, Semaphore turnstile) {
        this.r = resource;
        this.roomEmpty = roomEmpty;
        this.turnstile = turnstile;
    }
    
    @Override
    public void run() {

        while(true) {

            try { turnstile.acquire(); } 
            catch(InterruptedException ie) { }

            try { roomEmpty.acquire(); } 
            catch(InterruptedException ie) { }
            
            //Incrementa recurso
            this.r.incr();

            turnstile.release();
            
            roomEmpty.release();
        }
        
    }
}