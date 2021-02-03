public class Main {

    public static void main(String[] args) {
        // 5x5-ös rács
        int[][] grid = {
                {1,0,1,0,1,},
                {0,0,1,0,1,},
                {0,1,0,0,1,},
                {1,0,1,0,1,},
                {0,1,1,0,1,},
        };

        Simulation sm = new Simulation(grid);
        sm.gameOfLife();
    }
}
