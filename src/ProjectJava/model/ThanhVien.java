/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.model;

/**
 *
 * @author TanMy
 */
public class ThanhVien {
    private int IDUser;
    private String FullName, Email, Phone, Address;

    public ThanhVien (){}
    public ThanhVien(String FullName, String Email, String Phone, String Address) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
    }
    public ThanhVien(int IDUser, String FullName, String Email, String Phone, String Address) {
        this.IDUser = IDUser;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
     public String toString(){
        return FullName;
    }
}
