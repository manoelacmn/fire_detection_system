import java.util.Random;

public class Main {
    public static final int SIZE = 30; // Tamanho da floresta 30x30
    public static volatile char[][] forest = new char[SIZE][SIZE]; // Matriz da floresta

    public static void main(String[] args) throws InterruptedException {
        initializeForest();

        SensorNode[][] sensors = new SensorNode[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sensors[i][j] = new SensorNode(i, j, forest);
                new Thread(sensors[i][j]).start();
            }
        }

        ControlCenter controlCenter = new ControlCenter(forest);
        new Thread(controlCenter).start();

        FireGenerator fireGenerator = new FireGenerator(forest);
        new Thread(fireGenerator).start();

        // Loop para visualizar a matriz
        while (true) {
            printForest();
            Thread.sleep(3000); // Atualiza a visualização a cada 3 segundos
        }
    }

    private static void initializeForest() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Math.random() > 0.2) {
                    forest[i][j] = 'T'; // Célula monitorada por um nó sensor ativo
                } else {
                    forest[i][j] = '-'; // Área livre
                }
            }
        }
    }

    private static void printForest() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }
}
