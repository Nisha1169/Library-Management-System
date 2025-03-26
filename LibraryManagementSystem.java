import java.util.*;

class Book {
    private String bookID;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    public Book(String bookID, String title, String author, String genre, String availabilityStatus) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    public String getBookID() {
        return bookID;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "BookID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + availabilityStatus;
    }
}

class Library {
    private Map<String, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        if (!books.containsKey(book.getBookID())) {
            books.put(book.getBookID(), book);
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Book ID already exists!");
        }
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.values().forEach(System.out::println);
        }
    }

    public void searchBook(String query) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getBookID().equals(query) || book.getTitle().equalsIgnoreCase(query)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public void updateBook(String bookID, String title, String author, String availabilityStatus) {
        if (books.containsKey(bookID)) {
            Book book = books.get(bookID);
            if (!title.isEmpty()) book.setTitle(title);
            if (!author.isEmpty()) book.setAuthor(author);
            book.setAvailabilityStatus(availabilityStatus);
            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book ID not found!");
        }
    }

    public void deleteBook(String bookID) {
        if (books.remove(bookID) != null) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book ID not found!");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookID = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability Status (Available/Checked Out): ");
                    String status = scanner.nextLine();
                    library.addBook(new Book(bookID, title, author, genre, status));
                    break;
                case 2:
                    library.viewAllBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID or Title: ");
                    String searchQuery = scanner.nextLine();
                    library.searchBook(searchQuery);
                    break;
                case 4:
                    System.out.print("Enter Book ID to update: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter new Title (leave blank to keep unchanged): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new Author (leave blank to keep unchanged): ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new Availability Status: ");
                    String newStatus = scanner.nextLine();
                    library.updateBook(updateID, newTitle, newAuthor, newStatus);
                    break;
                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    String deleteID = scanner.nextLine();
                    library.deleteBook(deleteID);
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
