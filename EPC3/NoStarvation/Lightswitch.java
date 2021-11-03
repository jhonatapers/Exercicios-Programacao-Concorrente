import java.util.concurrent.Semaphore;

public class Lightswitch {
    int counter;
    Semaphore mutex;

    public Lightswitch() {
        counter = 0;
        mutex = new Semaphore(1);
    }

    public void lock(Semaphore semaphore) {        
        try { mutex.acquire(); } 
        catch(InterruptedException ie) { }

        counter ++;

        if(counter == 1) {
            try { semaphore.acquire(); } 
            catch(InterruptedException ie) { }
        }

        mutex.release();
    }

    public void unlock(Semaphore semaphore) { 
        try { mutex.acquire(); } 
        catch(InterruptedException ie) { }

        counter--;

        if(counter == 0) {
            semaphore.release();
        }

        mutex.release();
    }
}