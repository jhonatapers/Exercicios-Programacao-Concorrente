package Barriers;

public class Matriz{

    private int tamanhoMatriz;
    private int[][] matriz;

    public Matriz(int tamanhoMatriz, Barrier barrier){
        this.tamanhoMatriz = tamanhoMatriz;
        this.matriz = new int[tamanhoMatriz][tamanhoMatriz];
        this.populaMatriz();
    }

    private void populaMatriz(){
        int max = 1000;
        int min = 0;
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = (int)Math.floor(Math.random()*(max-min+1)+min);
            }
        }
    }

    public void exibeMatriz(int interacao){
        System.out.println("\n*****************************************************");
        System.out.printf("\nESTADO ATUAL DA MATRIZ (INTERAÇÃO %d)\n",interacao);
        System.out.print("\n");
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                System.out.print("\t"+this.matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public int[][] getMatriz(){
        return this.matriz;
    }

    
}
