public class Raycasting {
    public static int numberOfRays = 0;
    private static int dirScalar = 10;
    private static int cameraScalar = 100;
    
    public static double[][] endPointsOfVectors(double dirX, double dirY, double planeX, double planeY) {
        double[][] endPoints = new double[cameraScalar][2];

        for(int cameraXScalar = 0, i = 0; cameraXScalar < cameraScalar; cameraXScalar++, i++) {
            double cameraX = 2 * cameraXScalar / (double) (cameraScalar) - 1;
            double rayDirX = (dirX + planeX * cameraX)*cameraScalar;
            double rayDirY = (dirY + planeY * cameraX)*cameraScalar;

            endPoints[i] = new double[]{rayDirX, rayDirY};
        }

        return endPoints;
    }

    public static int getXField(int x) {
        return x/Constants.cellWidth;
    }

    public static int getYField(int y) {
        return y/Constants.cellHeight;
    }
}
