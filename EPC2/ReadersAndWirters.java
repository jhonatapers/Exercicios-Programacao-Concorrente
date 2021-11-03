import java.util.concurrent.Semaphore;

public class ReadersAndWirters {



    public static void main(String[] args) {
        withPostponement(2, 20);
    }

    private static void withPostponement(Integer NR, Integer NW) {

        //Recurso
        Counter resource = new Counter();

        //Quantidade de leitores na sala
        int readersInRoom = 0;
        
        //Semafora para exlcusao mutua
        Semaphore mutex = new Semaphore(1);
    
        //Semaforo que indica que a sala esta vazia
        Semaphore roomEmpty = new Semaphore(1);

        //NW = Number of Writers
        for(int i = 0; i < NW; i++) {
            (new Writer(resource, roomEmpty)).start();
        }

        //NR = Number of Readers
        for(int i = 0; i < NR; i++) {
            (new Reader(resource, readersInRoom, mutex, roomEmpty)).start();
        }
        
    }

    private void withoutPostponement() {
        
    }

}

class Counter {
	
	private  int n;   
	
	public Counter(){ 	
		n = 0;
	} 
		
	public void incr(){
        try{
            n++;
        }catch(Exception e) {
            n = 0;
            n++;
        }   
	} 	
	
	public int value() { 
        return n; 
    }
}

class Reader extends Thread {

    Counter r;
    int readersInRoom;
    Semaphore mutex;
    Semaphore roomEmpty;


    public Reader(Counter resource, int readersInRoom, Semaphore mutex, Semaphore roomEmpty) {
        this.r = resource;
        this.readersInRoom = readersInRoom;
        this.mutex = mutex;
        this.roomEmpty = roomEmpty;
    }
    
    @Override
    public void run() {
        while(true) {

            //Tenta pegar recurso;
            try { mutex.acquire(); } 
            catch(InterruptedException ie) { }

            readersInRoom++;

            if(readersInRoom == 1){
                try { roomEmpty.acquire(); } 
                catch(InterruptedException ie) { }
            }

            mutex.release();

            System.out.println(r.value());

            //Tenta pegar recurso;
            try { mutex.acquire(); } 
            catch(InterruptedException ie) { }
            
            readersInRoom--;
            if(readersInRoom == 0){
                roomEmpty.release(); 
            }

            mutex.release();
            
        }
    }
}

class Writer extends Thread {
    
    private Counter r;
    private Semaphore roomEmpty;

    public Writer(Counter resource, Semaphore roomEmpty) {
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
