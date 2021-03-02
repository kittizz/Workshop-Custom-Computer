
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

class ItemEntry {
    String type;
    String name;
    double price;

    ItemEntry(String  type,String name ,double price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
public class WorkshopCustomComputer extends JFrame {
    int posY = 70;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WorkshopCustomComputer form = null;
                form = new WorkshopCustomComputer();
                form.setVisible(true);
            }
        });

    }
    WorkshopCustomComputer()  {
        //ตั้งค่า ฟอนต์ ให้โปรแกรม
        setUIFont(new FontUIResource("Microsoft Sans Serif", Font.PLAIN, 16));

        //กำหนดหัวโปรแกรม
        setTitle("Workshop : โปรแกรมจัดสเปคคอมพิวเตอร์");
        //กำหนดขนาดโปรแกรม
        setSize(500, 800);
        //กำหนดตำแหน่งขอหน้าต่าง
        setLocation(0, 0);
        //กำหนดให้กดปิดแล้วออกจากโปรแกรม
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        //ตัวแปรเก็บข้อมูลสินค้า
        ArrayList<ItemEntry> stocks = new ArrayList<ItemEntry>();

        //เพิ่มราคา CPU เข้าคลังสินค้า
        stocks.add(new ItemEntry("CPU","i3",2350));
        stocks.add(new ItemEntry("CPU","i5",4450));
        stocks.add(new ItemEntry("CPU","i7",10900));
        stocks.add(new ItemEntry("CPU","i9",2350));

        //เพิ่มราคา Mainboard เข้าคลังสินค้า
        stocks.add(new ItemEntry("Mainboard","B460",4490));
        stocks.add(new ItemEntry("Mainboard","H470",5390));
        stocks.add(new ItemEntry("Mainboard","Z490",8990));

        //เพิ่มราคา GPU เข้าคลังสินค้า
        stocks.add(new ItemEntry("GPU","RTX 2060",13711));
        stocks.add(new ItemEntry("GPU","RTX 2070",18000));
        stocks.add(new ItemEntry("GPU","RTX 2080",21000));
        stocks.add(new ItemEntry("GPU","RTX 2080 Ti",25000));

        //เพิ่มราคา Ram เข้าคลังสินค้า
        stocks.add(new ItemEntry("Ram","4 GB",988));
        stocks.add(new ItemEntry("Ram","8 GB",1390));
        stocks.add(new ItemEntry("Ram","16 GB",2880));
        stocks.add(new ItemEntry("Ram","32 GB",4250));
        stocks.add(new ItemEntry("Ram","64 GB",12250));

        //เพิ่มราคา Harddisk เข้าคลังสินค้า
        stocks.add(new ItemEntry("Harddisk","250 GB",500));
        stocks.add(new ItemEntry("Harddisk","500 GB",990));
        stocks.add(new ItemEntry("Harddisk","1 TB",1080));
        stocks.add(new ItemEntry("Harddisk","2 TB",1660));

        //เพิ่มราคา Case เข้าคลังสินค้า
        stocks.add(new ItemEntry("Case","Mini Tower",2970));
        stocks.add(new ItemEntry("Case","Mid Tower",2670));
        stocks.add(new ItemEntry("Case","Full Tower",3390));

        //เพิ่มราคา PowerSupply เข้าคลังสินค้า
        stocks.add(new ItemEntry("PowerSupply","550 W",1290));
        stocks.add(new ItemEntry("PowerSupply","750 W",1990));
        stocks.add(new ItemEntry("PowerSupply","850 W",3790));

        //เพิ่มราคา CPU Cooler เข้าคลังสินค้า
        stocks.add(new ItemEntry("CPU Cooler","Air",470));
        stocks.add(new ItemEntry("CPU Cooler","Water",3990));

        //เพิ่มราคา Monitor เข้าคลังสินค้า
        stocks.add(new ItemEntry("Monitor","18 นิ้ว",1300));
        stocks.add(new ItemEntry("Monitor","20 นิ้ว",2000));
        stocks.add(new ItemEntry("Monitor","24 นิ้ว",3600));
        stocks.add(new ItemEntry("Monitor","27 นิ้ว",4190));

        //เพิ่มราคา Keyboard เข้าคลังสินค้า
        stocks.add(new ItemEntry("Keyboard","ขนาด 60%",2690));
        stocks.add(new ItemEntry("Keyboard","ขนาด 80%",3690));
        stocks.add(new ItemEntry("Keyboard","ขนาด 100%",3290));

        //เพิ่มราคา Mouse เข้าคลังสินค้า
        stocks.add(new ItemEntry("Mouse","Acer",790));
        stocks.add(new ItemEntry("Mouse","Razer",1390));
        stocks.add(new ItemEntry("Mouse","Logitch",850));

        JLabel titleLabel = new JLabel("Workshop : โปรแกรมจัดสเปคคอมพิวเตอร์ | Custom Computer");
        titleLabel.setBounds(40, 20, 500, 60);
        add(titleLabel);

        ArrayList<String> checkAdded = new ArrayList<String>();
        for (int i = 0; i < stocks.size(); i++) {
            if (checkAdded.contains(stocks.get(i).type)) continue;
            checkAdded.add(stocks.get(i).type);

            posY += 50;

            JLabel lbResult = new JLabel(stocks.get(i).type);
            lbResult.setBounds(50, posY, 300, 20);
            add(lbResult);

            final ArrayList<String> combo = new ArrayList<String>();

            int finalI = i;
            stocks.forEach((v) -> {
                if (v.type == stocks.get(finalI).type){
                    combo.add(v.name+" , ราคา "+v.price +" บาท");
                }
            });

            JComboBox cb = new JComboBox(combo.toArray());
            cb.setBounds(150, posY, 260, 30);
            add(cb);
        }

        JButton submit = new JButton("คำนวนราคา");
        submit.setBounds(200,680,120,40);
        add(submit);
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