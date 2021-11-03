package DiningPhilosophers;

import java.util.concurrent.Semaphore;

public class Philosophe extends Thread {
    
	private Semaphore rFork, lFork;

    public Philosophe(Semaphore rFork, Semaphore lFork){		
        this.rFork = rFork;
        this.lFork = lFork;
    }

    @Override
    public void run() {
        while(true) {
            try{ rFork.acquire(); }
            catch(InterruptedException ie){}    

            try{ lFork.acquire(); }
            catch(InterruptedException ie){}

            System.out.println("Janta");		

            rFork.release();
            lFork.release();
        }
    }
}
