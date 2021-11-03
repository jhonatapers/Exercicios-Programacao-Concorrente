
package unissex.funcionario;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import unissex.Banheiro.Banheiro;
import java.util.concurrent.TimeUnit;

//=====================================================

public class func implements Runnable {

    private String nome;
    private genero gen;
    private Banheiro banheiro;
    private boolean Apertado;
    private boolean Sair;

    public func(String nome, genero gen, Banheiro banheiro) {
        this.nome = nome;
        this.gen = gen;
        this.banheiro = banheiro;
        this.Sair = false;
        this.Apertado = true;
    }

    public void usarBanheiro() {
        // para usar o banheiro ele entra no banheiro
        this.banheiro.addUser(this);

        if (this.banheiro.aliviando(this)) {
            System.out.println(this.getName() + " Está se aliviando");
            try {
                TimeUnit.SECONDS.sleep((new Random()).nextInt(1) + 1);
                this.Sair = true;
                System.out.println(getName() + "Se aliviou");
            } catch (InterruptedException ex) {
                Logger.getLogger(func.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void SairBanheiro() {
        // saiu
        this.banheiro.removeUser(this);

        this.Sair = false;
        this.Apertado = false;
    }

    // ======================== PAGA VARIAVEIS DO FUNCIONARIO =================

    // PEGA NOME
    public String getName() {
        return this.nome;
    }

    // PEGA GENERO
    public genero getGen() {
        return this.gen;
    }

    // ==================== como deve ser chamado o metodo ====================
    @Override
    public void run() {
        System.out.println(this.getName());
        // Se a pessoa precisa ir ao banheiro
        while (this.Apertado) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(func.class.getName()).log(Level.SEVERE, null, ex);
            }
            // se eles estao apertados
            if ((this.banheiro.getatual().equals(this.getGen()) || this.banheiro.getatual().equals(genero.NENHUM))
                    && !this.banheiro.cheio() && !this.banheiro.aliviando(this)) {
                this.usarBanheiro();
            }
            // se eles querem sair
            if (this.Sair) {
                this.SairBanheiro();
            }
        }
    }

    @Override
    public String toString() {
        return "Funcionario{" + "nome = " + this.nome + ", Genero = " + this.gen + '}';
    }
}
