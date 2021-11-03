import java.util.concurrent.Semaphore;

public class UnisexBathroom { 

    static final int NF = 10;
    static final int NM = 10;

    public static void main(String[] args) {
        
        Semaphore empty = new Semaphore(1);
        Lightswitch maleSwitch = new Lightswitch();
        Lightswitch femaleSwitch = new Lightswitch();
        Semaphore maleMultiplex = new Semaphore(3);
        Semaphore femaleMultiplex = new Semaphore(3);

        //NW = Number of Womans
        for(int i = 0; i < NF; i++) {
            (new Female(femaleSwitch, femaleMultiplex, empty)).start();
        }

        //NM = Number of Mens
        for(int i = 0; i < NM; i++) {
            (new Male(maleSwitch, maleMultiplex, empty)).start();
        }
    }

}