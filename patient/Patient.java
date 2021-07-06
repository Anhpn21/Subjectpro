/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LAPTOP VINH HA
 */
public class Patient extends Information{
    private String diseaseName;

    public Patient() {
    }

    public Patient(String ID, String name, String sex, long birth, int phone, String address, String diseaseName) {
        super(ID, name, sex, birth, phone, address);
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return  s + " ||  " + diseaseName ;
    }
    
    
}
