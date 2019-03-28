/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectJava.model;

import java.util.ArrayList;

/**
 *
 * @author Phat
 */
public class CBONXBData {
    private int ID;
    private String tennxb;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTennxb() {
        return tennxb;
    }

    public void setTennxb(String tennxb) {
        this.tennxb = tennxb;
    }

    public CBONXBData(int ID, String tennxb) {
        this.ID = ID;
        this.tennxb = tennxb;
    }
    @Override
    public String toString(){
        return tennxb;
    }
    
    
}
