import java.util.LinkedList;
import java.util.Queue;

public class Deposito{

    private int cafesMaxDeposito = 5;
    private int cafesEnDeposito;
    private int totalCafesProducidos;
    private boolean produccionTerminada = false;
    private Queue<Thread> cola = new LinkedList<>();

    public int getTotalCafesProducidos() {
        return totalCafesProducidos;
    }

    public synchronized void depositarCafe(){

        while (cafesEnDeposito == cafesMaxDeposito){
            System.out.println("Cafetera esperando: depósito lleno (" + cafesEnDeposito + ")");
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("El proceso fue interrumpido");
                }
        }
        cafesEnDeposito++;
        totalCafesProducidos++;
        System.out.println("Cafetera prepara y deposita Cafe-" + totalCafesProducidos + ". En depósito: " + cafesEnDeposito);
        notifyAll();
    }
    public synchronized boolean retirarCafe(String nombre){

        Thread actual = Thread.currentThread();

        //Añadir el profesor a la cola
        cola.add(actual);

        while ((cafesEnDeposito == 0  && !produccionTerminada)|| cola.peek() != actual){
            if(cola.peek() != actual){
                System.out.println(nombre + " esperando turno.");
            }else {
                System.out.println(nombre + " esperando: depósito vacío.");
            }

            try{
                wait();
            }catch (InterruptedException e){
                System.out.println(nombre + " interrumpido.");
            }

        }

        if (cafesEnDeposito == 0 && produccionTerminada){
            cola.poll();
            System.out.println(nombre + ": No hay más cafés, fin de la jornada.");
            notifyAll();
            return false;
        }

        cola.poll();
        cafesEnDeposito--;
        System.out.println( nombre + " retira un café. En depósito: " + cafesEnDeposito);
        notifyAll();
        return true;
    }

    public synchronized void marcarProduccionTerminada(){
        produccionTerminada = true;
        notifyAll();
    }

}
