/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.model;

/**
 *
 * @author CONG TAO
 */
public class Sach {

    private String IDSach;
    private String tensach;
    //private CBONXBData nxb;
    private int nxb;
    private int sotrang;

    public String getIDSach() {
        return IDSach;
    }

    public void setIDSach(String IDSach) {
        this.IDSach = IDSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getNxb() {
        return nxb;
    }

    public void setNxb(int nxb) {
        this.nxb = nxb;
    }

    public int getSotrang() {
        return sotrang;
    }

    public void setSotrang(int sotrang) {
        this.sotrang = sotrang;
    }
}
