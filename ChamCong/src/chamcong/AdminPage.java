/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chamcong;

import static chamcong.FormNhapInfor.rsaPubKey;
import static chamcong.Login.thebus;
import connectDataBase.ConnectDB;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.smartcardio.CardException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class AdminPage extends javax.swing.JFrame {
    private boolean connected= true;
    private boolean init = false;
    static info info;
   
    private boolean khoitao = false;
    private Boolean input= false;
    static byte[] rsaPubKey = new byte[256];
    ConnectDB connectDatabase=new ConnectDB();
    
    private TheNV thebus;
    private Login login;
    /**
     * Creates new form AdminPage
     */
    public AdminPage() {
        info = new info();
        thebus = new TheNV();
        login = new Login();
       // setConnected(init);
        initComponents();
    }
    
    private void getImage(byte[] img) {
     if (img == null) return;
     try {
         byte[] cmd = {(byte) 0xA0, (byte) 0x13, (byte) 0x01, (byte) 0x00};
         thebus.sendAPDUtoApplet(cmd);
         int sendlen = img.length;

         byte[] cmnd = {(byte) 0xA0, (byte) 0x13, (byte) 0x02, (byte) 0x00};
         byte[] resimg = new byte[sendlen];
         int pointer = 0;
         int datalen = 255;
         while (sendlen > 0) {
             thebus.sendAPDUtoApplet(cmnd);
             byte[] temp = thebus.resAPDU.getData();
             System.arraycopy(temp, 0, resimg, pointer, datalen);
             pointer += 255;
             sendlen -= 255;
             if (sendlen < 255) {
                 datalen = sendlen;
             }
         }
         System.out.println("ảnh res:" + resimg);
         System.out.println("Image = "+img);
         ByteArrayInputStream bais = new ByteArrayInputStream(img);
         BufferedImage b = ImageIO.read(bais);
         if (b != null) {
             ImageIcon icon = new ImageIcon(b.getScaledInstance(anhthe.getWidth(), anhthe.getHeight(), Image.SCALE_SMOOTH));
             anhthe.setIcon(icon);
         } else {
             System.out.println("Failed to read the image data.");
             
         }
     } catch (IOException ex) {
         Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
     }
 }


         // Phương thức để cập nhật trạng thái của các thành phần trong giao diện
    private void setConnected(boolean init) {
        this.init = init;
      //  formInfor.setEnabled(init);
        btnDisplay.setEnabled(init);
        txt_manv.setEnabled(init);
        txt_name.setEnabled(init);
        txt_ns.setEnabled(init);
        txt_chucvu.setEnabled(init);
        txt_sdt.setEnabled(init);
        anhthe.setEnabled(init);
    }
        private void loginSetEnabled(boolean b) {
        formInfor.setEnabled(b);
        if(b){
            formInfor.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
            //txt_login.setForeground(Color.blue);
            txt_manv.setBackground(Color.white);
            txt_name.setBackground(Color.white);
            txt_ns.setBackground(Color.white);
            txt_chucvu.setBackground(Color.white);
            txt_sdt.setBackground(Color.white);
            anhthe.setBackground(Color.white);
        }else{
            formInfor.setBorder(null);
           // txt_login.setForeground(Color.black);
            txt_manv.setBackground(Color.decode("#d7d7d7"));
            txt_name.setBackground(Color.decode("#d7d7d7"));
            txt_ns.setBackground(Color.decode("#d7d7d7"));
            txt_chucvu.setBackground(Color.decode("#d7d7d7"));
            txt_sdt.setBackground(Color.decode("#d7d7d7"));
            anhthe.setBackground(Color.decode("#d7d7d7"));
        }
        txt_manv.setEnabled(b);
        txt_name.setEnabled(b);
        txt_ns.setEnabled(b);
        txt_chucvu.setEnabled(b);
        txt_sdt.setEnabled(b);
        anhthe.setEnabled(b);
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
     
//      private void getImage(byte[] img) {
//     if (img == null) return;
//     try {
//         byte[] cmd = {(byte) 0xA0, (byte) 0x13, (byte) 0x01, (byte) 0x00};
//         thebus.sendAPDUtoApplet(cmd);
//         int sendlen = img.length;
//
//         byte[] cmnd = {(byte) 0xA0, (byte) 0x13, (byte) 0x02, (byte) 0x00};
//         byte[] resimg = new byte[sendlen];
//         int pointer = 0;
//         int datalen = 255;
//         while (sendlen > 0) {
//             thebus.sendAPDUtoApplet(cmnd);
//             byte[] temp = thebus.resAPDU.getData();
//             System.arraycopy(temp, 0, resimg, pointer, datalen);
//             pointer += 255;
//             sendlen -= 255;
//             if (sendlen < 255) {
//                 datalen = sendlen;
//             }
//         }
//         System.out.println("ảnh res:" + resimg);
//         System.out.println("Image = "+img);
//         ByteArrayInputStream bais = new ByteArrayInputStream(img);
//         BufferedImage b = ImageIO.read(bais);
//         if (b != null) {
//             ImageIcon icon = new ImageIcon(b.getScaledInstance(anh.getWidth(), anh.getHeight(), Image.SCALE_SMOOTH));
//             anh.setIcon(icon);
//         } else {
//             System.out.println("Failed to read the image data.");
//             
//         }
//     } catch (IOException ex) {
//         Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
//     }
// }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_unlock = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btn_initcard = new javax.swing.JButton();
        formInfor = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_manv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ns = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_chucvu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        btnDisplay = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        anhthe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setForeground(new java.awt.Color(204, 255, 204));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/adminn.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Quản trị viên ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_add.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_add.setText("Thêm nhân viên");
        btn_add.setToolTipText("");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/personal-information.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pass.png"))); // NOI18N
        jLabel4.setText("jLabel3");

        btn_unlock.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_unlock.setText("Mở khóa thẻ");
        btn_unlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_unlockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_unlock, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_unlock, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Ngắt kết nối");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/unlink.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        jLabel6.setText("jLabel3");

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("Xóa thẻ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pass.png"))); // NOI18N
        jLabel13.setText("jLabel3");

        btn_initcard.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_initcard.setText("Khởi tạo thẻ");
        btn_initcard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_initcardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_initcard, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_initcard, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        formInfor.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mã nhân viên:");

        txt_manv.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Họ và tên:");

        txt_name.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Ngày sinh:");

        txt_ns.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Chức vụ:");

        txt_chucvu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Số điện thoại:");

        txt_sdt.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        btnDisplay.setBackground(new java.awt.Color(0, 0, 204));
        btnDisplay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDisplay.setForeground(new java.awt.Color(255, 255, 255));
        btnDisplay.setText("Hiển thị thông tin");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel12.setText("Thông tin nhân viên");

        btn_back.setBackground(new java.awt.Color(255, 0, 0));
        btn_back.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("Quay lại");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        anhthe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        anhthe.setToolTipText("");
        anhthe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        anhthe.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        anhthe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout formInforLayout = new javax.swing.GroupLayout(formInfor);
        formInfor.setLayout(formInforLayout);
        formInforLayout.setHorizontalGroup(
            formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formInforLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formInforLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formInforLayout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formInforLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formInforLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(formInforLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(anhthe, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formInforLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btn_back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );
        formInforLayout.setVerticalGroup(
            formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formInforLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formInforLayout.createSequentialGroup()
                        .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_ns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(anhthe, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(formInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
          byte[] cmd = {(byte) 0xA0, (byte) 0x11, (byte) 0x00, (byte) 0x00};
        byte[] data= {0};        
        thebus.sendAPDUtoApplet(cmd);
        byte[] dataRes = thebus.resAPDU.getData();
        int le = thebus.resAPDU.getNr();
        String tach = new String(dataRes) ;

        System.out.print("a:"+tach);
        String[] a = tach.split(":");
        String mnv = a[0];
        String ht = a[1];
        String ns = a[2];
        String cv = a[3];
        String dt = a[4];
        
        txt_manv.setText(mnv);
        txt_name.setText(ht);
        txt_ns.setText(ns);
        txt_chucvu.setText(cv);
        txt_sdt.setText(dt);
       
        System.out.println("data" + mnv + ht + ns + cv + dt);
        
        byte[] cmd1 = {(byte) 0xA0, (byte) 0x21, (byte) 0x00, (byte) 0x00};
        thebus.sendAPDUtoApplet(cmd1);
        byte[] b = thebus.resAPDU.getData();
        byte[] imageAvatar=null ;
        try (Connection conn = DriverManager.getConnection(ConnectDB.getURL(), ConnectDB.getUSER(), ConnectDB.getPWD())) {
            imageAvatar=connectDatabase.getNhanvienImageByMaNV(conn, txt_manv.getText().toString());
            JOptionPane.showMessageDialog(this, "truy xuất ảnh thành công");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất ảnh: " + e.getMessage());

        }
        System.out.println("image" + imageAvatar);
        getImage(imageAvatar);
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if(connected ==true){
            String defaultString="[0, 0, 0, 0]";
            if(check_pindefault(defaultString)==1)
            {
                JOptionPane.showMessageDialog(this ,"Thẻ chưa khởi tạo dữ liệu ");
                return;
            }
            
            if(txt_manv.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this ,"Nhập mã pin trước khi xóa thẻ");
                return;
            }
                byte[] cmd = {(byte) 0xA0, (byte) 0x18, (byte) 0x00, (byte) 0x00};
                byte[] data = {0};
               // setCommandAPDU(cmd,(byte)0, data, (byte)0);//hien thi apdu cmd len GUI
                thebus.sendAPDUtoApplet(cmd);
                byte[] dataRes= thebus.resAPDU.getData();
                int le= thebus.resAPDU.getNr();
               // setResponseAPDU(dataRes,(short) le);//hien thi du lieu phan hoi tu applet
                String sothexoa=txt_manv.getText();
                txt_name.setText("");
                txt_manv.setText("");
                txt_ns.setText("");
                txt_sdt.setText("");
                txt_chucvu.setText("");
//                txt_sodu.setText("");
//                txt_pin.setText("");
                JOptionPane.showMessageDialog(this, "Thẻ đã xóa dữ liệu.");
                input=false;
                khoitao=false;
             //   cardready=false;
              anhthe.setIcon(null);
              connectDatabase.deleteData(sothexoa);
        }else JOptionPane.showMessageDialog(this, "Chưa connect thẻ");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if(connected == true ){
            String manv = txt_manv.getText();
                FormNhapInfor formNhap= new FormNhapInfor(manv);
                formNhap.setVisible(true);
                formNhap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                init = true;
                //setConnected(init);
        }else JOptionPane.showMessageDialog(this, "Chức năng này chưa được phép truy cập");

    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_initcardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_initcardActionPerformed

    sendData();
            
            
    }//GEN-LAST:event_btn_initcardActionPerformed

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
                String phone = info.getSdt();
                String pin = info.getPin();
                String arraysend = manv.concat(".").concat(hoten).concat(".").concat(ngaysinh).concat(".").concat(chucvu).concat(".").concat(phone).concat(".").concat(pin);
                System.out.println("send:"+arraysend);
                int lc = arraysend.length();
                byte datalen = (byte) lc; //do dai du lieu gui vao applet
                byte[] cmd = {(byte) 0xA0, (byte) 0x10, (byte) 0x00, (byte) 0x00};
                byte[] data = arraysend.getBytes();
                //setCommandAPDU(cmd, (byte)lc, data, (byte)0);
                thebus.sendAPDUtoApplet(cmd, data);
                rsaPubKey = thebus.resAPDU.getData();
                
                info.setMacongkhai(rsaPubKey);
                System.out.println("RSA = "+ bytesToHex(rsaPubKey));
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
    
    // Hàm chuyển đổi byte thành chuỗi hex
public static String bytesToHex(byte[] bytes) {
    StringBuilder result = new StringBuilder();
    for (byte b : bytes) {
        result.append(String.format("%02X", b));
    }
    return result.toString();
}
    
    
    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
//        clearFields();
        setVisible(false);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_unlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_unlockActionPerformed
        // TODO add your handling code here:
           if(connected==true){
            
            
             String defaultString="[0, 0, 0, 0]";
            if(check_pindefault(defaultString)==1)
            {
                JOptionPane.showMessageDialog(this ,"Thẻ chưa khởi tạo dữ liệu ");
                return;
            }
                byte[] cmd = {(byte) 0xA0, (byte) 0x20, (byte) 0x00, (byte) 0x00};
                byte[] data= {0};
              //  setCommandAPDU(cmd,(byte)0, data, (byte)0);//hien thi apdu cmd len GUI
                thebus.sendAPDUtoApplet(cmd);
                byte[] dataRes= thebus.resAPDU.getData();
                int le= thebus.resAPDU.getNr();
                Login login = new Login();
                login.setSaipin(3);
               // setResponseAPDU(dataRes,(short) le);//hien thi du lieu phan hoi tu applet
               // saipin=3;
                JOptionPane.showMessageDialog(this, "Thẻ đã được mở khóa.");
            
        }else JOptionPane.showMessageDialog(this, "Chưa connect thẻ");
    }//GEN-LAST:event_btn_unlockActionPerformed

    public int check_pindefault(String pin) {
        short lc = (short) pin.length(); //do dai du lieu gui vao applet
        short le = 1;//du lieu nhan mong doi (Le)
        byte[] cmd = {(byte) 0xA0, (byte) 0x22, (byte) 0x00, (byte) 0x00};
        byte[] data = pin.getBytes();
        //setCommandAPDU(cmd, (byte)lc, data,(byte)le);
        thebus.sendAPDUtoApplet(cmd, data);
        byte[] dataRes = thebus.resAPDU.getData();
        int lenRes= thebus.resAPDU.getNr() ;
       // setResponseAPDU(dataRes, (byte)lenRes);
        //String a = new String(dataRes);
        if (dataRes[0] == (byte)0x01) {//đúng mã PIN
            return 1;
        } else if(dataRes[0] == (byte)0x00){
            return 0;
        }else return 2;
    }
    
       
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anhthe;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_initcard;
    private javax.swing.JButton btn_unlock;
    private javax.swing.JPanel formInfor;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField txt_chucvu;
    private javax.swing.JTextField txt_manv;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_ns;
    private javax.swing.JTextField txt_sdt;
    // End of variables declaration//GEN-END:variables
}
