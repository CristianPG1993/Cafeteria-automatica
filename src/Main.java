public class Main {
    public static void main(String[] args) {

        Deposito deposito = new Deposito();

        Thread cafetera = new Thread(new Cafetera(deposito,20));

        Thread p1 = new Thread(new Profesor(deposito,"Profesora Ana",6));
        Thread p2 = new Thread(new Profesor(deposito,"Profesor Luis",7));
        Thread p3 = new Thread(new Profesor(deposito,"Profesora Marta",7));

        cafetera.start();

        p1.start();
        p2.start();
        p3.start();

        try{
            cafetera.join();
            p1.join();
            p2.join();
            p3.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido");
        }

        System.out.println("Total de cafés producidos: " + deposito.getTotalCafesProducidos());
        System.out.println("Simulación de la cafetería completada correctamente.");
    }
}
