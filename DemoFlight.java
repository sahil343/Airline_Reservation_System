import pack.User;                                   //imports User class file from package "pack"
import pack.Login;                                  //imports Login class file from package "pack"
import pack.FileHandling;                           //imports FileHandling class file from package "pack"
import java.io.*;
import java.util.*;

public class DemoFlight{
    public static void main(String[] args) {
 
        File myfile=new File("Flight.txt");         //File object which points to Flight file in same folder 
        User U=new User();                          //new object of User class to take passenger details and date(with validation) from user
        Scanner sc=new Scanner(System.in);          //new object of scanner class to take input from console
        FileHandling op=new FileHandling();         //new object of FileHandling class to perfrom all File I/O operaions(Read Flight Schedule,append in Passenger Record,etc)
        int p,q,r;String upd;
        op.starter();                               
        Login T=new Login();                        //new object of Login class to perform Sign up and Login Operations
        label:while(true){
            System.out.println("\n\n");
           System.out.println("\t\t\t\t\t\t\t*********************************************");
           System.out.println("\t\t\t\t\t\t\t*  1 : Search Flights                       *");
           System.out.println("\t\t\t\t\t\t\t*  2 : Cancel Booked Tickets                *");
           System.out.println("\t\t\t\t\t\t\t*  3 : Book your Ticket with Flight Number  *");
           System.out.println("\t\t\t\t\t\t\t*  4 : Print Booked Tickets                 *");
           System.out.println("\t\t\t\t\t\t\t*  5 : Exit                                 *");
           System.out.print("\t\t\t\t\t\t\t*********************************************\n\n\t\t\t\t\t\t\tEnter Your Choice :: ");
            p=sc.nextInt();
 
            if(p == 1){
                System.out.print("\n\tEnter Location from Where you want to board Flight :: ");
               String src=sc.next();
               System.out.print("\n\tEnter Destination of your journey :: ");
               String dest=sc.next();
 
                boolean flag=op.read(src,dest,myfile);                          //prints all available flights with user inserted Source and Destination and returns true,and false if there are no such flights
                if(flag){
                    System.out.println("\t\t\t\t\t\t\t************************************");
                   System.out.println("\t\t\t\t\t\t\t*  1 : Book Flight from List       *");
                   System.out.println("\t\t\t\t\t\t\t*  2 : Go To Previous menu         *");
                   System.out.print("\t\t\t\t\t\t\t************************************\n\n\t\t\t\t\t\t\tEnter Your Choice :: ");
                    r=sc.nextInt();
                    if(r == 1){
                        upd=T.menu();
                        if(!upd.equals("EMPTY"))
                        {
                            System.out.print("\n\tEnter Flight Number : ");
                        int flightnumber=sc.nextInt();
                        String confirm=op.read(flightnumber,myfile);      //finds Flight details with User entered Flight number, which return "CAN'T FOUND" String if Flight number is Invalid 
                        if(!confirm.equalsIgnoreCase("CAN'T FOUND")){
                            System.out.print("\n\tEnter Total number of Passengers for Ticket Booking : ");
                           q = sc.nextInt();
                           String temp = new String();
                           if(q>1)temp=q+" passengers are";
                           else temp=q+" passenger is";
                            String Date=U.DATE();                                   //takes Date from user by applying validations
                            for(int i=1;i<=q;i++){
                                String kj=U.EnterDetails();                         //Takes Passenger Details(Name, Age , Contact number) for Ticket Booking 
                                op.appendinlist(Date+" "+kj+" "+confirm+" "+upd);   //Appends data of passengers with confirmed ticket in Passenger record along with Flight Details,User-name and password
                            }
                            System.out.println("\n\t\t\t\t\t\t-----------------------------------------------------------");
                           System.out.println("\t\t\t\t\t\tTicket for "+temp+" Booked Successfully..");
                           System.out.println("\t\t\t\t\t\t-----------------------------------------------------------");
                        }                        
                        }
                        else{
                            System.out.println("\n\tYou Have Entered INVALID Flight Number ..");    //prints Error message if user enters flight number out of database 
                        }
                    }
                    else{
                        continue label;                      //If user wants to go to Previous menu then we can simply continue do..while loop of main menu with label
                    }
                }
                else{                            
                    System.out.println("\n\t\tSorry..There are no Flights available from "+src+" to "+dest);
                }
            }
            else if(p == 2){
                String conf=T.login();  //takes user-name and password and finds in UserDetails text file, "USERNAME CAN'T FOUND" String is returned if User-name not found in file
                if(!conf.equals("USERNAME CAN'T FOUND")){
                    System.out.print("\n\tEnter number of Ticket That You want to Cancel : ");
                    sc.nextLine();
                    String delete=sc.nextLine();
                    boolean flag=op.renewrecord(delete,conf);    //finds ticket having user entered number with current user name and password,returns false if not found
                    if(flag){
                        System.out.println("\t\t\t\t\t\t-----------------------------------------------------------------");
                        System.out.println("\t\t\t\t\t\tTicket : "+delete+" is Cancelled Sucessfully..");
                        System.out.println("\t\t\t\t\t\t-----------------------------------------------------------------");
                    }
                    else{
                        System.out.println("\n\tYou Have Entered INVALID Ticket number..");
                    }
                }
                else{
                    System.out.println("\n\tUser Name doesn't exist..");
                    continue label;
                }
            }
            else if(p == 3){
                System.out.print("\n\tEnter Flight Number : ");
                sc.nextLine();
                String flightnumber=sc.nextLine();
                String output=op.read(flightnumber,myfile);                 //finds Flight details with User entered Flight number, which return "CAN'T FOUND" String if Flight number is Invalid 
 
                if(!output.equals("CAN'T FOUND")){
                    upd=T.menu();
                    if(!upd.equals("EMPTY"))
                    {
                        System.out.print("\n\tEnter Total number of Passengers for Ticket Booking : ");
                   q = sc.nextInt();
                   String temp = new String();
                    if(q>1)temp=q+" passengers are";
                    else temp=q+" passenger is";
                    String Date=U.DATE();                                   //takes Date from user by applying validations
                    for(int i=1;i<=q;i++){
                        System.out.println("-----------------------------------------------------------");
                        String kj=U.EnterDetails();                         //Takes Passenger Details(Name, Age , Contact number) for Ticket Booking 
                        op.appendinlist(Date+" "+kj+" "+output+" "+upd);    //Appends data of passengers with confirmed ticket in Passenger record along with Flight Details,User-name and password
                    }
                    System.out.println("\n\t\t\t\t\t\t-----------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\tTicket for "+temp+" Booked Successfully..");
                    System.out.println("\t\t\t\t\t\t-----------------------------------------------------------");
                    }
                }
                else{
                    System.out.println("\n\tYou Have Entered INVALID Flight Number ..");
                }
            }
            else if(p == 4){
                T.print_Tickets();                                          //method to print Previously booked Tickets
            }
            else{
                break;
            }
        }
    }
}

