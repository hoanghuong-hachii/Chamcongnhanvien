package connectDataBase;
import Data.Nhanvien;
import chamcong.info;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectDB {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {

        try ( Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            if (conn != null) {
                System.out.println("Connected to database.");
            }

            createTableIfNotExists(conn);

        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    private static void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS nhanvien (" +
                    "manv VARCHAR(50) PRIMARY KEY," +
                    "name VARCHAR(100)," +
                    "birth VARCHAR(10)," +
                    "chucvu VARCHAR(50)," +
                    "phone VARCHAR(100)," +
                    "pin VARCHAR(50)," +
                    "image LONGBLOB," +
                    "publicKey LONGBLOB" +
                    ")";
            stmt.execute(createTableSQL);
            System.out.println("Table 'nhanvien' created or already exists.");
        }
    }

    public static String getURL() {
        return URL;
    }
    
    public static String getUSER() {
        return USER;
    }
    
    public static String getPWD() {
        return PASSWORD;
    }
    public static void createdata(Connection conn, info nhanvien) throws SQLException {
        String insertDataSQL = "INSERT INTO nhanvien (manv, name, birth, chucvu, phone, pin, image, publicKey) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertDataSQL)) {
            pstmt.setString(1, nhanvien.getManv());
            pstmt.setString(2, nhanvien.getHoten());
            pstmt.setString(3, nhanvien.getNgaysinh());
            pstmt.setString(4, nhanvien.getChucvu());
            pstmt.setString(5, nhanvien.getSdt());
            pstmt.setString(6, nhanvien.getPin());
            pstmt.setBytes(7, nhanvien.getAvatar());
            pstmt.setBytes(8, nhanvien.getMacongkhai());

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully for manv: " + nhanvien.getManv());
        }
    }
    
    public static Nhanvien getNhanvienByMaNV(Connection conn, String maNV) throws SQLException {
        String selectDataSQL = "SELECT * FROM nhanvien WHERE manv = ?";
        Nhanvien nhanvien = null;

        try (PreparedStatement pstmt = conn.prepareStatement(selectDataSQL)) {
            pstmt.setString(1, maNV);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nhanvien = new Nhanvien();
                nhanvien.setMaNV(rs.getString("manv"));
                nhanvien.setName(rs.getString("name"));
                nhanvien.setBirth(rs.getString("birth"));
                nhanvien.setChucvu(rs.getString("chucvu"));
                nhanvien.setPhone(rs.getString("phone"));
                nhanvien.setPin(rs.getString("pin"));
                nhanvien.setAvatar(rs.getBytes("image"));
                nhanvien.setMacongkhai(rs.getBytes("publicKey"));
            }

            rs.close();
        }

        return nhanvien;
    }
    
    public Nhanvien getInfoByManv(String manv) {
    Nhanvien data = null;
        System.out.println("manv " + manv);
    try {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String selectQuery = "SELECT * FROM nhanvien WHERE manv = ?";
        
        // Chuẩn bị câu truy vấn
        PreparedStatement statement = conn.prepareStatement(selectQuery);
        statement.setString(1, manv);
        
        // Thực thi câu truy vấn
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String hoten = resultSet.getString("name");
            String ngaysinh = resultSet.getString("birth");
            String chucvu = resultSet.getString("chucvu");
            String sdt = resultSet.getString("phone");
            String pin = resultSet.getString("pin");
            byte[] avatar = resultSet.getBytes("image");
            byte[] macongkhai = resultSet.getBytes("publicKey");
            System.out.println("mkck  " + macongkhai);
           // byte[] decodeavatar = Base64.getDecoder().decode(avatar);
           // byte[] decodmacongkhai = Base64.getDecoder().decode(macongkhai);
            
            data = new Nhanvien(manv, hoten, ngaysinh, chucvu, sdt, pin, avatar, macongkhai);
            System.out.println("data" + data.toString());
        }

        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    return data;
}
    
    public static byte[] getNhanvienImageByMaNV(Connection conn, String maNV) throws SQLException {
        String selectImageSQL = "SELECT image FROM nhanvien WHERE manv = ?";
        byte[] imageBytes = null;

        try (PreparedStatement pstmt = conn.prepareStatement(selectImageSQL)) {
            pstmt.setString(1, maNV);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                imageBytes = rs.getBytes("image");
            }

            rs.close();
        }

        return imageBytes;
    }
    
    public static List<Nhanvien> getAllNhanvien(Connection conn) throws SQLException {
        List<Nhanvien> nhanvienList = new ArrayList<>();
        String selectAllDataSQL = "SELECT * FROM nhanvien";

        try (PreparedStatement pstmt = conn.prepareStatement(selectAllDataSQL)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Nhanvien nhanvien = new Nhanvien();
                nhanvien.setMaNV(rs.getString("manv"));
                nhanvien.setName(rs.getString("name"));
                nhanvien.setBirth(rs.getString("birth"));
                nhanvien.setChucvu(rs.getString("chucvu"));
                nhanvien.setPhone(rs.getString("phone"));
                nhanvien.setPin(rs.getString("pin"));
                nhanvien.setAvatar(rs.getBytes("image"));
                nhanvien.setMacongkhai(rs.getBytes("publicKey"));

                nhanvienList.add(nhanvien);
            }

            rs.close();
        }

        return nhanvienList;
    }
    
    public void deleteData(String sothe) {
    try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
        
        // Tạo câu truy vấn DELETE
        String deleteQuery = "DELETE FROM nhanvien WHERE manv=?";
        
        // Chuẩn bị câu truy vấn
        PreparedStatement statement = con.prepareStatement(deleteQuery);
        
        // Đặt tham số trong câu truy vấn
        statement.setString(1, sothe);
        
        // Thực thi câu truy vấn
        statement.executeUpdate();
        
        statement.close();
        con.close();
        
        System.out.println("Xóa thông tin thành công");
        
    } catch (SQLException ex) {
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public Boolean checkthe(String manv){
        boolean check=false;
       try {
           
           Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
String selectQuery = "SELECT * FROM nhanvien WHERE manv = '"+manv+"'";

        Statement statement = conn.createStatement();
        
        ResultSet resultSet = statement.executeQuery(selectQuery);
            
      
        while (resultSet.next()) {
            // Đọc dữ liệu từ các cột
            check=true;
            
            // Lấy các giá trị khác tùy theo cấu trúc bảng

            // Xử lý dữ liệu
            // ...

            // In ra kết quả
            
            
            // In các giá trị khác tùy theo cấu trúc bảng
        }

        // Đóng ResultSet, Statement và Connection
        resultSet.close();
        statement.close();
        conn.close();
        return check;

       } catch (SQLException ex) {
           Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       return  check;
    }
    
    public void updateData(String manv, info updatedInfo) {
    try {
         Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //manv, name, birth, chucvu, phone, pin, image, publicKey
        // Tạo câu truy vấn UPDATE
        String updateQuery = "UPDATE nhanvien SET name=?, birth=?, chucvu=?, phone=? WHERE manv=?";
        
        // Chuẩn bị câu truy vấn
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        
        // Đặt các tham số trong câu truy vấn
        statement.setString(1, updatedInfo.getHoten());
        statement.setString(2, updatedInfo.getNgaysinh());
        statement.setString(3, updatedInfo.getChucvu());
        statement.setString(4, updatedInfo.getSdt());
        statement.setString(5, manv);
        
        // Thực thi câu truy vấn
        statement.executeUpdate();
        
        statement.close();
        conn.close();
        
        System.out.println("Cập nhật thông tin thành công");
        
    } catch (SQLException ex) {
        Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
}
