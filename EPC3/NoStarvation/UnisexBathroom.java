import java.util.concurrent.Semaphore;

public class UnisexBathroom { 

    static final int NF = 50;
    static final int NM = 50;

    public static void main(String[] args) {
        
        Semaphore empty = new Semaphore(1);
        Lightswitch maleSwitch = new Lightswitch();
        Lightswitch femaleSwitch = new Lightswitch();
        Semaphore maleMultiplex = new Semaphore(3);
        Semaphore femaleMultiplex = new Semaphore(3);

        Semaphore turnstile = new Semaphore(1);

        //NW = Number of Womans
        for(int i = 0; i < NF; i++) {
            (new Female(femaleSwitch, femaleMultiplex, empty, turnstile)).start();
        }

        //NM = Number of Mens
        for(int i = 0; i < NM; i++) {
            (new Male(maleSwitch, maleMultiplex, empty, turnstile)).start();
        }
    }

}