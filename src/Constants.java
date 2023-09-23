public class Constants {
    public static int windowHeight = 1000;    
    public static int windowWidth = 1000;
    public static int gridWidth = 20;
    public static int gridHeight = 20;
    public static boolean[][] map = new boolean[gridHeight][gridWidth];
    public static int cellHeight = Constants.windowHeight/Constants.gridHeight;
    public static int cellWidth = Constants.windowWidth/Constants.gridWidth;
    public static double twoPI = Math.PI*2;
}
