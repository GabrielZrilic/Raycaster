import com.formdev.flatlaf.FlatDarkLaf;

public class App {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        FlatDarkLaf.setup();

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
