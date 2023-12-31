public class Camera {
    private static double rotationValue = 0.04;
    private static double movementValue = 0.1;

    public static double dirX = 0, dirY = 1, planeX = 0.75, planeY = 0;
    public static double x = 0.5;
    public static double y = 0.5;
    public static Movement movement = Movement.STILL;
    public static Rotation rotation = Rotation.STILL;

    public static void updateLocation() {
        if (movement == Movement.FORWARD)
            move(movementValue);
        else if (movement == Movement.BACK)
            move(-movementValue);
    }

    public static void updateRotation() {
        if (rotation == Rotation.RIGHT)
            rotate(rotationValue);
        else if (rotation == Rotation.LEFT)
            rotate(-rotationValue);
    }

    private static void move(double scalar) {
        double newX = x + dirX * scalar;
        double newY = y + dirY * scalar;

        if (newX > 0 && newX < Constants.gridWidth - 1 && !Constants.map[(int) newY][(int) newX])
            x = newX;
        if (newY > 0 && newY < Constants.gridHeight - 1 && !Constants.map[(int) newY][(int) newX])
            y = newY;
    }

    private static void rotate(double phi) {
        double initDirX = dirX, initDirY = dirY;
        double initPlaneX = planeX, initPlaneY = planeY;

        double cos_phi = Math.cos(phi), sin_phi = Math.sin(phi);

        dirX = initDirX * cos_phi - initDirY * sin_phi;
        dirY = initDirX * sin_phi + initDirY * cos_phi;

        planeX = initPlaneX * cos_phi - initPlaneY * sin_phi;
        planeY = initPlaneX * sin_phi + initPlaneY * cos_phi;
    }
}
