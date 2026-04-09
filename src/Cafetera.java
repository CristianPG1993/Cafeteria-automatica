public class Cafetera implements Runnable{

    private Deposito deposito;
    private int totalCafes;

    public Cafetera(Deposito deposito, int totalCafes) {
        this.deposito = deposito;
        this.totalCafes = totalCafes;
    }



    @Override
    public void run() {

        for (int i = 0; i < totalCafes; i++){

            try {
                Thread.sleep((int) (Math.random()* 500 + 200));
            } catch (InterruptedException e) {
                System.out.println("La cafetera fue interrumpida");
                break;
            }

            deposito.depositarCafe();
        }

        deposito.marcarProduccionTerminada();
    }
}
