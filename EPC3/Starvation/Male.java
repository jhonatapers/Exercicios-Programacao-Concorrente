import java.util.concurrent.Semaphore;

public class Male extends Thread {
    
    private Lightswitch maleSwitch;
    private Semaphore maleMultiplex;
    private Semaphore empty;
    
    public Male(Lightswitch maleSwitch, Semaphore maleMultiplex, Semaphore empty) {
        this.maleSwitch = maleSwitch;
        this.maleMultiplex = maleMultiplex;
        this.empty = empty;
    }

    @Override
    public void run() {
        while(true) {
            maleSwitch.lock(empty);

            try { maleMultiplex.acquire(); } 
            catch(InterruptedException ie) { }

            System.out.println("Homem ENTROU no banheiro");

            maleMultiplex.release();
            System.out.println("Homem SAIU no banheiro");

            maleSwitch.unlock(empty);          
        }
    }

}
