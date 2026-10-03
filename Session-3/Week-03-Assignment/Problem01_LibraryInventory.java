
class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    public LibraryInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }
}

public class Problem01_LibraryInventory {
    public static void main(String[] args) {
        BookInventory[] inventory = {
            new LibraryInventory("Clean Code", "Robert C. Martin", 3),
            new LibraryInventory("Effective Java", "Joshua Bloch", 5),
            new LibraryInventory("Refactoring", "Martin Fowler", 0),
            new LibraryInventory("Design Patterns", "GoF", 2)
        };

        for (BookInventory book : inventory) {
            book.printEntry();
        }
    }
}