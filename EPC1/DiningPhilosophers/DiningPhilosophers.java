package DiningPhilosophers;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    
    private static final int FIL = 5;

    public static void main(String[] args) {
        //noSolution();
        solution1();
        //solution2();
        //solution3();
    }

    private static void noSolution(){
        Semaphore[] forks = new Semaphore[FIL];
        for (int i=0; i< FIL; i++) {
            forks[i]= new Semaphore(1); 
        }

        for (int i=0; i< FIL; i++) {
            (new Philosophe(forks[i], forks[(i+1)%(FIL)])).start();	
        }
	}

    //Retira um filosofo
	private static void solution1(){

        Semaphore[] forks = new Semaphore[FIL];
        for (int i=0; i< FIL; i++) {
            forks[i]= new Semaphore(1); 
        }

        for (int i=0; i< FIL-1; i++) {
            (new Philosophe(forks[i], forks[(i+1)%(FIL)])).start();	
        }
	}

	//Último filoso troca de mão
	private static void solution2(){

        Semaphore[] forks = new Semaphore[FIL];
        for (int i=0; i< FIL; i++) {
            forks[i]= new Semaphore(1); 
        }

        for (int i=0; i< FIL; i++) {            
            if(i == FIL-1) {
                (new Philosophe(forks[0], forks[FIL-1])).start();	
            }else
            {
                (new Philosophe(forks[i], forks[(i+1)%(FIL)])).start();	
            }
        }
	}
    
	//Adiciona um garfo Extra para o ultimo filosofo
	private static void solution3(){

        Semaphore[] forks = new Semaphore[FIL+1];
        for (int i=0; i< FIL+1; i++) {
            forks[i]= new Semaphore(1); 
        }

        for (int i=0; i< FIL; i++) {            
            if(i == FIL-1) {
				(new Philosophe(forks[i], forks[i+1])).start();	
            }else
            {
                (new Philosophe(forks[i], forks[(i+1)%(FIL)])).start();	
            }
        }
	}


}
