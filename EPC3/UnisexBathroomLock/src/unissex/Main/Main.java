/*
 * GNU License.
 */
package unissex.Main;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import unissex.Banheiro.Banheiro;
import unissex.funcionario.genero;
import unissex.funcionario.func;


public class Main {

    
    public static void main(String[] args) {
        // Banheiro
        Banheiro banheiro = Banheiro.getInstance();
        //lista de funcionario 
        List<func> Func = new ArrayList<>();
        //criar funcionarios
        for (int i = 0; i < ((new Random()).nextInt(10) + 15); i++) {
            
            if (new Random().nextInt(2) == 0) {
                // Cria func homem
                Func.add(new func(("Homem id: " + i + " "), genero.HOMEM, banheiro));
            } else {
                // Cria func mulher
                Func.add(new func(("MULHER id: " + i + " "), genero.MULHER, banheiro));
            }
        }
        // Stats persons
        Func.stream().map((func) -> new Thread(func)).forEach((t) -> {
            t.start();
        });
        // 
        Func.stream().map((func) -> new Thread(func)).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
