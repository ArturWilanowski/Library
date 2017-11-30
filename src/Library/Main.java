package Library;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    new Main();
    }

    private Scanner scanner;
    private List<Book> bookList;


    public Main(){
        scanner = new Scanner(System.in);
        Utils.parseBooksFromFile(Paths.get("books.txt"));
        start();
    }

    private void start(){
        System.out.println("Witaj w bibliotece!");

        String command;
        do{
            System.out.println("1 - Dodawanie książki");
            System.out.println("2 - Wypozyczenie książki");
            System.out.println("3 - Oddaj książkę");
            System.out.println("4 - Wyświetl wolne pozycje");

            System.out.println("Wpisz polecenie: ");
            command = scanner.nextLine();
            parseChoice(command);

        }while (!command.equals("exit"));
    }

    private void parseChoice(String command) {
        switch (command){
            case "1": {
                addBook();
                break;
            }
            case "2": {
                rentBook();
                break;
            }
            case "3": {
                bringBackBook();
                break;
            }
            case "4": {
               showFreeBooks();
                break;
            }
            case "exit":{

                break;
            }
            default: {
                System.out.println("Nie ma takiej komendy!");
            }
        }
    }

    private void bringBackBook() {
        System.out.println("Podaj nazwę książki, którą chcesz zwrócić: ");
        String name = scanner.nextLine();

        for (Book book : bookList) {
            if (book.getName().equalsIgnoreCase(name) && book.getRentStatus() == 1) {
                book.setRentStatus(0);
                System.out.println("Dięki za zwrócenie książki");
                return;
            }
        }
        System.out.println("Taka książka nie jest wypożyczona, lub nie istnieje!");
    }

    private void rentBook() {
        System.out.println("Podaj nazwę książki, którą chcesz wypożyczyć: ");
        String name = scanner.nextLine();

        for (Book book : bookList){
            if (book.getName().equalsIgnoreCase(name) && book.getRentStatus() == 0){
                book.setRentStatus(1);
                System.out.println("Wypożyczono książkę " + book.getName());
                System.out.println("Oddaj jak tylko przeczytasz!");
                return;
            }
        }
        System.out.println("Nie mamy takiej książki na stanie!");
    }

    private void addBook() {
        System.out.println("Dodajesz nową książkę!");

        String title, author;
        int pages, produceYear;

        System.out.println("Tytuł: ");
        title = scanner.nextLine();

        for (Book book : bookList) {
            if (book.getName().equalsIgnoreCase(title)){
                System.out.println("Taka książka już istnieje!");
                return;
            }
        }

        System.out.println("Autor: ");
        author = scanner.nextLine();

        System.out.println("Ilość stron: ");
        pages = Integer.parseInt(scanner.nextLine());

        System.out.println("Rok wydania: ");
        produceYear = Integer.parseInt(scanner.nextLine());

        bookList.add(new Book(title, author, pages, produceYear, 0));
        System.out.println("Dodano książkę " + title);
    }

        private void showFreeBooks() {
            for (Book book : bookList){
            if (book.getRentStatus() == 0){
            System.out.println("Wolna pozycja: " + book.getName());
            }
        }
    }
}

