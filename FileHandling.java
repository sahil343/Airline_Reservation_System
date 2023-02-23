package pack;                           //defines class in package "pack"
import java.io.*;
import java.util.*;
public class FileHandling{
    public void starter(){
        Scanner sc=null;
        String first="------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        String second="DATE\t\tNAME\t\tAGE\t\tCONTACT\t\tSRC\t\tDEST\t\tTIME\t\tTICKETNUMBER\tUSERNAME\tPASSWORD";
        String third="------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        boolean flag=false;
        String line1,line2,line3;
        FileReader f= null;
        try {
            f = new FileReader("record.txt");
            sc=new Scanner(f);
            line1=sc.nextLine();line2=sc.nextLine();line3=sc.nextLine();
            if(line1.equalsIgnoreCase(first) && line2.equalsIgnoreCase(second) && line3.equalsIgnoreCase(third)){
                flag=true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
     
        if(!flag){
            try {
                FileWriter fp=new FileWriter("record.txt",true);
                fp.write("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                fp.write("DATE\t\tNAME\t\tAGE\t\tCONTACT\t\tSRC\t\tDEST\t\tTIME\t\tTICKETNUMBER\tUSERNAME\tPASSWORD\n");
                fp.write("------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                fp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     }
 
    public String read(int op,File fp){               //searchs for Flight with flight number and returns String containing Source,Destination and Departure time of it,"CAN'T FOUND" String is returned if not found
        boolean flag=false;
        String p=new String();
        try {
            String line;
            Scanner sc=new Scanner(fp);
            while(sc.hasNextLine()){
                line=sc.nextLine();
                line=line.replaceAll("\\s+"," ");
                String s[]=line.split(" ");
                if(s[3].equalsIgnoreCase(Integer.toString(op))){
                    p=s[0]+" "+s[1]+" "+s[2];
                    flag=true;
                    break;
                }
            }
            if(!flag){
                p="CAN'T FOUND";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            p="CAN'T FOUND";
        }
        return p;
    }
 
    public boolean read(String i,String j,File fp){          //method with boolean return type to check and print Available flights from user entered Source to Destination
        try {
            boolean flag=false;
            String line;
            Scanner sc=new Scanner(fp);
            System.out.println("\n\n\t\t\t\t\t********************************************************************");
           System.out.println("\t\t\t\t\t*  Source  |  Destination  |   Flight Number  |    Departure Time  *");
           System.out.println("\t\t\t\t\t********************************************************************");
           while(sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", " ");
                String s[] = line.split(" ");
                if (s[0].equalsIgnoreCase(i) && s[1].equalsIgnoreCase(j)) {
                    flag = true;
                    System.out.println("\t\t\t\t\t*  "+s[0]+"\t  "+s[1]+"\t\t  "+s[3]+"\t\t   "+s[2]+"\t   *");
                }
            }
            System.out.println("\t\t\t\t\t********************************************************************\n\n");
           if(!flag){
                return false;
            }
            else{
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    public boolean renewrecord(String s,String upd){        //method for finding line(with user entered ticket number and corresponding user-name,password) and delete it
        Scanner sc=null;String line,jk;
        String str[] = upd.split(" ");
        File fp=new File("temp\\temp.txt");
        try {
            fp.createNewFile();
        } catch (IOException e) {
            System.out.println("Can't Open Mentioned File");
           e.printStackTrace();
        }
 
        File read=new File("record.txt");
        try {
            FileWriter fp2=new FileWriter("temp\\temp.txt");
            sc=new Scanner(read);
            while(sc.hasNextLine()){
                fp2.write(sc.nextLine());
                fp2.write("\n");
            }
            fp2.close();
        } catch (IOException e) {
            System.out.println("ENABLE TO WRITE IN FILE");
            e.printStackTrace();
        }
 
        File f=new File("temp\\temp.txt");
        boolean flag=false;
        try {
            FileWriter fnl=new FileWriter("record.txt");
            sc=new Scanner(f);
 
            while(sc.hasNextLine()){
                line=sc.nextLine();
 
                jk=line.replaceAll("\\s+"," ");
                String s1[]=jk.split(" ");
                if(s1.length >= 8 && s1[7].equals(s) && s1[8].equals(str[0]) && s1[9].equals(str[1])){
                    flag=true;
                    //delete this record
                }
                else{
                    fnl.write(line);
                    fnl.write("\n");
                }
            }
            fnl.close();
        } catch (IOException e){
            System.out.println("ENABLE TO WRITE IN FILE");
            e.printStackTrace();
        }
 
        return flag;
    }
 
    public void appendinlist(String append){                    //to append data of newly booked ticket in record text file
        String p;
        try {
            FileWriter fp=new FileWriter("record.txt",true);
            String s[]=append.split(" ");
            for(int i=0;i<s.length;i++){
                if(i == 7){
                    Random rand=new Random();
                    int y= rand.nextInt(999999)+1;
                    p=Integer.toString(y);
                    p=String.format("%-16s",p);
                    fp.write(p);
                }
                p=String.format("%-16s",s[i]);
                fp.write(p);
            }
            fp.write("\n");
            fp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public String read(String number,File fp){          //searchs for Flight with flight number and returns String containing Source,Destination and Departure time of it,"CAN'T FOUND" String is returned if not found
        String output=new String();
        try {
            boolean flag=false;
            String line=new String();
            Scanner sc=new Scanner(fp);
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", " ");
                String s[] = line.split(" ");
                if (s[3].equals(number)) {
                    flag=true;
                    output=s[0]+" "+s[1]+" "+s[2];
                    break;
                }
            }
            if(!flag){
                return "CAN'T FOUND";
            }
            else{
                return output;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "CAN'T FOUND";
        }
    }
}