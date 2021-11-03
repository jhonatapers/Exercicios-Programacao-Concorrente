import java.util.concurrent.Semaphore;

public class Female extends Thread {
    
    private Lightswitch femaleSwitch;
    private Semaphore femaleMultiplex;
    private Semaphore empty;

    private Semaphore turnstile;
    
    public Female(Lightswitch femaleSwitch, Semaphore femaleMultiplex, Semaphore empty, Semaphore turnstile) {
        this.femaleSwitch = femaleSwitch;
        this.femaleMultiplex = femaleMultiplex;
        this.empty = empty;

        this.turnstile = turnstile;
    }
    
    @Override
    public void run() {
        while(true) {

            try { turnstile.acquire(); } 
            catch(InterruptedException ie) { }

            femaleSwitch.lock(empty);

            turnstile.release();
            

            try { femaleMultiplex.acquire(); } 
            catch(InterruptedException ie) { }

            System.out.println("Mulher ENTROU no banheiro");

            femaleMultiplex.release();
            System.out.println("Mulher SAIU no banheiro");

            femaleSwitch.unlock(empty);          
        }
    }

}
