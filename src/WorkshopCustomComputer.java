import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class WorkshopCustomComputer extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WorkshopCustomComputer form = new WorkshopCustomComputer();
                form.setVisible(true);
            }
        });

    }
    WorkshopCustomComputer(){
        setUIFont(new FontUIResource("Microsoft Sans Serif", Font.PLAIN, 16));
        setTitle("Workshop : โปรแกรมจัดสเปคคอมพิวเตอร์");
        setSize(600, 500);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Workshop : โปรแกรมจัดสเปคคอมพิวเตอร์ | Custom Computer");
        titleLabel.setBounds(80, 20, 500, 60);
        getContentPane().add(titleLabel);

    }
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}