import java.util.concurrent.Semaphore;

public class Writer extends Thread {
    
    private Resource r;
    private Semaphore roomEmpty;

    public Writer(Resource resource, Semaphore roomEmpty) {
        this.r = resource;
        this.roomEmpty = roomEmpty;
    }
    
    @Override
    public void run() {

        while(true) {

            //Tenta pegar recurso;
            try { roomEmpty.acquire(); } 
            catch(InterruptedException ie) { }
            
            //Incrementa recurso
            this.r.incr();

            //Solta recurso
            roomEmpty.release();
        }
        
    }
}