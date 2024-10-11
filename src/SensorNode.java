public class SensorNode implements Runnable {
    private int x, y;
    private char[][] forest;
    private boolean running = true;

    public SensorNode(int x, int y, char[][] forest) {
        this.x = x;
        this.y = y;
        this.forest = forest;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (forest) {
                if (forest[x][y] == '@') { // Detecta fogo
                    System.out.println("Incêndio detectado no nó (" + x + ", " + y + ")");
                    notifyNeighbors();
                    if (isOnEdge()) {
                        ControlCenter.reportFire(x, y);
                    }
                    forest[x][y] = '/'; // Marca célula como queimada após detectar
                }
            }
            try {
                Thread.sleep(1000); // Checa a cada 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyNeighbors() {
        // Comunicação com os vizinhos para propagar a mensagem de incêndio
        if (x > 0 && forest[x - 1][y] == 'T') {
            forest[x - 1][y] = '@'; // Propaga incêndio para cima
        }
        if (x < Main.SIZE - 1 && forest[x + 1][y] == 'T') {
            forest[x + 1][y] = '@'; // Propaga incêndio para baixo
        }
        if (y > 0 && forest[x][y - 1] == 'T') {
            forest[x][y - 1] = '@'; // Propaga incêndio para a esquerda
        }
        if (y < Main.SIZE - 1 && forest[x][y + 1] == 'T') {
            forest[x][y + 1] = '@'; // Propaga incêndio para a direita
        }
    }

    private boolean isOnEdge() {
        return x == 0 || x == Main.SIZE - 1 || y == 0 || y == Main.SIZE - 1;
    }
}
