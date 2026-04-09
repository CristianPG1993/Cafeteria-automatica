public class Deposito{

    private int cafesMaxDeposito = 5;
    private int cafesEnDeposito;
    private int totalCafesProducidos;
    private boolean produccionTerminada = false;


    public synchronized void depositarCafe(){

        while (cafesEnDeposito == cafesMaxDeposito){
            System.out.println("El depósito está lleno. Depósito = " + cafesEnDeposito + " cafés.");
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

        while (cafesEnDeposito == 0 && !produccionTerminada){
            System.out.println("El depósito está vacío.");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("El proceso fue interrumpido.");
            }
        }

        if ( cafesEnDeposito == 0 && produccionTerminada){
            return false;
        }
        cafesEnDeposito--;
        System.out.println( "Profesor " + nombre + " retira un café. En depósito: " + cafesEnDeposito);
        notifyAll();
        return true;
    }

    public synchronized void marcarProduccionTerminada(){
        produccionTerminada = true;
        notifyAll();
    }

}
