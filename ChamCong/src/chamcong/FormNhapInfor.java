/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chamcong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static chamcong.Login.thebus;
import connectDataBase.ConnectDB;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class FormNhapInfor extends javax.swing.JFrame {
    private info info;
    String manvnew;
    ConnectDB connectDatabase=new ConnectDB();
    TheNV thebus;
    private Boolean input= false;
    static boolean cardready= false;
    private boolean connected= false;
    private boolean khoitao = false;
    static byte[] rsaPubKey = new byte[256];
    private int soid=0;
    private int saipin= 3;
    int num;
    /**
     * Creates new form FormNhapInfor
     */
    public FormNhapInfor(String manv) {
        info = new info();
        thebus = new TheNV();
        initComponents();
        num = (int) (System.currentTimeMillis() % 1000000);
        txt_manv.setText(idNhanvien(num));        
//        if(connectDatabase.checkthe(txt_manv.getText()))
//        {
//            
//        }
//        while (connectDatabase.checkthe(txt_manv.getText())) {
//            manvnew++;
//            txt_manv.setText(idthe(manvnew));
//            
//        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_manv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_hoten = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ns = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_chucvu = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        btn_ok = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        anh = new javax.swing.JLabel();
        Button_getava = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_checkpin = new javax.swing.JPasswordField();
        txt_pin = new javax.swing.JPasswordField();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mã nhân viên:");

        txt_manv.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Họ và tên:");

        txt_hoten.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hotenActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Ngày sinh:");

        txt_ns.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Chức vụ:");

        txt_chucvu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txt_sdt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btn_ok.setBackground(new java.awt.Color(0, 0, 204));
        btn_ok.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_ok.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok.setText("Cập nhật");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Nhập thông tin ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Số điện thoại:");

        anh.setBackground(new java.awt.Color(255, 255, 255));
        anh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anh.setText("3x4");
        anh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Button_getava.setBackground(new java.awt.Color(102, 102, 255));
        Button_getava.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Button_getava.setForeground(new java.awt.Color(255, 255, 255));
        Button_getava.setText("Chọn ảnh");
        Button_getava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_getavaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Nhập lại:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Nhập mã PIN:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/new.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(Button_getava)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_chucvu, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_pin, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(txt_checkpin))))
                .addGap(59, 59, 59))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(Button_getava)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_pin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_checkpin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String idNhanvien(int idmoi){
        String a= "NV";
        int soid = 100 + idmoi;
        String id= a +String.valueOf(soid);
        return id;
    }
    
    
     private void sendData() {
        
        if(info.getHoten()==null)
            {
                return;
            }
            if (info.getHoten().equals("")==false) {
                String defaultString="[0, 0, 0, 0]";
            
                setImage(info.getAvatar());
                //chuyen du lieu xuong applet
                String manv = info.getManv();
                String hoten = info.getHoten();
                String ngaysinh = info.getNgaysinh();
                String chucvu = info.getChucvu();
                String sdt = info.getSdt();
                String pin = info.getPin();
                String arraysend = manv.concat(".").concat(hoten).concat(".").concat(ngaysinh).concat(".").concat(chucvu).concat(".").concat(sdt).concat(".").concat(pin);
                System.out.println("send:"+arraysend);
                int lc = arraysend.length();
                byte datalen = (byte) lc; //do dai du lieu gui vao applet
                byte[] cmd = {(byte) 0xA0, (byte) 0x10, (byte) 0x00, (byte) 0x00};
                byte[] data = arraysend.getBytes();
                //setCommandAPDU(cmd, (byte)lc, data, (byte)0);
                thebus.sendAPDUtoApplet(cmd, data);
                rsaPubKey = thebus.resAPDU.getData();
                
                info.setMacongkhai(rsaPubKey);
                System.out.println("RSA = "+ rsaPubKey);
                System.out.println("THONG TIN = "+ info.toString());
                if(thebus.resAPDU.getSW1() == 0x90){
                    
                    try (Connection conn = DriverManager.getConnection(ConnectDB.getURL(), ConnectDB.getUSER(), ConnectDB.getPWD())) {
                        connectDatabase.createdata(conn, info);
                        JOptionPane.showMessageDialog(this, "Khởi tạo thông tin thành công");
                        input = true;
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi lưu thông tin vào cơ sở dữ liệu: " + e.getMessage());
                        khoitao = false;
                        input = false;
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Lỗi! Hãy khởi tạo lại thông tin");
                    khoitao=false;
                    input=false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa khởi tạo thông tin");
            }
        
    }
    

    
    private void txt_hotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hotenActionPerformed

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
    String pin = Arrays.toString(txt_pin.getPassword());
        String checkpin = Arrays.toString(txt_checkpin.getPassword());
        if(txt_manv.getText().equals("") || txt_hoten.getText().equals("") || txt_ns.getText().equals("")|| txt_chucvu.getText().equals("") ||txt_sdt.getText().equals("")|| txt_pin.getPassword().equals("")|| txt_checkpin.getPassword().equals("")){
            JOptionPane.showMessageDialog(this, "Tất cả các trường không được để trống!");
        }else if (pin.length() <18 || pin.length() >60){
            JOptionPane.showMessageDialog(this, "độ dài PIN từ 6-20 ký tự.");
        }else if((pin.equals(checkpin)) != true) {
            JOptionPane.showMessageDialog(this, "Xác nhận mã pin sai");
        } else {
        String manv = txt_manv.getText();
        String hoten = txt_hoten.getText();
        String ngaysinh = txt_ns.getText();
        String chucvu = txt_chucvu.getText();
        String sdt = txt_sdt.getText();
        info.setManv(manv);
        info.setHoten(hoten);
        info.setNgaysinh(ngaysinh);
        info.setChucvu(chucvu);
        info.setSdt(sdt);
        info.setPin(pin);
        AdminPage.info = this.info;
        JOptionPane.showMessageDialog(null, "Khởi tạo nội dung thẻ thành công.");
        txt_manv.setText("");
        txt_hoten.setText("");
        txt_ns.setText("");
        txt_chucvu.setText("");
        txt_sdt.setText("");
        txt_pin.setText("");
        txt_checkpin.setText("");
        setVisible(false);
        }
    }//GEN-LAST:event_btn_okActionPerformed

    private void clearFields() {
        txt_manv.setText("");
        txt_hoten.setText("");
        txt_ns.setText("");
        txt_chucvu.setText("");
        txt_sdt.setText("");
        txt_pin.setText("");
        txt_checkpin.setText("");
    }
     private void setImage(byte [] img){
        if(img == null) return;
               
        int sendlen = img.length;
        System.out.println("ảnh gửi:" +img);
        int pointer = 0;
        byte[] temp = new byte[255];
        int datalen = 255;
        while(sendlen >0){
            System.arraycopy(img, pointer, temp, 0, datalen);
            pointer += 255;
            sendlen -=255;
            if(sendlen <255){
                datalen = sendlen;
            }
        }
//        byte[] cmd_encrypt = {(byte) 0xA0, (byte) 0x12, (byte) 0x03, (byte) 0x00};
//        thebus.sendAPDUtoApplet(cmd_encrypt);
    }
    
    
    private void Button_getavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_getavaActionPerformed
        // TODO add your handling code here:
          JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String path = file.getAbsolutePath();
            BufferedImage bimage;
            try{
                bimage = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bimage, "jpg", baos);
                byte[] img = baos.toByteArray();
                if(img.length > 128000){
                    JOptionPane.showMessageDialog(this, "Ảnh bạn chọn lớn hơn kích thước tối đa (128Kb), có thể xảy ra lỗi!\nHãy chọn ảnh khác!", "Warning!!!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ImageIcon icon= new ImageIcon(bimage.getScaledInstance(anh.getWidth(), anh.getHeight(), Image.SCALE_SMOOTH));
                    icon.getImage();
                    anh.setIcon(icon);
                    info.setAvatar(img);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_Button_getavaActionPerformed


    private String idthe(int idmoi){
        String a= "TXB";
        int soid = 10000+idmoi;
        String id= a+String.valueOf(soid);
        return id;
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormNhapInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormNhapInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormNhapInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormNhapInfor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormNhapInfor().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_getava;
    private javax.swing.JLabel anh;
    private javax.swing.JButton btn_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField txt_checkpin;
    private javax.swing.JTextField txt_chucvu;
    private javax.swing.JTextField txt_hoten;
    private javax.swing.JTextField txt_manv;
    private javax.swing.JTextField txt_ns;
    private javax.swing.JPasswordField txt_pin;
    private javax.swing.JTextField txt_sdt;
    // End of variables declaration//GEN-END:variables
}
