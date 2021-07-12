package BN;


import Sup.Function;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrugList extends Vector<Drug> implements Function {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> listDon = new ArrayList<>();
    int index=0;
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
            System.out.println("Arr rỗng");
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
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, e);
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
    
   
    public int findN(String fName){
        for(int i=0;i<this.size();i++){
            if(this.get(i).getName().equalsIgnoreCase(fName)) return i;
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
        String uId;
        System.out.print("Enter ID Drug u want to update: ");
        uId=sc.nextLine().toUpperCase();
        int tmp=find(uId);
        double newWe = 0,oldWe;
        int newPr = 0, oldPr;
        if(tmp<0) System.out.println("ID Drug is not exist!");
        else{
            oldWe=this.get(tmp).getWeight();
            oldPr=this.get(tmp).getPrice();
            System.out.print("Old Weight: " + oldWe+", new Weight: ");
            newWe=Double.parseDouble(sc.nextLine());
            System.out.print("Old Price: " + oldPr+", new Price: ");
            newPr=Integer.parseInt(sc.nextLine());
        }
        this.get(tmp).setWeight(newWe);
        this.get(tmp).setPrice(newPr);
        System.out.println("Drug: "+uId+" "+this.get(tmp).getName()+" has been updated.");
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
            System.out.println("Drug ID: "+rID+" has been removed!");
        }
    }
    
    
    //Don thuoc
    
    public void AddFileDon(String fName) {
        
        try {
            FileReader fr = new FileReader(f); 
                BufferedReader bf = new BufferedReader(fr);
                String s=null,detail;
                while((detail=bf.readLine())!= null){
                    StringTokenizer st=new StringTokenizer(detail, ",");
                    if(st.hasMoreTokens()){
                        //st=new StringTokenizer(detail, ",");
                        String dId=st.nextToken().toUpperCase();
                        String dName=st.nextToken().toUpperCase();
                        String dDesease=st.nextToken();
                        String dDrug=st.nextToken().toUpperCase();
                        double dPrice=Double.parseDouble(st.nextToken());
                        s = dId+","+dName+","+dDesease+","+dDrug+","+dPrice;
                        listDon.add(index, s);
                        index++;
                    }
            }
            bf.close();fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SaveFileDon(String fName) {
        if(listDon.isEmpty()) {
            System.out.println("Arr rỗng");
            return;
        }
        File f=new File(fName);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(int i=0; i<listDon.size();i++){
                pw.println(listDon.get(i));
                
            }
            System.out.println("save success");
            pw.close();fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DrugList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addNewD(String aId, String aName, String aDes){
        System.out.println("\tName: " + aName);
        System.out.println("\tDesease: " + aDes);
        System.out.println("--Drug List--");
        System.out.println("**Do not enter ==> stop");
        String adrug,fDrug="";
        double aWei,oWei;
        double fPrice=0;
        boolean dem=true;
        while(dem){
            System.out.print("\tDrug: ");
            adrug=sc.nextLine().toUpperCase();
            if("".equals(adrug)){
                dem=false;
            }
            int tmp=findN(adrug);
            if(tmp<0) {
                System.out.println("Không tìm thấy thuốc");
                continue;
            } 
            else{
                System.out.print("\tWeight(kg): ");
                aWei=Double.parseDouble(sc.nextLine());
                oWei=this.get(tmp).getWeight();
                if(aWei<oWei){
                    this.get(tmp).setWeight(oWei-aWei);
                }else System.out.println("kho không đủ");
            }
            fPrice += (aWei*(this.get(tmp).getPrice()));
            fDrug+=adrug+"("+aWei+")" + " - "; 
        }
        fDrug=fDrug.substring(0, fDrug.length()-3);
        String s=aId + "," + aName+"," + aDes+","+fDrug+","+fPrice;
        listDon.add(index, s);
        index++;
        //list.add(dId + "," +dName+ "," +dDesease+ "," +dDrug);
        System.out.println("New Đơn thuốc has been added");
        
    }
    
    public void displayDon(){
        if(listDon.size()==0){
            System.out.println("file rỗng");
            return;
        }
        String sz="";
        String format="%-8s%-25s%-20s%-60s%s";
        System.out.println("----------------------");
        System.out.printf(format,"ID","Name","Mắc Bệnh","Đơn thuốc","Tổng giá");
        System.out.println("");
        for(String x: listDon){
            String arr[]=x.split(",");
            
            String dsID=arr[0];
            String dsName=arr[1];
            String dsDesease=arr[2];
            String donThuoc=arr[3];
            String dsPrice=arr[4];
            
            System.out.printf(format,dsID,dsName,dsDesease,donThuoc,dsPrice);
            System.out.println("");
    }
}
