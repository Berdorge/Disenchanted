public class Disenchanted {
    public static void main(String[] args) {
        Thread webAppThread = new Thread(RunWebAppKt::main);
        webAppThread.start();
        ServerKt.main(args);
    }
}
