package com.company;
import java.util.Scanner;

public class Main {

    public static char[][] pole = new char[3][3];

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++) {
                pole[i][j]='.';
            }
        }
        boolean actor = true;

        while(true) {

            if (actor){
                System.out.println("Введите ваши координаты, игрок Первый");
                InputData(1);
                actor = false;
                PrintPole(pole);
            }
            else{
                System.out.println("Введите ваши координаты, игрок Второй");
                InputData(2);
                actor = true;
                PrintPole(pole);
            }

            if(IsWin(1)){
                System.out.println("Первый молодец-огурец!");
                break;
            }
            if (IsWin(2)){
                System.out.println("Второй молодец-огурец!");
                break;
            }

        }
    }
    
    public static void InputData(int player){
        int x,y;
        Scanner in = new Scanner(System.in);
        while(true) {
            x = in.nextInt();
            y = in.nextInt();
            if (x>3 || x<0 || y>3 || y<0){
                System.out.println("Не позволю!");
            }
            else if (pole[x][y] != '.') {
                System.out.println("Поле занято! Пропуск хода за невнимательность! Шутка, жду новое значение"); //пофиксить
            }
            else break;
        }
        if (player == 1){
            pole[x][y] = 'X';
        }
        else{
            pole[x][y] = 'O';
        }

    }

    public static boolean IsWin(int player){
        char ch;
        if (player == 1)
            ch = 'X';
        else ch = 'O';
        if ((pole[0][0]==ch && pole[1][1]==ch && pole[2][2]==ch)||
                (pole[0][0]==ch && pole[0][1]==ch && pole[0][2]==ch)||
                (pole[1][0]==ch && pole[1][1]==ch && pole[1][2]==ch)||
                (pole[2][0]==ch && pole[2][1]==ch && pole[2][2]== ch)||
                (pole[0][0]==ch && pole[0][1]==ch && pole[0][2]==ch)||
                (pole[1][0]==ch && pole[1][1]==ch && pole[1][2]==ch)||
                (pole[2][0]==ch && pole[2][1]==ch && pole[2][2]==ch)){
            return true;
        }
        else return false;
    }

    public static void PrintPole(char[][] pole){
        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                System.out.print(pole[i][j]);
            }
            System.out.println();
        }
    }
}