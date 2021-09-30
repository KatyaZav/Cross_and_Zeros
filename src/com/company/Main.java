package com.company;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Main {

    public static char[][] field = new char[3][3];
    public static int Size = 3;
    public static String[] Congratulation = new String[]{"молодец-огурец!","победил! Поздравляшки!",
            "- герой этого поля. Молодец!", "- чемпион! Гип-гип Ура!",
            "- победитель! Шампанского!", "игрок победитель!", "победил, а у меня закончились поздравления",
            "окончил игру с победой!","получает шоколадку за победу", "WINNER"};

    public static void main(String[] args) {

        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++) {
                field[i][j]='.';
            }
        }
        boolean actor = true;

        while(true) {

            if (actor){
                System.out.println("Введите ваши координаты, игрок Первый");
                InputData(1);
                actor = false;
                PrintPole(field);
            }
            else{
                System.out.println("Введите ваши координаты, игрок Второй");
                InputData(2);
                actor = true;
                PrintPole(field);
            }

            if (IsWinPosition(1)) {
                //System.out.println("Первый молодец-огурец!");
                break;
            }
            if (IsWinPosition(2)){
                //System.out.println("Второй молодец-огурец!");
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
            if (x>=3 || x<0 || y>=3 || y<0){
                System.out.println("Не позволю!");
            }
            else if (field[x][y] != '.') {
                System.out.println("Поле занято! Пропуск хода за невнимательность! Шутка, жду новое значение"); //пофиксить
            }
            else break;
        }
        if (player == 1){
            field[x][y] = 'X';
        }
        else{
            field[x][y] = 'O';
        }
    }

    public static boolean IsWinPosition(int player){
       char ch;
        if (player == 1)
            ch = 'X';
        else ch = 'O';

        if (CheckDiagonals(ch)){
            MakeCongratulation(player);
            return true;
        }
        if (CheckLines(ch)){
            MakeCongratulation(player);
            return true;
        }
        return false;
    }

    public static boolean CheckDiagonals(char ch){
        int dial1,dial2;
        dial1 = 1;
        dial2 = 1;
        for (int i = 0; i<Size;i++){
            if (field[i][i]==ch){
                dial1 *= 1;
            }
            else dial1 *= 0;

            if (field[i][Size-1-i]==ch){
                dial2 *= 1;
            }
            else dial2 *= 0;
        }
        if (dial1 == 1 || dial2 == 1)
            return true;
        else return false;
    }

    public static boolean CheckLines(char ch){
        int check_i, check_j;
        for (int i = 0; i<Size;i++){
            check_i = 1;
            check_j = 1;
            for (int j = 0;j<Size;j++){
                if (field[i][j]!=ch){
                    check_i *= 0;
                }
                if (field[j][i]!=ch){
                    check_j *= 0;
                }
            }
            if (check_i == 1 || check_j == 1)
                return true;
        }
        return false;
    }

    public static void PrintPole(char[][] pole){
        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                System.out.print(pole[i][j]);
                }
            System.out.println();
        }
    }

    public static void MakeCongratulation(int player){
        String name;
        if (player == 1)
            name = "Первый";
        else
            name = "Второй";
        System.out.println(name + " "+ Congratulation[(int)(Math.random()*(Congratulation.length-1))]);
    }
}