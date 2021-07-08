package BN;
import Sup.Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientList extends Vector<Patient> implements Function{
    Scanner sc = new Scanner(System.in);
    List<Patient> listBN=new Vector<>();
    public PatientList() {
    }
    

    @Override
    public void AddFromFile(String fName) {
        File f = new File(fName);
        if(!f.exists()) return;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details=bf.readLine())!=null){
                StringTokenizer stk = new StringTokenizer(details, ",");
                String ID = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                String sex = stk.nextToken();
                long birth = Long.parseLong(stk.nextToken());
                int phone = Integer.parseInt(stk.nextToken());
                String address = stk.nextToken();
                String diseaseName = stk.nextToken();
                
                Patient bn = new Patient(ID, name, sex, birth, phone, address, diseaseName);
                this.add(bn);
                listBN.add(bn);
                
            }
            bf.close();fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PatientList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PatientList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void SaveToFile(String fName) {
        if(this.isEmpty()){
            System.out.println("file rỗng");
            return;
        }
        File f = new File(fName);
        
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(Patient x: this){
                pw.print(x.getID()+","+x.getName()+","+x.getSex()+","+x.getBirth()+","+x.getPhone()+","+x.getAddress()+","+x.getDiseaseName()+"\n");
            }
            pw.close();fw.close();
        } catch (IOException ex) {
            Logger.getLogger(PatientList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void addnew() {
        String newID, newName, newSex, newAdd, newDisea;
        long newBirth;
        int newPhone, tmp;
        boolean valid = true;
        System.out.println("---Enter new info Patient---");
        do{
            System.out.print("\tID: ");
            newID=sc.nextLine().toUpperCase();
            tmp= find(newID);
            valid= newID.matches("^BN\\d{3}$");
            if(tmp>=0) System.out.println("**ID is duplicated!!");
            if(!valid) System.out.println("**Hint form ID: BN + 3 digits");
        }while(tmp>=0|| !valid);
        System.out.print("\tName: ");
        newName=sc.nextLine().toUpperCase();
        System.out.print("\tSex: ");
        newSex=sc.nextLine();
        System.out.print("\tBirth: ");
        newBirth=Long.parseLong(sc.nextLine());
        System.out.print("\tPhone: ");
        newPhone=Integer.parseInt(sc.nextLine());
        System.out.print("\tAddress: ");
        newAdd=sc.nextLine();
        System.out.print("\tName of Disease: ");
        newDisea=sc.nextLine();
        
        this.add(new Patient(newID, newName, newSex, newBirth, newPhone, newAdd, newDisea));
        System.out.println("New Patient have been added");
    }

    @Override
    public int find(String aId) {
        for(int i=0;i<this.size();i++){
            if(this.get(i).getID().equalsIgnoreCase(aId)) return i;
        }
        return -1;
    }
    public int find12(String fId){
        for(Patient x: listBN){
            if(x.getID().equalsIgnoreCase(fId)) return listBN.indexOf(x);
        }
        return -1;
    }
//String ID, String name, String sex, long birth, int phone, String address, String diseaseName
    @Override
    public void display() {
        if(this.size()==0){
            System.out.println("File rỗng!");
            return;
        }
        System.out.println("-----------------------------");
        System.out.println("ID\tName\t\t\tInformation");
        for(Patient x: this){
            System.out.println(x);
        }
    }

    @Override
    public void update() {
        
    }

    @Override
    public void remove() {
        String rID;
        System.out.print("Enter ID Patient u want to remove: ");
        rID=sc.nextLine().toUpperCase();
        int tmp=find(rID);
        if(tmp<0) System.out.println("ID Patient is not exist");
        else{
            this.remove(tmp);
            System.out.println("Patient " + this.get(tmp).getName() + rID + "has been removed");
        }
    }
    
    public String getNameP(String gId){
        int tmp=find(gId);
        return this.get(tmp).getName();
    }
    
    public String getDeseaseP(String gId){
        int tmp=find(gId);
        return this.get(tmp).getDiseaseName();
    }
}
