
package Data;

public class Nhanvien {
    private String name ;
    private String maNV ;
    private String birth;
    private String chucvu;
   
    private String phone;
    private String pin ;
    private byte[] avatar,macongkhai;

    public Nhanvien(String name, String maNV, String birth, String chucvu, String phone, String pin, byte[] avatar, byte[] macongkhai) {
        this.name = name;
        this.maNV = maNV;
        this.birth = birth;
        this.chucvu = chucvu;
        this.phone = phone;
        this.pin = pin;
        this.avatar = avatar;
        this.macongkhai = macongkhai;
    }

    

    public Nhanvien() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public byte[] getMacongkhai() {
        return macongkhai;
    }

    public void setMacongkhai(byte[] macongkhai) {
        this.macongkhai = macongkhai;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Nhanvien= {" + "name=" + name + ", maNV=" + maNV + ", birth=" + birth + ", chucvu=" + chucvu + ", phone=" + phone + ", pin=" + pin + ", avatar=" + avatar + ", macongkhai=" + macongkhai + '}';
    }

    
    
    

}
