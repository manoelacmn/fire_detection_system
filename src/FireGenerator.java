import java.util.Random;

public class FireGenerator implements Runnable {
    private final char[][] forest;
    private final Random random = new Random();

    public FireGenerator(char[][] forest) {
        this.forest = forest;
    }

    @Override
    public void run() {
        while (true) {
            igniteRandomLocation();
            sleep(3000);
        }
    }

    private void igniteRandomLocation() {
        int row = random.nextInt(Main.GRID_SIZE);
        int col = random.nextInt(Main.GRID_SIZE);

        synchronized (forest) {
            if (forest[row][col] == 'T') {
                forest[row][col] = '@';
            }
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
