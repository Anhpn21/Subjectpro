package BN;


import Sup.Function;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Donthuoc extends PatientList implements Function{
    Scanner sc = new Scanner(System.in);
    ArrayList list = new ArrayList();
    
    public Donthuoc() {
    } 

    @Override
    public void AddFromFile(String fName) {
        File f= new File(fName);
        if(!f.exists()) return;
        try {
            FileReader fr =  new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details=bf.readLine())!=null){
                StringTokenizer stk=new StringTokenizer(details,",");
                String ID=sc.nextLine().toUpperCase();
                String name=sc.nextLine().toUpperCase();
                String desease=sc.nextLine();
                String drug=sc.nextLine();
                list.add(ID+name+desease+drug);
                
            }
            bf.close();fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Donthuoc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Donthuoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SaveToFile(String fName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addnew() {
        String ID,drug;
        boolean valid = true;
        int tmp1;
        do {           
            System.out.print("--Enter ID of patient: ");
            ID=sc.nextLine().toUpperCase();
            tmp1=super.find(ID);
            valid=ID.matches("^BN\\d{3}$");
            if(tmp1<0) System.out.println("**ID patient is not exist!!");
            if(!valid) System.out.println("**Hint form ID: T + 3 digits");
        } while (tmp1<0 || !valid);
        String name=this.get(tmp1).getName();
        System.out.print("\tName: "+name);
        String disease = this.get(tmp1).getDiseaseName();
        System.out.print("\tDesease: "+disease);
        System.out.println("--Drug List: --");
        System.out.println("**Hint form: Drug name1(weight) - Drug name2(weight) ");
        drug=sc.nextLine();
        list.add(ID+","+name+","+disease+","+drug);
    }

    @Override
    public int find(String aId) {
        return super.find(aId);
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

