package Barriers;

public class Main {
    public static void main(String[] args) {
       int nrInteracoes = 5;
       int N = 20; //Matriz NxN
       int threads = (int)Math.pow(N, 2); //Threads necessárias para a matriz (1 thread por célula)

       Barrier barrier = new Barrier(threads + 1);//Acrescenta +1 para o 'ExibeMatriz'
       Matriz matriz = new Matriz(N,barrier); 

       //Imprime o conteúdo da matriz em cada interação, na fase de leitura.
       (new ExibeMatriz(matriz,barrier,nrInteracoes)).start(); 

       for (int i=0; i< threads; i++) {
           (new CalculaMediaMatriz(matriz.getMatriz(), barrier, i,nrInteracoes)).start();	
       }

    }
}
