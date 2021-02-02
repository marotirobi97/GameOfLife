public class Simulation {

    private int[][] grid;

    public Simulation(int[][] grid) {
        this.grid = grid;
    }

    public  void printOutGrid(int[][] grid) {
        System.out.println("Grid:");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println(" ");
        }
        System.out.println("\r\n");
    }

    public void gameOfLife() {
        // ebben mentem a változtatásokat,így az eredeti felálláson nem változtatok és így nem befolyásolja be a születéseket és halálozásokat
        int[][] newGrid = new int[this.grid.length][this.grid.length];

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                //jelenlegi cella kiszervezése
                int currentCell = this.grid[i][j];
                //szomszédok megszámolása
                int neighboursOfTheCurrentCell = numberOfAliveNeighbours(i,j, grid.length);

                if(currentCell == 1){
                    //A sejt elpusztul, ha kettőnél kevesebb (elszigetelődés), vagy háromnál több (túlnépesedés) szomszédja van.
                    if(neighboursOfTheCurrentCell < 2 || neighboursOfTheCurrentCell > 3){
//                        this.setDead(i,j);
                        newGrid[i][j] = 0;
                    }
                    if(neighboursOfTheCurrentCell == 3 || neighboursOfTheCurrentCell == 2){
                        newGrid[i][j] = 1;
                    }
                }else{
                    //Új sejt születik minden olyan cellában, melynek környezetében pontosan három sejt található.
                    if(neighboursOfTheCurrentCell == 3){
//                        this.setAlive(i,j);
                        newGrid[i][j] = 1;
                    }
                }
            }
        }
        //Eredmény kiíratása
        this.printOutGrid(newGrid);
    }

    private int numberOfAliveNeighbours(int i, int j,int limit) {
        int numberOfAliveNeighbours = 0;
        // az adott cella szomszédjainak a koordinátái kiszervezve a könnyebb olvashatóság kedvéért
        int a = i - 1;
        int b = i + 1;
        int c = j - 1;
        int d = j + 1;

        /*
        Jobb esetben az adott vizsgált elemünket cellák veszik körül így semmi sem akadályozza meg a vizsgálást, de ha pl. a bal sarokban
        lenne akkor figyelnünk kell rá hogy
        ne lépjen ki a tömből, különben ArrayIndexOutOfBoundsException kapunk.

     i ----->
     j  |----------|----------|----------|
     |  |[i-1][j-1]| [i][j-1] |[i+1][j-1]|
     |  |----------|----------|----------|
     v  | [i-1][j] |  [i][j]  | [i+1][j] |
        |----------|----------|----------|
        |[i-1][j+1]| [i][j+1] |[i+1][j+1]|
        |----------|----------|----------|
         */

        // j-1 nagyobb vagy egyenlő mint 0 akkor menjen bele ebben az if ágba (tehát a tömbön belül van)
        if (c >= 0) {
            //szintén ha tömbön belül van i-1
            if (a >= 0) {
                //hozzáadom az adott cella értékét (hha 0 akkor mintha semmi történt volna, ha 1-es van ott akkor a szomszédok számát növeli egyel mivel az élő cellákat is 1-el jelöljük)
                numberOfAliveNeighbours += this.grid[a][c];
            }
            //középső felső elem
            numberOfAliveNeighbours += this.grid[i][c];
            //ha nincs kint a tömbön akkor fusson erre az ágra
            if (b < limit) {
                numberOfAliveNeighbours += this.grid[b][c];
            }
        }

        //középső réteg ellenőrzése
        if (a >= 0) {
            numberOfAliveNeighbours += this.grid[a][j];
        }
        if (b < limit) {
            numberOfAliveNeighbours += this.grid[b][j];
        }

        //alsó réteg ellenőrzése
        if (d < limit) {
            if (a >= 0) {
                numberOfAliveNeighbours += this.grid[a][d];
            }
            numberOfAliveNeighbours += this.grid[i][d];
            if (b < limit) {
                numberOfAliveNeighbours += this.grid[b][d];
            }
        }

        return numberOfAliveNeighbours;
    }
}
