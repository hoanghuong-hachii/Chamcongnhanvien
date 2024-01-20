/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chamcong;

/**
 *
 * @author hoang
 */
public class info {
    private String manv, hoten, ngaysinh, chucvu, pin, sdt;
    private byte[] avatar,macongkhai;
    public info(String manv, String hoten, String ngaysinh, String chucvu, String sdt,String pin,byte[] avatar,byte[] macongkhai) {
        this.manv = manv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.chucvu = chucvu;
        this.pin = pin;
        this.sdt = sdt;
        this.avatar= avatar;
        this.macongkhai=macongkhai;
    }
    public info(){

    }

    public byte[] getMacongkhai() {
        return macongkhai;
    }

    public void setMacongkhai(byte[] macongkhai) {
        this.macongkhai = macongkhai;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "info{" + "manv=" + manv + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh + ", chucvu=" + chucvu + ", pin=" + pin + ", sdt=" + sdt + ", avatar=" + avatar + ", macongkhai=" + macongkhai + '}';
    }
    
    
}
