import java.awt.Color;

public class Raycasting {
    public static int numberOfRays;

    public static Ray[] showRays() {
        Ray[] rays = new Ray[numberOfRays];
        for (int i = 0; i < numberOfRays; i++)
            rays[i] = new Ray((double) numberOfRays, (double) i);
        return rays;
    }

    public static RayLines[] dda(Ray[] rays) {
        RayLines[] lineHeights = new RayLines[numberOfRays];
        for (int i = 0; i < rays.length; i++) {
            int mapX = (int) (Player.x);
            int mapY = (int) (Player.y);

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
                sideDistX = (Player.x - mapX) * deltaDistX;
            } else {
                stepX = 1;
                sideDistX = (mapX + 1 - Player.x) * deltaDistX;
            }
            if (rays[i].dirY < 0) {
                stepY = -1;
                sideDistY = (Player.y - mapY) * deltaDistY;
            } else {
                stepY = 1;
                sideDistY = (mapY + 1 - Player.y) * deltaDistY;
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

            if (side == Side.EAST_WEST) {
                perpWallDist = (sideDistX - deltaDistX);
                color = new Color(255, 0, 0);
            } else {
                perpWallDist = (sideDistY - deltaDistY);
                color = new Color(247, 76, 2);
            }

            lineHeights[i] = new RayLines((int) (Constants.windowHeight / perpWallDist), color);
        }

        return lineHeights;
    }
}

class Ray {
    public double dirX;
    public double dirY;

    public Ray(double w, double z) {
        double cameraX = 2 * z / w - 1;
        dirX = Player.dirX + Player.planeX * cameraX;
        dirY = Player.dirY + Player.planeY * cameraX;
    }
}

enum Side {
    NORTH_SOUTH,
    EAST_WEST
}