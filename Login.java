package pack;                            //defines class in package "pack"
import java.io.*;
import java.util.*;
public class Login{
    private String Username = new String();     //String object to store User Name
    private String Password = new String();     //String object to store Password
    Console c=System.console();         //new object of console to take password from user on console
 
    public void print_Tickets()         //method to print all tickets booked from particular account
   {
    String str = login();
    
    if(!str.equals("USERNAME CAN'T FOUND")){
        String user[] = str.split(" ");
        String s[] = new String[10];
    try {
        boolean flag=false;
        String line=new String();
        Scanner sc=new Scanner(new File("record.txt"));
        line = sc.nextLine();
        line = sc.nextLine();
        line = sc.nextLine(); 
        while(sc.hasNextLine()) {        
            line = sc.nextLine();
            line = line.replaceAll("\\s+", " ");
            s = line.split(" ");
            if (s.length>5 && s[8].equals(user[0]) && s[9].equals(user[1])) {       //prints ticket if user entered username and password matches with ticket
                System.out.println("\n\n\t\t\t\t\t*********************************************************************************");
                System.out.println("\t\t\t\t\t*\tTicket Number : "+s[7]+"\t\t\t\t\t\t\t*");
                System.out.println("\t\t\t\t\t*\tName : "+s[1]+"\t\tAge : "+s[2]+"\tContact Number : "+s[3]+"\t*");
                System.out.println("\t\t\t\t\t*\tDate : "+s[0]+"\tTime : "+s[6]+"\t\t\t\t\t*");
                System.out.println("\t\t\t\t\t*********************************************************************************");
                System.out.println("\t\t\t\t\t*\tSource : "+s[4]+"\t\tDestination : "+s[5]+"\t\t\t\t*");
                System.out.println("\t\t\t\t\t*********************************************************************************");
            }
        }
        
    } catch (FileNotFoundException e) {                  //catches exception of FileNotFoundExcception class
        e.printStackTrace();
    }
    
    }
    else{
        System.out.println("\n\tUser Name doesn't exist..");
        return;
    }
    
   }
 
    public String signup(){                             //method to create new account with Username and password
        Scanner sc=new Scanner(System.in);
        System.out.print("\n\tUser Name :: ");
        Username=sc.next();
        System.out.print("\n\tSet Your Password :: ");
        char pass1[]=c.readPassword();                  //passoword is stored with method of console class readPassword() which returns character array, then it is converted to String
        Password=String.valueOf(pass1);
 
        char pass2[];String str;
 
        System.out.print("\n\tEnter Confirm Password : ");
       pass2=c.readPassword();
       str=String.valueOf(pass2);

       while(!str.equals(Password)){
           System.out.print("\n\tYou have to Enter 'Confirm Password' Again.. : ");
           pass2=c.readPassword();
           str=String.valueOf(pass2);
        }
 
        try {
            FileWriter fp=new FileWriter("UserDetails.txt",true);
            fp.write(Username+" "+Password+"\n");       //User name and password of new account are added in UserDetails text file
            fp.close();
        } catch (IOException e) {
            System.out.println("Can't Open File from Directory");
            e.printStackTrace();
        }
        System.out.println("\t\t\t***************************************");
         System.out.println("\t\t\tYou Have Successfully Signed up");
         System.out.println("\t\t\t***************************************");
        System.out.println("");
        return Username+" "+str;                        //returns String containing Username and password
    }
 
    public String login(){                              //method to login with already existing user
        Scanner sc=new Scanner(System.in);
        boolean flag=false;
        String line=new String();
        System.out.print("\n\tUser-Name : ");
       Username=sc.next();
 
        String str=new String();
        File fp=new File("UserDetails.txt");
        try {
            sc=new Scanner(fp);
            while(sc.hasNextLine()){
                line=sc.nextLine();
                String s[]=line.split(" ");
                if(s[0].equals(Username)){              //searches for user entered username in file and then compares with relevant Password
                    str=s[1];
                    flag=true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE IS UNABLE TO OPEN");
            e.printStackTrace();
        }
 
        if(flag){
            System.out.print("\n\tPassword : ");
            char pass1[]=c.readPassword();
            Password=String.valueOf(pass1);
 
            while(!str.equals(Password) && flag) {
                System.out.println("\n\tYou Have Entered INCORRECT Password..\n");
                System.out.print("\n\tPlease Again Enter Your Password : ");
                pass1=c.readPassword();
                Password=String.valueOf(pass1);
            }
            System.out.println("\n\t\t\t***************************************");
            System.out.println("\t\t\tYou Have Successfully Logged in");
            System.out.println("\t\t\t***************************************");
            return Username+" "+String.valueOf(pass1);
        }
        else{
            System.out.println("\n\tYou Have to Sign up First ..");
            return "USERNAME CAN'T FOUND";              //returns "USERNAME CAN'T FOUND" String if Username doesn't found in list
        }
    }
 
    public String menu(){                               //method to Show menu for Sign up and Login and take User's choice
        boolean flag=true;
        String output=new String();
        output="EMPTY";
        Scanner scanner=new Scanner(System.in);
        while(flag){
            System.out.println("\t\t\t\t\t\t\t*****************************");
            System.out.println("\t\t\t\t\t\t\t*  1 : Sign up              *");
            System.out.println("\t\t\t\t\t\t\t*  2 : Login                *");
            System.out.println("\t\t\t\t\t\t\t*  3 : Go To Previous menu  *");
            System.out.print("\t\t\t\t\t\t\t*****************************\n\n\t\t\t\t\t\t\tEnter Your Choice :: ");
            int a = scanner.nextInt();
            if (a == 1){
                output=signup();
                flag=false;
            }
            else if(a == 2)
            {
                output=login();
                if(output.equals("USERNAME CAN'T FOUND")){
                    //do nothing
                }
                else{
                    flag=false;
                }
                
            }
            else{
                break;
            }
        }
        return output;
    }
}