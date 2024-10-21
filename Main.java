public class Main {
    public static final int GRID_SIZE = 30; // Tamanho da floresta 30x30
    public static volatile char[][] forest = new char[GRID_SIZE][GRID_SIZE];

    public static void main(String[] args) throws InterruptedException {
        configureForestGrid();

        initializeSensorNodes();

        initializeControlCenter();

        initializeFireGenerator();

        continuouslyDisplayForestState();
    }

    private static void configureForestGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                forest[i][j] = (Math.random() > 0.2) ? 'T' : '-';
            }
        }
    }

    private static void initializeSensorNodes() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                new Thread(new SensorNode(i, j, forest)).start();
            }
        }
    }

    private static void initializeControlCenter() {
        new Thread(new ControlCenter(forest)).start();
    }

    private static void initializeFireGenerator() {
        new Thread(new FireGenerator(forest)).start();
    }

    private static void continuouslyDisplayForestState() throws InterruptedException {
        while (true) {
            displayForest();
            Thread.sleep(3000);
        }
    }

    private static void displayForest() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }
}
