package Menu;
import BN.Patient;
import BN.PatientList;
import BV.Doctor;
import BV.DoctorList;
import BN.Drug;
import BN.DrugList;
import BN.Donthuoc;
        

import java.util.Scanner;

public class Menu {

    static void menuMain() {
        System.out.println("-==========Menu==========-");
        System.out.println("-= 1. QL Bác sĩ         =-");
        System.out.println("-= 2. QL Bệnh Nhân      =-");
        System.out.println("-= 3. QL Thuốc          =-");
        System.out.println("-= 4. Exit              =-");
        System.out.println("-========================-");
    }

    static void menuSub(int luachon) {
        switch (luachon) {
            case 1:
                System.out.println("-==========QL Bác sĩ==========-");
                System.out.println("-= 1. Thêm Bác sĩ mới        =-");
                System.out.println("-= 2. Xóa Bác sĩ             =-");
                System.out.println("-= 3. Hiển thị tất cả Bác sĩ =-");
                System.out.println("-= 4. Lưu vào File           =-");
                System.out.println("-= 5. Trở lại                =-");
                System.out.println("-=============================-");
                break;
            case 2:
                System.out.println("-==========QL Bệnh nhân==========-");
                System.out.println("-= 1. Thêm Bệnh nhân mới        =-");
                System.out.println("-= 2. Viết đơn thuốc            =-");
                System.out.println("-= 3. Xóa Bệnh nhân(nếu cần)    =-");
                System.out.println("-= 4. Hiển thị tất cả BN        =-");
                System.out.println("-= 5. Lưu vào File              =-");
                System.out.println("-= 6. Trở lại                   =-");
                System.out.println("-================================-");
                break;
            case 3:
                System.out.println("-==========  QL Thuốc  ==========-");
                System.out.println("-= 1. Thêm Thuốc mới            =-");
                System.out.println("-= 2. Xóa Thuốc(nếu cần)        =-");
                System.out.println("-= 3. Hiển thị tất cả số Thuốc  =-");
                System.out.println("-= 4. Lưu vào File              =-");
                System.out.println("-= 5. Trở Lại                   =-");
                System.out.println("-================================-");
                break;
        }
    }

    public static void main(String[] args) {
        boolean tmp=true;
        int luachon;
        String ans;
        do {
            menuMain();
            Scanner sc = new Scanner(System.in);
            System.out.print("Mời bạn nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice==4) return;
            menuSub(choice);
            
            String fileDoctor = "test.txt";
            String fileDrug = "test1.txt";
            String filePatient = "test2.txt";
            String fileDon= "test3.txt";
            
            PatientList lPatient=new PatientList();
            DrugList lDrug = new DrugList();
            DoctorList lDoctor = new DoctorList();
            Donthuoc lDon = new Donthuoc();
            
            lDoctor.AddFromFile(fileDoctor);
            lDrug.AddFromFile(fileDrug);
            lPatient.AddFromFile(filePatient);
            lDon.AddFromFile(fileDon);
            boolean dem=true;
            
            do {
                System.out.print("Mời bạn nhập lựa chọn: ");
                luachon = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    switch (luachon) {
                        case 1:
                            lDoctor.addnew();
                            dem = true;
                            break;
                        case 2:
                            lDoctor.remove();
                            dem = true;
                            break;
                        case 3:
                            lDoctor.display();
                            break;
                        case 4:
                            lDoctor.SaveToFile(fileDoctor);
                            dem=false;
                            break;
                        case 5:
                            luachon=-1;
                            if(dem){
                                System.out.print("Bạn muốn lưu vào file không(C/K)? ");
                                //Scanner sc1 = new Scanner(System.in);
                                ans = sc.nextLine().toUpperCase();
                                if (ans.startsWith("C")) {
                                    lDoctor.SaveToFile(fileDoctor);
                                }
                                System.out.println("Đã được lưu vào File!!");
                            }
                            break;
                            

                    }
                } else if (choice == 2) {
                    //System.out.println("code Quản lý BN");
                    switch(luachon){
                        case 1:
                            lPatient.addnew();
                            dem=true;
                            break;
                        case 2:
                            lDon.addnew();
                            break;
                        case 3:
                            lPatient.remove();
                            dem=true;
                            break;
                        case 4:
                            lPatient.display();
                            break;
                        case 5:
                            lPatient.SaveToFile(filePatient);
                            dem=false;
                            break;
                        case 6:
                            luachon=-1;
                            if(dem){
                                System.out.print("Bạn có muốn lưu vào File không(C/K)? ");
                                ans=sc.nextLine().toUpperCase();
                                if(ans.startsWith("C")){
                                    lPatient.SaveToFile(filePatient);
                                }
                                System.out.println("Đã được lưu vào File!!");
                            }
                            break;
                    }
                } else if (choice == 3) {
                    //System.out.println("code QL thuốc");
                    
                    switch(luachon){
                        case 1: //thêm thuốc
                            lDrug.addnew();
                            dem=true;
                            break;
                        case 2: // xóa thuốc
                            lDrug.remove();
                            dem=true;
                            break;
                        case 3: //hiển thị
                            lDrug.display();
                            break;
                        case 4: //lưu file
                            lDrug.SaveToFile(fileDrug);
                            dem=false;
                            break;
                        case 5: // Back
                            luachon=-1;
                            if(dem){
                                System.out.print("Bạn có muốn lưu vào File không(C/K)? ");
                               
                                ans=sc.nextLine().toUpperCase();
                                if(ans.startsWith("C")){
                                    lDrug.SaveToFile(fileDrug);
                                }
                                System.out.println("Đã được lưu vào File!!");
                            }  
                            break;     
                    }
                    
                } else {
                    tmp=false;
                    return;
                }
                System.out.println("\n");
            } while (luachon>0 && luachon <5);
            System.out.println("\n\n\n");
        } while (tmp=true);

    }
}
