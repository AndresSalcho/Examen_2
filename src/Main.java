

public class Main {
    public static void main(String[] args) {
        Menu m = new Menu();
        Runnable thread = m::Start;

        Thread hilo = new Thread(thread);
        hilo.start();
    }

}