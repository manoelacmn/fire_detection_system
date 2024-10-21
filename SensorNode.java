public class SensorNode implements Runnable {
    private final int row;
    private final int col;
    private final char[][] forest;

    public SensorNode(int row, int col, char[][] forest) {
        this.row = row;
        this.col = col;
        this.forest = forest;
    }

    @Override
    public void run() {
        while (isMonitoring()) {
            synchronized (forest) {
                if (isBurning()) {
                    detectAndReportFire();
                    propagateFireToNeighbors();
                    markAsBurned();
                }
            }
            sleep(1000);
        }
    }

    private boolean isMonitoring() {
        return true;
    }

    private boolean isBurning() {
        return forest[row][col] == '@';
    }

    private void detectAndReportFire() {
        System.out.println("Fire detected at (" + row + ", " + col + ")");
        if (isOnForestEdge()) {
            ControlCenter.reportFire(row, col);
        }
    }

    private void propagateFireToNeighbors() {
        if (row > 0 && forest[row - 1][col] == 'T') forest[row - 1][col] = '@';
        if (row < Main.GRID_SIZE - 1 && forest[row + 1][col] == 'T') forest[row + 1][col] = '@';
        if (col > 0 && forest[row][col - 1] == 'T') forest[row][col - 1] = '@';
        if (col < Main.GRID_SIZE - 1 && forest[row][col + 1] == 'T') forest[row][col + 1] = '@';
    }

    private void markAsBurned() {
        forest[row][col] = '/';
    }

    private boolean isOnForestEdge() {
        return row == 0 || row == Main.GRID_SIZE - 1 || col == 0 || col == Main.GRID_SIZE - 1;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
