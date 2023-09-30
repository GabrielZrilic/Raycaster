public class Constants {
    public static int windowHeight = 960;    
    public static int windowWidth = 1280;
    public static int coordinateSizeX = 1000;
    public static int coordinateSizeY = 1000;    
    public static int gridWidth = 20;
    public static int gridHeight = 20;
    public static boolean[][] map = new boolean[gridHeight][gridWidth];
    public static int cellHeight = Constants.coordinateSizeY/Constants.gridHeight;
    public static int cellWidth = Constants.coordinateSizeX/Constants.gridWidth;
    public static double twoPI = Math.PI*2;
}
