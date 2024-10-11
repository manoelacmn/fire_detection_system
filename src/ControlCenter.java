public class ControlCenter implements Runnable {
    private static char[][] forest;

    public ControlCenter(char[][] forest) {
        ControlCenter.forest = forest;
    }

    @Override
    public void run() {
        while (true) {
            // Central monitorando bordas e tomando ações
        }
    }

    public static void reportFire(int x, int y) {
        System.out.println("Central de Controle: Incêndio reportado no nó da borda (" + x + ", " + y + ")");
        // Lógica para combate ao incêndio
    }
}
