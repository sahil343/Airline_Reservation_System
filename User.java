package pack;                                   //defines class in package "pack"
import java.io.*;
import java.util.*;
public class User{
    Scanner sc=new Scanner(System.in);
    String Name;                                //Class variables to store Passenger's Details 
    int age;
    long contact;
 
    public String EnterDetails(){               //method to scan Passenger's Details from console
        String Output=new String();
        System.out.print("\n---------------------------------------------");
       System.out.print("\nPlease Provide Passenger's Details Below : ");
       System.out.print("\n---------------------------------------------");
       System.out.print("\n\tName : ");
       Name=sc.nextLine();
       System.out.print("\n\tAge : ");
       age=sc.nextInt();
       System.out.print("\n\tContact Number : ");
       contact=sc.nextLong();
       sc.nextLine();
       System.out.print("\n---------------------------------------------");
       return Name+" "+Integer.toString(age)+" "+Long.toString(contact);         //returns String containing Name, Age and Contact number of Passenger
    }
 
    public String DATE() {                      //method to take input of Date and return in form of String
        String d=new String();
        boolean flag1 = true;
 
        while (flag1) {
            int flag = 0, conf = 0, leap = 0, n = 0;
            System.out.print("\n\tEnter Date (DD/MM/YYYY) : ");
            d = sc.nextLine();
            String str[] = d.split("/");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            if ((c % 400 == 0 || c % 4 == 0) && c % 100 != 0) {
                leap = 1;
            }
            if (c >= 2021 && c <= 2030) {
                if (b >= 1 && b <= 12) {
                    conf = 1;
                    switch (b) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12: {
                            n = 31;
                            break;
                        }
                        case 4:
                        case 6:
                        case 9:
                        case 11: {
                            n = 30;
                            break;
                        }
                        case 2: {
                            if (leap == 1) n = 29;
                            else n = 28;
                            break;
                        }
                    }
                    if (a >= 1 && a <= n) flag = 1;
                }
            }
            if (flag == 1 && conf == 1) {
                flag1=false;
            } else {
                System.out.print("\n\nYou have entered Invalid Date..\n");  //Prints Error Message if User enteres INVALID date and asks to Insert date again
               flag1=true;
            }
        }
        return d;
    }
}