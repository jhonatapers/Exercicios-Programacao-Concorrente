package unissex.Banheiro;

import java.util.LinkedHashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import unissex.funcionario.genero;
import unissex.funcionario.func;

public class Banheiro {
    // Capacidade do banheiro
    private static final int Cabine = 5;
    private static Banheiro instance = new Banheiro(Cabine);

    // genero usando
    private genero atual;

    // capacidade do banheiro
    private int capacidade;

    // lista dos func do banheiro
    private LinkedHashSet<func> funcionario;

    // trancar porta
    private Lock trancar = new ReentrantLock();

    public Banheiro(int capacidade) {
        this.capacidade = capacidade;
        this.atual = genero.NENHUM;
        this.funcionario = new LinkedHashSet<>();
    }

    public static Banheiro getInstance() {
        return instance;
    }

    public void addUser(func func) {
        this.trancar.lock();
        try {
            // se for a primeira pessoa a entrar no banheiro
            if (this.vazio()) {
                this.atual = func.getGen();
            }

            // Verifique se o banheiro não está cheio
            if (!this.cheio() && !this.funcionario.contains(func) && getatual().equals(func.getGen())) {
                // Adicione a pessoa
                if (this.funcionario.add(func)) {
                    System.out.println(func.getName() + "entrou no banheiro");
                }
                // Verifique se o banheiro está cheio
                if (this.cheio()) {
                    System.out.println("O banheiro esta lotado");
                }
            }
        } finally {
            this.trancar.unlock();
        }
    }

    public void removeUser(func func) {
        this.trancar.lock();
        try {
            // Verifique se o banheiro não está vazio
            if (!this.vazio()) {
                if (this.funcionario.remove(func)) {
                    System.out.println(func.getName() + "saiu do banheiro");
                }
                // Verifique se o banheiro está vazio
                if (this.vazio()) {
                    System.out.println("O banheiro esta vazio");
                    this.atual = genero.NENHUM;
                }
            }
        } finally {
            this.trancar.unlock();
        }
    }

    public boolean aliviando(func func) {
        return this.funcionario.contains(func);
    }

    // banheiro esta cheio
    public boolean cheio() {
        return this.capacidade == this.funcionario.size();
    }

    // banheiro esta vazio
    public boolean vazio() {
        return this.funcionario.isEmpty();
    }

    // qual o genero atual do funcionario
    public genero getatual() {
        return this.atual;
    }

    // ================================== Sobrepor =================================
    @Override
    public String toString() {
        return "Banheiro{" + "Genero Atual = " + this.atual + ", capacidade = " + this.capacidade
                + ", numero de usuarios = " + this.funcionario.size() + '}';
    }
}
