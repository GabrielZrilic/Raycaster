import java.awt.Color;

public class Raycasting {
    public static int numberOfRays;
    private static int distanceOfView = 6;

    public static Ray[] createUnitVectors() {
        Ray[] rays = new Ray[numberOfRays];
        for (int i = 0; i < numberOfRays; i++)
            rays[i] = new Ray((double) numberOfRays, (double) i);
        return rays;
    }

    public static RayLines[] dda(Ray[] rays) {
        RayLines[] lineHeights = new RayLines[numberOfRays];
        for (int i = 0; i < rays.length; i++) {
            int mapX = (int) (Camera.x);
            int mapY = (int) (Camera.y);

            double sideDistX;
            double sideDistY;

            double deltaDistX = Math
                    .sqrt(1 + (rays[i].dirY * rays[i].dirY)
                            / (rays[i].dirX * rays[i].dirX));
            double deltaDistY = Math
                    .sqrt(1 + (rays[i].dirX * rays[i].dirX)
                            / (rays[i].dirY * rays[i].dirY));
            double perpWallDist;

            double stepX = 0, stepY = 0;
            boolean hit = false;
            Side side = null;

            if (rays[i].dirX < 0) {
                stepX = -1;
                sideDistX = (Camera.x - mapX) * deltaDistX;
            } else {
                stepX = 1;
                sideDistX = (mapX + 1 - Camera.x) * deltaDistX;
            }
            if (rays[i].dirY < 0) {
                stepY = -1;
                sideDistY = (Camera.y - mapY) * deltaDistY;
            } else {
                stepY = 1;
                sideDistY = (mapY + 1 - Camera.y) * deltaDistY;
            }

            while (!hit) {
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = Side.EAST_WEST;
                } else {
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = Side.NORTH_SOUTH;
                }
                if (mapY < 0 || mapY >= Constants.map.length || mapX < 0 || mapX >= Constants.map.length
                        || Constants.map[mapY][mapX] == true)
                    hit = true;
            }

            Color color;
            int height = 0;
            int brightness = 0;

            if (side == Side.EAST_WEST) {
                perpWallDist = (sideDistX - deltaDistX);
                height = (int) (Constants.windowHeight / perpWallDist);
                brightness = (int) ((distanceOfView * 255) / sideDistX);
                if (brightness > 255)
                    brightness = 255;
                color = new Color(brightness, brightness, brightness);
            } else {
                perpWallDist = (sideDistY - deltaDistY);
                height = (int) (Constants.windowHeight / perpWallDist);
                brightness = (int) ((distanceOfView * 255) / sideDistY);
                if (brightness > 200)
                    brightness = 200;
                color = new Color(brightness, brightness, brightness);
            }

            lineHeights[i] = new RayLines(height, color);
        }

        return lineHeights;
    }
}

class Ray {
    public double dirX;
    public double dirY;

    public Ray(double w, double z) {
        double cameraX = 2 * z / w - 1;
        dirX = Camera.dirX + Camera.planeX * cameraX;
        dirY = Camera.dirY + Camera.planeY * cameraX;
    }
}

enum Side {
    NORTH_SOUTH,
    EAST_WEST
}