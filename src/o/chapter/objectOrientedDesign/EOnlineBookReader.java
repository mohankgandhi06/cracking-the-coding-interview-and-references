package o.chapter.objectOrientedDesign;

import java.util.HashMap;
import java.util.List;

public class EOnlineBookReader {
    /* Question: Design the data structures for an online book reader system */

    public static void main(String[] args) {
        /* User Login Module */

        /* Once Login give access to his list of books */

        /* Library Module - Cart like feature */


    }
}

class Library implements BooksContainer {
    /* This is not like a offline library where if one person has taken the book others have to wait.
     * A new instance of the book will be given to the user */
    private int id;
    private String name;
    private List<Book> bookList;//This is just a simple implementation of a library
    // we can create a hashmap with the categories as the key and then use alphabetical buckets
    // for both bookname and author to index in easy maintainable order. Now for simplicity to
    // an already multi-class implementation we are assuming an easier implementation

    public Library(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBooks(List<Book> bookList) {
        this.bookList.addAll(bookList);
    }

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    public void showcase() {
        for (Book book : bookList) {
            System.out.println("Name: " + book.getName());
            System.out.println("Author Name: " + book.getAuthor());
            System.out.println("Book Description: ");
            System.out.println("Genre:          " + book.getGenreList().toString());
            System.out.println("Rating:         " + book.getRating());
            System.out.println("Reviews:         " + book.getReviews().toString());
        }
    }

    public void showBook(Book book) {
        System.out.println("Name: " + book.getName());
        System.out.println("Author Name: " + book.getAuthor());
        System.out.println("Book Description: ");
        System.out.println("Genre:          " + book.getGenreList().toString());
        System.out.println("Rating:         " + book.getRating());
        System.out.println("No. of Pages:   " + book.getPages());
        System.out.println("Synopsis:       " + book.getSynopsis());
    }

    /* Getter and Setter */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}

interface BooksContainer {
    public void showcase();

    public void showBook(Book book);
}

class User implements BooksContainer {
    private int id;
    private String name;
    private List<Book> bookList;
    private List<Book> archivedBookList;
    private HashMap<Book, HashMap<Page, List<Bookmark>>> bookmarks;//Page Number where the bookmark is present
    private HashMap<Book, Integer> currentPageNumber;//Last Read Page Number

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Page openBook(Book book) {
        return book.getPageContent().get(currentPageNumber.get(book));
    }

    public boolean buyBook(Book book) {
        return bookList.add(new Book(book.getName(), book.getAuthor(), book.getGenreList(), book.getSynopsis(), book.getPages(), book.getType()));
    }

    public void bookmark(Book book, int pageNumber, int startCharLocation, int endCharLocation) {
        Bookmark bookmark = new Bookmark(startCharLocation, endCharLocation);
        if (book.getPageContent().get(pageNumber) != null) {
            if (this.bookmarks.get(book).get(book.getPageContent().get(pageNumber)).size() == 0) {
                /*...*/
            } else {
                /*...*/
            }
        }
        return;
    }

    public void showcase() {
        for (Book book : bookList) {
            System.out.println("Name: " + book.getName());
            System.out.println("Author Name: " + book.getAuthor());
            System.out.println("Book Description: ");
            System.out.println("Genre:          " + book.getGenreList().toString());
            System.out.println("Rating:         " + book.getRating());
        }
    }

    public void showBook(Book book) {
        System.out.println("Name: " + book.getName());
        System.out.println("Author Name: " + book.getAuthor());
        System.out.println("Book Description: ");
        System.out.println("Genre:          " + book.getGenreList().toString());
        System.out.println("Rating:         " + book.getRating());
        System.out.println("No. of Pages:   " + book.getPages());
        System.out.println("Synopsis:       " + book.getSynopsis());
        System.out.println("Current Page:  " + this.currentPageNumber.get(book));
    }

    /* Getters and Setters */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getArchivedBookList() {
        return archivedBookList;
    }

    public void setArchivedBookList(List<Book> archivedBookList) {
        this.archivedBookList = archivedBookList;
    }

    public HashMap<Book, HashMap<Page, List<Bookmark>>> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(HashMap<Book, HashMap<Page, List<Bookmark>>> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public HashMap<Book, Integer> getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(HashMap<Book, Integer> currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }
}

class Book {

    private enum Genre {FICTION, FANTASY, BIOGRAPHY, DRAMA, ANTHOLOGY, MYSTERY, THRILLER, COMEDY, SELFHELP}

    private enum Type {LEATHERBOUNDED, BOUNDED, PAPERBACK}

    private int id;
    private String name;
    private String author;
    private List<Genre> genreList;
    private String synopsis;
    private int pages;
    private Type type;
    private int rating;
    private int buyersCount;
    private List<Review> reviews;
    private List<Page> pageContent;

    public Book(String name, String author, List<Genre> genreList, String synopsis, int pages, Type type) {
        this.name = name;
        this.author = author;
        this.genreList = genreList;
        this.synopsis = synopsis;
        this.pages = pages;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBuyersCount(int buyersCount) {
        this.buyersCount = buyersCount;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getPages() {
        return pages;
    }

    public Type getType() {
        return type;
    }

    public int getRating() {
        return rating;
    }

    public int getBuyersCount() {
        return buyersCount;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Page> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<Page> pageContent) {
        this.pageContent = pageContent;
    }
}

class Page {
    private int pagenumber;
    private String content;
    private List<Bookmark> bookmarks;
}

class Bookmark {
    /* Here we are considering the highlighting option */
    private int startingCharLocation;
    private int endingCharLocation;

    public Bookmark(int startingCharLocation, int endingCharLocation) {
        this.startingCharLocation = startingCharLocation;
        this.endingCharLocation = endingCharLocation;
    }

    public int getStartingCharLocation() {
        return startingCharLocation;
    }

    public int getEndingCharLocation() {
        return endingCharLocation;
    }
}

class Review {
    private int id;
    private String review;
    private String nameOfTheReviewer;//The Assumption here is that the person does not change the name
    private int helpful;
    private int unhelpful;
    private List<Comment> comments;

    public Review(String review, String nameOfTheReviewer) {
        this.review = review;
        this.nameOfTheReviewer = nameOfTheReviewer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getNameOfTheReviewer() {
        return nameOfTheReviewer;
    }

    public void setNameOfTheReviewer(String nameOfTheReviewer) {
        this.nameOfTheReviewer = nameOfTheReviewer;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public int getUnhelpful() {
        return unhelpful;
    }

    public void setUnhelpful(int unhelpful) {
        this.unhelpful = unhelpful;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

class Comment {
    private int id;
    private int comment;
    private String nameOfTheCommenter;//The Assumption here is that the person does not change the name
    private int helpful;
    private int unhelpful;

    public Comment(int comment, String nameOfTheCommenter) {
        this.comment = comment;
        this.nameOfTheCommenter = nameOfTheCommenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getNameOfTheCommenter() {
        return nameOfTheCommenter;
    }

    public void setNameOfTheCommenter(String nameOfTheCommenter) {
        this.nameOfTheCommenter = nameOfTheCommenter;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public int getUnhelpful() {
        return unhelpful;
    }

    public void setUnhelpful(int unhelpful) {
        this.unhelpful = unhelpful;
    }
}