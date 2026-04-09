public class Profesor implements Runnable{

    private Deposito deposito;
    private String nombre;
    private int cafesBebidos;
    private int cafesConsumidos;

    public Profesor(Deposito deposito, String nombre, int cafesBebidos){
        this.deposito = deposito;
        this.nombre = nombre;
        this.cafesBebidos = cafesBebidos;
    }

    @Override
    public void run() {

        for (int i = 0; i < cafesBebidos; i++){

            boolean retirado = deposito.retirarCafe(nombre);

            if(!retirado){
                break;
            }

            cafesConsumidos++;

            try {
                Thread.sleep((int) (Math.random() * 3000 + 1000));
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido mientras tomaba el café.");
            }
        }
        System.out.println(nombre + " ha consumido " + cafesConsumidos + " cafés.");

    }
}
