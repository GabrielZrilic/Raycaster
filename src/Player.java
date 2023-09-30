public class Player {
    private static double rotationValue = 0.04;
    private static double movementValue = 0.5;
    
    public static double dirX = 0, dirY = 1, planeX = 1, planeY = 0;
    public static double x = Constants.cellWidth/2;
    public static double y = Constants.cellHeight/2;
    public static Movement movement = Movement.STILL;
    public static Rotation rotation = Rotation.STILL;

    public static void updateEndPoints() {
    }

    public static void updateLocation() {
        if(movement == Movement.FORWARD) move(movementValue);
        else if (movement == Movement.BACK) move(-movementValue);
    }

    public static void updateRotation() {
        if(rotation == Rotation.ROTATE_RIGHT) rotate(rotationValue);
        else if(rotation == Rotation.ROTATE_LEFT) rotate(-rotationValue);
    }

    private static void move(double scalar) {
        double newX = x + dirX*scalar;
        double newY = y + dirY*scalar;

        if(newX > 0 && newX < Constants.coordinateSizeX-1 && !Constants.map[(int) (newY/Constants.cellWidth)][(int) (newX/Constants.cellHeight)]) x = newX;
        if(newY > 0 && newY < Constants.coordinateSizeY-1 && !Constants.map[(int) (newY/Constants.cellWidth)][(int) (newX/Constants.cellHeight)]) y = newY;
    }
    
    private static void rotate(double phi) {
        double initDirX = dirX, initDirY = dirY;
        double initPlaneX = planeX, initPlaneY = planeY;

        double cos_phi = Math.cos(phi), sin_phi = Math.sin(phi);

        dirX = initDirX*cos_phi - initDirY*sin_phi;
        dirY = initDirX*sin_phi + initDirY*cos_phi;

        planeX = initPlaneX*cos_phi - initPlaneY*sin_phi;
        planeY = initPlaneX*sin_phi + initPlaneY*cos_phi;
    }

}
