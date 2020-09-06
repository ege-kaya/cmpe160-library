package  books;

import librarymembers.LibraryMember;

/**
 * Defines an abstract class representing a blueprint for a Book that will be
 * stored in the library.
 * 
 * @author egeka
 * 
 */
public abstract class Book {
	/**
	 * Unique ID of the Book. Ranges from 1 to 10^6.
	 */
	protected int bookID;
	/**
	 * Type of the Book. "H" if Handwritten and "P" if Printed.
	 */
	protected String bookType;
	/**
	 * Whether the Book is taken. true if taken, false if not.
	 */
	protected boolean isTaken;
	/**
	 * Current holder of the Book. Points to a LibraryMember. If the Book is not
	 * taken, has a null pointer.
	 */
	protected LibraryMember whoHas;
	/**
	 * Total number of Book instances created. Used in assigning unique bookIDs to
	 * Books.
	 */
	private static int numBooks;

	/**
	 * Constructor for Book. Initializes the bookID and bookType fields.
	 * 
	 * @param bookID   ID of the Book.
	 * @param bookType type of the Book.
	 */
	public Book(int bookID, String bookType) {
		this.bookID = bookID;
		this.bookType = bookType;
	}

	/**
	 * Getter for numBooks.
	 * 
	 * @return total number of instances of Book.
	 */
	public static int getNumBooks() {
		return numBooks;
	}

	/**
	 * Getter for bookID.
	 * 
	 * @return ID of the Book.
	 */
	public int getbookID() {
		return bookID;
	}

	/**
	 * Setter for numBooks.
	 * 
	 * @param n new value for numBooks.
	 */
	public static void setNumBooks(int n) {
		numBooks = n;
	}

	/**
	 * Returns borrowed Book. Abstract method to be implemented by children classes.
	 * 
	 * @param member LibraryMember returning the Book.
	 */
	public abstract void returnBook(LibraryMember member);

	/**
	 * Getter for isTaken.
	 * 
	 * @return true if the Book is taken, false if not.
	 */
	public boolean getIsTaken() {
		return isTaken;
	}

	/**
	 * Setter for isTaken.
	 * 
	 * @param isTaken new value for isTaken
	 */
	public void setIsTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	/**
	 * Getter for bookType.
	 * 
	 * @return type of the Book.
	 */
	public String getbookType() {
		return bookType;
	}
}