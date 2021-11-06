package Barriers;

public class CalculaMediaMatriz extends Thread{

    private int[][] matriz;
    private int media;
    private Barrier barrier;
    private int nrCelula; //Numero desta thread/celula
    private int celulaAtual = 0; //Numero da celula atual
    private int count = 1; // Contador de interações
    private int nrInteracoes;

    public CalculaMediaMatriz(int[][] matriz, Barrier barrier, int nrCelula, int nrInteracoes){
        this.matriz = matriz;
        this.barrier = barrier;
        this.nrCelula = nrCelula;
        this.nrInteracoes = nrInteracoes;
    }

    @Override
    public void run(){
        
        while(this.count <= this.nrInteracoes) {
            //FASE 1 ****** INICIO
            try{
                barrier.arrive();
                this.celulaAtual = 0;
                for (int linha = 0; linha < this.matriz.length; linha++) {
                    for (int coluna = 0; coluna < this.matriz[linha].length; coluna++) {
                        
                        if(celulaAtual == nrCelula){
                            //Verifica se a posição não está nas bordas: Possui os 4 vizinhos.
                            if ((linha > 0 && linha < this.matriz.length - 1) && (coluna > 0 && coluna < this.matriz[linha].length - 1)){
                                int oeste = this.matriz[linha][coluna-1];
                                int leste = this.matriz[linha][coluna+1];
                                int norte = this.matriz[linha-1][coluna];
                                int sul = this.matriz[linha+1][coluna];
                                this.media = (oeste + leste + norte + sul) / 4;
                            }
    
                            //Primeira linha
                            if(linha == 0){
                                if((coluna == 0)){
                                    int leste = this.matriz[linha][coluna+1];
                                    int sul = this.matriz[linha+1][coluna];
                                    this.media = (leste + sul) / 2;
                                }
        
                                if((coluna == this.matriz.length - 1)){
                                    int oeste = this.matriz[linha][coluna-1];
                                    int sul = this.matriz[linha+1][coluna];
                                    this.media = (oeste + sul) / 2;
                                }
    
                                if((coluna != 0 && coluna != this.matriz.length - 1)){
                                    int oeste = this.matriz[linha][coluna-1];
                                    int leste = this.matriz[linha][coluna+1];
                                    int sul = this.matriz[linha+1][coluna];
                                    this.media = (oeste + leste + sul) / 3;
                                }
                            }
    
                            //Ultima linha
                            if(linha == this.matriz.length - 1){
                                if((coluna == 0)){
                                    int leste = this.matriz[linha][coluna+1];
                                    int norte = this.matriz[linha-1][coluna];
                                    this.media = (leste + norte) / 2;
                                }
        
                                if((coluna == this.matriz.length - 1)){
                                    int oeste = this.matriz[linha][coluna-1];
                                    int norte = this.matriz[linha-1][coluna];
                                    this.media = (oeste + norte) / 2;
                                }
    
                                if((coluna != 0 && coluna != this.matriz.length - 1)){
                                    int oeste = this.matriz[linha][coluna-1];
                                    int leste = this.matriz[linha][coluna+1];
                                    int norte = this.matriz[linha-1][coluna];
                                    this.media = (oeste + leste + norte) / 3;
                                }
                            }
    
                            //Não está na primeira e nem na ultima linha.
                            if(linha != 0 && linha != this.matriz.length - 1){
                                if((coluna == 0)){
                                    int leste = this.matriz[linha][coluna+1];
                                    int norte = this.matriz[linha-1][coluna];
                                    int sul = this.matriz[linha+1][coluna];
                                    this.media = (leste + norte + sul) / 3;
                                }
    
                                if((coluna == this.matriz.length - 1)){
                                    int oeste = this.matriz[linha][coluna-1];
                                    int norte = this.matriz[linha-1][coluna];
                                    int sul = this.matriz[linha+1][coluna];
                                    this.media = (oeste + norte + sul) / 3;
                                }
                            }
                        }
                        celulaAtual++;
                    }
                }
            }
            catch(InterruptedException ie){

            }
            //FASE 1 ****** FIM
            
            
            //FASE 2 ****** INICIO
            try{
                barrier.leave();
                this.celulaAtual = 0;
                for (int linha = 0; linha < this.matriz.length; linha++) {
                    for (int coluna = 0; coluna < this.matriz[linha].length; coluna++) {
                        if(this.celulaAtual == nrCelula){
                            this.matriz[linha][coluna] = this.media;
                            //System.out.printf("FASE 2 - ESCRITA => LINHA: %d | COLUNA: %d | MÉDIA: %d \n",linha,coluna,this.media);
                        }
                        this.celulaAtual++;
                    }
                }
                this.count++;
            }
            catch(InterruptedException ie){

            }
            //FASE 2 ****** FIM
        }
    }
}