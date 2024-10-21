public class ControlCenter implements Runnable {
    private static char[][] forest;

    public ControlCenter(char[][] forest) {
        ControlCenter.forest = forest;
    }

    @Override
    public void run() {
        while (true) {
            // Monitorando incêndios reportados pelos nós sensores
        }
    }

    public static void reportFire(int row, int col) {
        System.out.println("Control Center: Fire reported at (" + row + ", " + col + ")");
    }
}
