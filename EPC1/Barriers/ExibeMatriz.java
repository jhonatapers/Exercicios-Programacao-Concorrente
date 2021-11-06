package Barriers;

public class ExibeMatriz extends Thread{

    private Matriz matriz;
    private Barrier barrier;
    private int count = 1; //Contador de interações
    private int nrInteracoes;

    public ExibeMatriz(Matriz matriz, Barrier barrier, int nrInteracoes){
        this.matriz = matriz;
        this.barrier = barrier;
        this.nrInteracoes = nrInteracoes;
    }

    @Override
    public void run(){
        
        while(this.count <= nrInteracoes) {
            //FASE 1 ****** INICIO
            try{ 
                barrier.arrive();
                this.matriz.exibeMatriz(this.count);
            }
            catch(InterruptedException ie){

            }
            //FASE 1 ****** FIM
            
            //FASE 2 ****** INICIO
            try{ 
                barrier.leave();
                this.count++;
            }
            catch(InterruptedException ie){

            }
            //FASE 2 ****** FIM
        }
    }
    
}