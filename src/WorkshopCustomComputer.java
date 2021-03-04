
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Vector;

class ItemEntry {
    String type;
    String name;
    double price;

    ItemEntry(String  type,String name ,double price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name+" , ราคา "+price +" บาท";
    }
}
public class WorkshopCustomComputer extends JFrame {
    int posY = 70;
    //ตัวแปรเก็บข้อมูลสินค้า
    ArrayList<ItemEntry> stocks = new ArrayList<ItemEntry>();
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
        setLocationByPlatform(true);
        //กำหนดให้กดปิดแล้วออกจากโปรแกรม
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        //โหลดสินค้า
        loadStock();

        //เพิ่มชื่อโปรแกรม
        JLabel titleLabel = new JLabel("Workshop : โปรแกรมจัดสเปคคอมพิวเตอร์ | Custom Computer");
        titleLabel.setBounds(40, 20, 500, 60);
        add(titleLabel);

        //สร้างตัวแปรเช็ค ชิ้นส่วนที่เพิ่มเข้าไปแล้ว
        ArrayList<String> checkAdded = new ArrayList<String>();
        //สร้างตัวแปรเก็บ comboBox ที่สรา้งไว้
        ArrayList<JComboBox> ListComboBox = new ArrayList<>();
        for (int i = 0; i < stocks.size(); i++) {

            if (checkAdded.contains(stocks.get(i).type)) continue;
            checkAdded.add(stocks.get(i).type);

            posY += 50;

            //สร้าง jlabel ของชิ้นส่วนต่างๆ
            JLabel lbResult = new JLabel(stocks.get(i).type);
            lbResult.setBounds(50, posY, 300, 20);
            add(lbResult);


            //สร้าง JComboBox ของชิ้นส่วนต่างๆ
            int finalI = i;
            Vector model = new Vector();
            stocks.forEach((v) -> {
                if (v.type == stocks.get(finalI).type){
                    model.addElement(v);
                }
            });
            JComboBox cb = new JComboBox(model);
            cb.setSelectedItem(null);
            cb.setBounds(150, posY, 300, 30);
            add(cb);
            ListComboBox.add(cb);

        }


        JButton submit = new JButton("คำนวนราคา");
        submit.setBounds(200,680,120,40);
        submit.addActionListener(new ActionListener() {
            double total = 0.0d;
            boolean isShow = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                total = 0;
                isShow = false;
                ArrayList<ItemEntry> itemBill = new ArrayList<ItemEntry>();
                ListComboBox.forEach((v) ->{

                    ItemEntry item = (ItemEntry)v.getModel().getSelectedItem();

                    if (isShow) return;

                    if (item == null){
                        ItemEntry iT = (ItemEntry) v.getModel().getElementAt(0);
                        JOptionPane.showMessageDialog(null, "โปรดเลือก "+iT.type);
                        isShow=true;
                        return;

                    }
                    total += item.price;
                    itemBill.add(item);
                });
                if (!isShow){
                    createFrameBill(itemBill);
                }
            }
        });
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
    public void loadStock(){
        //เพิ่มราคา CPU เข้าคลังสินค้า
        stocks.add(new ItemEntry("CPU","Intel i3",2350));
        stocks.add(new ItemEntry("CPU","Intel i5",4450));
        stocks.add(new ItemEntry("CPU","Intel i7",10900));
        stocks.add(new ItemEntry("CPU","Intel i9",23500));

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
    }
    public static String billHTML(ArrayList<ItemEntry> item) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>"
                + "<style type='text/css'>"
                + "body, h1, th, td {"
                + "  font-size: 16pt;"
                + "background: #F3F3F4;"
                + "color: #1E1E1F;"
                + "}"
                + "h1 {"
                + "  font-size: 20pt;"
                + "}"
                + "table {"
                + "}"
                + "td, th {"
                + "}"
                + "th {"
                + "  border-bottom: thin solid gray;"
                + "}"
                + ".overline td {"
                + "  border-top: thin solid gray;"
                + "}"
                + "</style>"
                + "<body>");
        sb.append("<h1>- ใบเสร็จรับเงิน -</h1>");
        sb.append("<table width='400' cellspacing='0'>"
                + "<tr>"
                + "<th width='50%' align='left'>ชิ้นส่วน</th>"
                + "<th width='20%' align='right'>สินค้า</th>"
                + "<th width='30%' align='right'>ราคา</th>"
                + "</tr>");
        int total = 0;
        for (ItemEntry v : item
             ) {
            sb.append("<tr>"
                    + "<td>")
                    .append(v.type)
                    .append("</td>"
                            + "<td align='right'>")
                    .append(v.name)
                    .append("</td>"
                            + "<td align='right'>")
                    .append(formatAmount((int) v.price))
                    .append("</td>"
                            + "</tr>");
            total +=  v.price;
        }

        sb.append("<tr class='overline'>"
                + "<td>&nbsp;")
                .append("</td>"
                        + "<td align='right' style='color:red;font-size: 18 px;'>")
                .append("รวม")
                .append("</td>"
                        + "<td align='right' style='color:red;font-size: 18 px;'>")
                .append(formatAmount(total))
                .append("</td>"
                        + "</tr>"
                        + "</table>");

        return sb.toString();
    }

    private static String formatAmount(int amount) {
        return new MessageFormat("{0,number,currency}").format(new Object[]{amount})
                .replace(' ', '\u00a0');
    }

    public static void createFrameBill(ArrayList<ItemEntry> item)
    {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("ใบเสร็จรับเงิน");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



            JPanel panel = new JPanel();
            panel.setPreferredSize (new Dimension (450, 400));


            String sb = billHTML(item);
            JLabel bill = new JLabel();
            bill.setBounds(0,0,450,400);
            bill.setText(sb);
            panel.add(bill);


            frame.getContentPane().add( panel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setResizable(false);
            frame.setLayout(null);
            frame.setVisible(true);


        });
    }
}
