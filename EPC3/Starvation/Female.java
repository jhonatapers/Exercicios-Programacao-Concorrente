import java.util.concurrent.Semaphore;

public class Female extends Thread {
    
    private Lightswitch femaleSwitch;
    private Semaphore femaleMultiplex;
    private Semaphore empty;
    
    public Female(Lightswitch femaleSwitch, Semaphore femaleMultiplex, Semaphore empty) {
        this.femaleSwitch = femaleSwitch;
        this.femaleMultiplex = femaleMultiplex;
        this.empty = empty;
    }
    
    @Override
    public void run() {
        while(true) {
            femaleSwitch.lock(empty);

            try { femaleMultiplex.acquire(); } 
            catch(InterruptedException ie) { }

            System.out.println("Mulher ENTROU no banheiro");

            femaleMultiplex.release();
            System.out.println("Mulher SAIU no banheiro");

            femaleSwitch.unlock(empty);          
        }
    }

}
