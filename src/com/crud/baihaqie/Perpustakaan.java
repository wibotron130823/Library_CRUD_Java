package com.crud.baihaqie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Perpustakaan {

    public static void main(String[] args) throws IOException {
        Scanner UserInput = new Scanner (System.in);
        int YourChoice;
        boolean isContinue = true;
        while(isContinue) {
            ClearScreenTerminal(); // in order to close the previous very first input that user input before
            System.out.println("\t========BOOK-DATABASE========\n");
            System.out.println("Welcome to Bai-Books guys !\nHere are what you can do today : \n");
            System.out.println("1.\tSee All Books");
            System.out.println("2.\tFind Books");
            System.out.println("3.\tAdd Books");
            System.out.println("4.\tDelete Books");

            System.out.print("\nYour Choice [1-4] : ");
            YourChoice = UserInput.nextInt();
            System.out.println();

            switch (YourChoice) {
                case 1:
                    System.out.println("==========");
                    System.out.println("BOOK LISTS");
                    System.out.println("==========");
                    //show data
                    ShowDatas();
                    break;
                case 2:
                    System.out.println("==============");
                    System.out.println("SEARCH BOOKS :");
                    System.out.println("==============");
                    //find data
                    break;
                case 3:
                    System.out.println("=========");
                    System.out.println("ADD BOOKS");
                    System.out.println("=========");
                    //change data
                    break;
                case 4:
                    System.out.println("============");
                    System.out.println("DELETE BOOKS");
                    System.out.println("============");
                    //delete data
                    break;
                default:
                    System.err.println("\nWarning : Only choose in range [1-4] !");
                    break;
            }

            isContinue = YesOrNoFunct("Wanna do anything else (Y/N) ? : ");
        }

    }

    public static void ClearScreenTerminal () throws IOException {
        try {
            new ProcessBuilder("cmd" , "/c" , "cls").inheritIO().start().waitFor();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void ShowDatas() throws IOException {
        FileReader fileInput;
        BufferedReader bufferedInput;

        try {
             fileInput = new FileReader("database.txt");
             bufferedInput = new BufferedReader(fileInput);
        }
        catch(Exception e){
            //Setelah ada kesalahan terdetect kita harus tambah buku dulu ke dalam database.txt
            System.err.println("DataBase is not found, please Add the datas first !");
            return;
        }
        int num = 1;
        String data = bufferedInput.readLine();
        StringTokenizer stringToken = new StringTokenizer(data,",");
        System.out.print("\n| No |\tRealesed Year |\tAuthor               |\tPublishing House    |\tTitle                            |\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        while(stringToken.hasMoreTokens()) {
            stringToken.nextToken(); // so that this kind of thing 'fiersabesari_2012_1' doesnt appear
            System.out.printf("| %2d |", num);
            num++;
            System.out.printf("\t\t%5s\t  |", stringToken.nextToken());
            System.out.printf(" %-20s |", stringToken.nextToken());
            System.out.printf(" %-20s |", stringToken.nextToken());
            System.out.printf(" %-34s |", stringToken.nextToken());
            System.out.println("\n----------------------------------------------------------------------------------------------------------");
            data = bufferedInput.readLine();
             stringToken = new StringTokenizer(data,",");
        }




    }

    public static boolean YesOrNoFunct (String message) {
        //change isYourChoice condition if the input is not (y or Y)
        Scanner UserInput = new Scanner (System.in);
        System.out.print(message);
        String UserChoice = UserInput.next();
        while(! (UserChoice.equalsIgnoreCase("Y") || UserChoice.equalsIgnoreCase("N")) ) {
            System.err.print("Warning : Input invalid, please re-input with (Y/N) only : ");
            UserChoice = UserInput.next();
            System.out.println();
        }
        if (UserChoice.equalsIgnoreCase("N")) {
            return false;
        }
        return UserChoice.equalsIgnoreCase("Y");
    }

}
