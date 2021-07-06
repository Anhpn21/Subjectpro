/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LAPTOP VINH HA
 */
public class Drug {
    private String name,ID;
    private double weight;
    private int price;

    public Drug() {
    }

    public Drug(String ID, String name,double weight, int price) {
        this.ID=ID;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        
        return ID +"\t" + name + "\t"+  weight + "  3|"+ price ;
    }
    
    
}
