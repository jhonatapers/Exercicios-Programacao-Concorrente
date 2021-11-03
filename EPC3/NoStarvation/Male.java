import java.util.concurrent.Semaphore;

public class Male extends Thread {
    
    private Lightswitch maleSwitch;
    private Semaphore maleMultiplex;
    private Semaphore empty;

    private Semaphore turnstile;
    
    public Male(Lightswitch maleSwitch, Semaphore maleMultiplex, Semaphore empty, Semaphore turnstile) {
        this.maleSwitch = maleSwitch;
        this.maleMultiplex = maleMultiplex;
        this.empty = empty;
        this.turnstile = turnstile;
    }

    @Override
    public void run() {
        while(true) {

            try { turnstile.acquire(); } 
            catch(InterruptedException ie) { }

            maleSwitch.lock(empty);

            turnstile.release();
            
            try { maleMultiplex.acquire(); } 
            catch(InterruptedException ie) { }

            System.out.println("Homem ENTROU no banheiro");

            maleMultiplex.release();
            System.out.println("Homem SAIU no banheiro");

            maleSwitch.unlock(empty);          
        }
    }

}
