package com.company;
import java.io.Console;
import java.net.SocketOption;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Main {
    public static boolean AutoOn = false;
    public static boolean RealPlayerFirst = true;
    public static char[][] field;
    public static int Size = 3;
    public static boolean actor = true;

    public static String[] Congratulation = new String[]{"молодец-огурец!","победил! Поздравляшки!",
            "- герой этого поля. Молодец!", "- чемпион! Гип-гип Ура!",
            "- победитель! Шампанского!", "игрок победитель!", "победил, а у меня закончились поздравления",
            "окончил игру с победой!","получает шоколадку за победу", "WINNER"};
    public static String[] BanWords = new String[]{"Не позволю!","Жулик!", "Читер!", "Вы ошиблись",
            "Ошибка", "Я могу и буду ругаться", "Я скромно стою в стороне и слежу за порядком",
            "Прояви уважение к правилам игры!", "Ооой. Что-то пошло не так...","...",
            "Научите бота ругаться при нарушении правил...", "Кто-то очепятался",
            "Пока вы ищите ошибку, прорекламирую..."};
    public static String[] BanSen = new String[]{"А поле-то занято!",
            "Пропуск хода за невнимательность. Шутка, я теперь умею проверять ввод до послднего :)",
            "Вы ошиблись координатами. Бывает", "...", "Поле занято", "Ай, поле занято...",
            "Хочу мороженку, а не вот это вот все...", "Где-то ошибка, но где не скажу",
            "Ищите ошибку", "Кто-то очепятался", "Пока вы ищите ошибку, прорекламирую..."};
    public static String[] DrawWord = new String[]{"Шах и... Пат","Ничья!",
            "Победа, поражение... Ничья", "Ну... Поле кончилось",
            "Давайте еще партейку?"};

    public static void main(String[] args) {
        System.out.println("Правила просты: первый игрок - крестики, второй - нолики. \n" +
                "Хотите включить бота?\n"+
                "1 - да, 2 - нет. Итак?");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        if (a==1){
            System.out.println("Он глупенький, но попытается...");
            AutoOn = true;
            Loop();
        }
        else if (a==2){
            Loop();
        }
        else {
            System.out.println("Ну я же просил... Окей, еще одна попытка\n" +
                    "1 - бот, 2 - реальный игрок. Ваш выбор?");
            while (true){
                a = in.nextInt();
                if (a==1){
                    System.out.println("Он глупенький... Но постарается");
                    AutoOn = true;
                    Loop();
                    break;
                }
                if (a==2){
                    Loop();
                    break;
                }
                System.out.println("1 - бот, 2 - реал");
            }
        }
    }

    public static void Loop(){
        Scanner in = new Scanner(System.in);
        boolean end = false;
        while(true) {
            Playtime();
            System.out.println("Еще раз?" +
                    "1 - да, 2 - нет");
            while (true){
                int a = in.nextInt();
                if (a ==2){
                    System.out.println("Пока-пока!");
                    end = true;
                    break;
                }
                if (a==1){
                    System.out.println("Супер, погнали!");
                    if (AutoOn) {
                        RealPlayerFirst = !RealPlayerFirst;
                        System.out.println("Вы меняетесь с ботом местами");
                        actor = !actor;
                    }
                    break;
                }
            }
            if (end)
                break;
        }
    }

    public static void Playtime(){
        if (!AutoOn) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите желаемые размеры поля (Одно число).");
            int a = in.nextInt();
            if (a>0)
                Size = a;
            else Size = 3;
        }
        field = new char[Size][Size];

        for (int i=0; i<Size;i++){
            for (int j=0;j<Size;j++) {
                field[i][j]='.';
            }
        }

        while(true) {
            if (actor){
                if (AutoOn&&!RealPlayerFirst){
                    EasyBot();
                }
                else {
                    System.out.println("Введите ваши координаты, игрок Первый");
                    InputData(1);
                    actor = false;
                    PrintPole();
                }
            }
            else{
                if (AutoOn&&RealPlayerFirst){
                    EasyBot();
                }
                else {
                    System.out.println("Введите ваши координаты, игрок Второй");
                    InputData(2);
                    actor = true;
                    PrintPole();
                }
            }

            if (IsDrawPosition())
                break;
            if (IsWinPosition(1))
                break;
            if (IsWinPosition(2))
                break;
        }
    }

    public static void EasyBot(){
        char boot;
        System.out.println("Ходит бот");
        if (RealPlayerFirst)
            boot = 'O';
        else boot = 'X';
        if (field[1][1]=='.')
            field[1][1]=boot;
        else if (field[0][0]=='.'||
                field[2][2]=='.'||
                field[2][0]=='.'||
                field[0][2]=='.') {
            if (field[0][0]=='.')
                field[0][0]=boot;
            else if (field[2][2]=='.')
                field[2][2]=boot;
            else if (field[2][0]=='.')
                field[2][0]=boot;
            else field[0][2]=boot;
        }
        else if(field[1][0]=='.')
            field[1][0]=boot;
        else if (field[0][1]=='.')
            field[0][1]=boot;
        else if (field[1][2]=='.')
            field[1][2]=boot;
        else field[2][1]=boot;
        PrintPole();
        actor = !actor;
    }

    public static void InputData(int player){
        int x,y;
        Scanner in = new Scanner(System.in);
        while(true) {
            x = in.nextInt();
            y = in.nextInt();
            if (x>=Size || x<0 || y>=Size || y<0){
                System.out.println(BanWords[(int)(Math.random()*(BanWords.length-1))]);
            }
            else if (field[x][y] != '.') {
                System.out.println(BanSen[(int)(Math.random()*(BanSen.length-1))]);
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

    public static boolean IsDrawPosition(){
        boolean emptyPosition = false;
        for (int i = 0; i<Size;i++){
            for (int j = 0;j<Size;j++) {
                if (field[i][j]=='.')
                    emptyPosition = true;
            }
        }
        if (emptyPosition){
            return false;
        }
        else{
            System.out.println(DrawWord[(int)(Math.random()*(DrawWord.length-1))]);
            return true;
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

    public static void PrintPole(){
        for (int i=0; i<Size;i++){
            for (int j=0;j<Size;j++){
                System.out.print(field[i][j]);
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