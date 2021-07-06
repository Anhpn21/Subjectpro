
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrugList extends Vector<Drug> implements Function {
    Scanner sc = new Scanner(System.in);

    public DrugList() {
    }
    
    @Override
    public void AddFromFile(String fName) {
        
        try {
            File f = new File(fName);
            if(!f.exists()) return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details=bf.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(details, ",");
                String ID = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                double weight = Double.parseDouble(stk.nextToken());
                int price = Integer.parseInt(stk.nextToken());
                
                Drug thuoc = new Drug(ID, name, weight, price);
                this.add(thuoc);
            }
            bf.close();fr.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    @Override
    public void SaveToFile(String fName) {
        if(this.isEmpty()){
            System.out.println("File rỗng");
            return;
        } 
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(Drug x: this){
                pw.print(x.getID()+","+x.getName()+","+x.getWeight()+","+x.getPrice()+"\n");
            }
            pw.close();fw.close();
        } catch (IOException e) {
            Logger.getLogger(DoctorList.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addnew() {
        String newName,newID;
        double newWeight;
        int tmp,newPrice;
        boolean valid = true;
        System.out.println("---Enter new info of Drug---");
        do{
            System.out.print("\tID: ");
            newID=sc.nextLine().toUpperCase();
            tmp=find(newID);
            valid=newID.matches("^T\\d{3}$");
            if(tmp>0) System.out.println("**ID is duplicated");
            if(!valid) System.out.println("**Hint form ID: T + 3 digits");
        }while(tmp>0|| !valid);
        System.out.print("\tName: ");
        newName=sc.nextLine().toUpperCase();
        System.out.print("\tWeight(kg): ");
        newWeight=Double.parseDouble(sc.nextLine());
        System.out.print("\tPrice(g/vnd): ");
        newPrice=Integer.parseInt(sc.nextLine());
        this.add(new Drug(newID, newName, newWeight, newPrice));
        System.out.println("New Drug has been added");
    }

    @Override
    public int find(String aId) {
        for(int i=0; i<this.size();i++){
            if(this.get(i).getID().equalsIgnoreCase(aId)) return i;
        }
        return -1;
    }

    @Override
    public void display() {
        if(this.size()==0){
            System.out.println("File rỗng");
            return;
        }
        System.out.println("-----------------------");
        System.out.println("ID\tName\t\tWeight and Price");
        for(Drug x: this){
            System.out.println(x);
        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        String rID;
        System.out.print("Enter ID Drug u want to remove: ");
        rID=sc.nextLine().toUpperCase();
        int tmp=find(rID);
        if(tmp<0) System.out.println("Drug is not existed!!");
        else{
            this.remove(tmp);
            System.out.println("Drug: " + this.get(tmp).getName()+"  ID: "+rID+" has been removed!");
        }
    }
    
}
