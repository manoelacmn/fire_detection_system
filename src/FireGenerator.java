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
            int x = random.nextInt(Main.SIZE);
            int y = random.nextInt(Main.SIZE);

            synchronized (forest) {
                if (forest[x][y] == 'T') {
                    forest[x][y] = '@'; // Gera fogo em uma célula
//                    System.out.println("Fogo iniciado em (" + x + ", " + y + ")");
                }
            }

            try {
                Thread.sleep(3000); // Espera 3 segundos antes de gerar o próximo fogo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
