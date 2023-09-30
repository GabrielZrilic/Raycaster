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
            int mapX = (int) (Player.x / Constants.cellWidth);
            int mapY = (int) (Player.y / Constants.cellHeight);

            double sideDistX;
            double sideDistY;

            double deltaDistX = Math
                    .sqrt(Constants.cellWidth * Constants.cellWidth
                            + (rays[i].dirY * rays[i].dirY * Constants.cellWidth * Constants.cellWidth)
                                    / (rays[i].dirX * rays[i].dirX));
            double deltaDistY = Math
                    .sqrt(Constants.cellWidth * Constants.cellWidth
                            + (rays[i].dirX * rays[i].dirX * Constants.cellWidth * Constants.cellWidth)
                                    / (rays[i].dirY * rays[i].dirY));
            double perpWallDist;

            double stepX = 0, stepY = 0;
            boolean hit = false;
            Side side = null;

            if (rays[i].dirX < 0) {
                stepX = -Constants.cellWidth;
                sideDistX = (Player.x - mapX * Constants.cellWidth) * deltaDistX / Constants.cellWidth;
            } else {
                stepX = Constants.cellWidth;
                sideDistX = (mapX * Constants.cellWidth + Constants.cellWidth - Player.x) * deltaDistX
                        / Constants.cellWidth;
            }
            if (rays[i].dirY < 0) {
                stepY = -Constants.cellHeight;
                sideDistY = (Player.y - mapY * Constants.cellHeight) * deltaDistY / Constants.cellHeight;
            } else {
                stepY = Constants.cellHeight;
                sideDistY = (mapY * Constants.cellHeight + Constants.cellHeight - Player.y) * deltaDistY
                        / Constants.cellHeight;
            }

            while (!hit) {
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    mapX += stepX / Constants.cellWidth;
                    side = Side.EAST_WEST;
                } else {
                    sideDistY += deltaDistY;
                    mapY += stepY / Constants.cellHeight;
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