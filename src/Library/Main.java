package Library;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private Scanner scanner;
    private List<Book> bookList;

    public Main() {
        scanner = new Scanner(System.in);
        bookList = Utils.parseBooksFromFile(Paths.get("book.txt"));
        start();
    }


    private void start(){
        System.out.println("Witaj w mojej bibliotece!");

        String command;
        do{
            System.out.println("1 - Dodawanie ksiazki");
            System.out.println("2 - Wypozyczenie ksiazki");
            System.out.println("3 - Oddawanie ksiazki");
            System.out.println("4 - Wyswietl wolne pozycje");

            System.out.print("Wpisz polecenie: ");
            command = scanner.nextLine();
            parseChoice(command);

        }while (!command.equals("exit"));
    }

    private void parseChoice(String command) {
        switch (command){
            case "4": {
                showFreeBooks();
                break;
            }
            case "1":{
                addBook();
                break;
            }
            case "2":{
                rentBook();
                break;
            }
            case "3": {
                bringBackBook();
                break;
            }
            case "exit":{
                Utils.saveBooksToFile(Paths.get("book.txt"), bookList);
                break;
            }
            default: {
                System.out.println("Nie ma takiej komendy!");
            }
        }
    }

    private void bringBackBook() {
        System.out.print("Podaj nazwę książki, którą chcesz zwrócić: ");
        String name = scanner.nextLine();

        for (Book book : bookList) {
            if(book.getName().equalsIgnoreCase(name) && book.getRentStatus() == 1){
                book.setRentStatus(0);
                System.out.println("Dzięki za zwrócenie książki");
                return;
            }
        }
        System.out.println("Taka ksiazka nie jest wypozyczona, lub nie istnieje!");

    }

    private void rentBook() {
        System.out.print("Podaj nazwę książki, którą chcesz wypożyczyć: ");
        String name = scanner.nextLine();

        for (Book book : bookList) {
            if(book.getName().equalsIgnoreCase(name) && book.getRentStatus() == 0){
                book.setRentStatus(1);
                System.out.println("Wypożyczono książkę " + book.getName());
                System.out.println("Oddaj jak tylko przeczytasz!");
                return;
            }
        }
        System.out.println("Nie mamy takiej ksiazki na stanie!");
    }

    private void addBook() {
        System.out.println("Dodajesz nową książke!");

        String title, author;
        int pages, produceYear;

        System.out.print("Tytuł: ");
        title = scanner.nextLine();

        for (Book book : bookList) {
            if(book.getName().equalsIgnoreCase(title)){
                System.out.println("Taka ksiazka juz istnieje!");
                return;
            }
        }

        System.out.print("Autor: ");
        author = scanner.nextLine();

        System.out.print("Ilość stron: ");
        pages = Integer.parseInt(scanner.nextLine());

        System.out.print("Rok wydania: ");
        produceYear = Integer.parseInt(scanner.nextLine());

        bookList.add(new Book(title, author, pages, produceYear, 0));
        System.out.println("Dodano książke " + title);

    }

    private void showFreeBooks() {
        for (Book book : bookList) {
            if(book.getRentStatus() == 0){
                System.out.println("Wolna pozycja: " + book.getName());
            }
        }
    }
}

