package librarymembers;

import java.util.ArrayList;
import books.*;

/**
 * Defines an abstract class representing a blueprint for a member of the
 * library.
 * 
 * @author egeka
 * 
 */
public abstract class LibraryMember {
	/**
	 * Unique ID of the LibraryMember. Ranges from 1 to 10^6.
	 */
	protected int id;
	/**
	 * Maximum number of Books that can be held simultaneously by this
	 * LibraryMember.
	 */
	protected int maxNumberOfBooks;
	/**
	 * Type of the LibraryMember. "A" if Academic and "S" if Student.
	 */
	protected String memberType;
	/**
	 * ArrayList containing the current Books held by this LibraryMember.
	 */
	protected ArrayList<Book> currentBooks;
	/**
	 * Time limit for deadlines and deadline extensions.
	 */
	protected int timeLimit;
	/**
	 * Total number of LibraryMember instances created. Used in assigning unique ids
	 * to LibraryMembers.
	 */
	private static int numMembers;
	/**
	 * All Books previously or currently held by this LibraryMember.
	 */
	protected ArrayList<Book> history;
	/**
	 * Number of Books currently held by this LibraryMember.
	 */
	protected int numCurrentBooks;

	/**
	 * Constructor for LibraryMember. Initializes the id and memberType fields.
	 * 
	 * @param id         ID of the LibraryMember.
	 * @param memberType type of the LibraryMember. "A" for Academic and "S" for
	 *                   Student.
	 */
	public LibraryMember(int id, String memberType) {
		this.id = id;
		this.memberType = memberType;
	}

	/**
	 * Getter for maxNumberOfBooks.
	 * 
	 * @return Maximum number of Books that can be held by this LibraryMember.
	 */
	public int getMaxNumberOfBooks() {
		return maxNumberOfBooks;
	}

	/**
	 * Getter for numCurrentBooks.
	 * 
	 * @return Number of Books currently held by this LibraryMember.
	 */
	public int getNumCurrentBooks() {
		return numCurrentBooks;
	}

	/**
	 * Setter for numCurrentBooks.
	 * 
	 * @param numCurrentBooks new value for numCurrentBooks.
	 */
	public void setNumCurrentBooks(int numCurrentBooks) {
		this.numCurrentBooks = numCurrentBooks;
	}

	/**
	 * Abstract getter for history.
	 * 
	 * @return history of all the books previously or currently held by this
	 *         LibraryMember.
	 */
	public abstract ArrayList<Book> getTheHistory();

	/**
	 * Getter for numBooks.
	 * 
	 * @return total number of instances of LibraryMember.
	 */
	public static int getNumMembers() {
		return numMembers;
	}

	/**
	 * Getter for id.
	 * 
	 * @return ID of this LibraryMember.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Adds a Book to the history of this LibraryMember.
	 * 
	 * @param b book to be added to history.
	 */
	public void addToHistory(Book b) {
		history.add(b);
	}

	/**
	 * Adds a Book to the ArrayList of currently held books of this LibraryMember.
	 * 
	 * @param b book to be added to currentBooks.
	 */
	public void addToCurrent(Book b) {
		currentBooks.add(b);
	}

	/**
	 * Removes a book from the ArrayList of currently held books of this
	 * LibraryMember.
	 * 
	 * @param b book to be removed from currentBooks.
	 */
	public void removeFromCurrent(Book b) {
		currentBooks.remove(b);
	}

	/**
	 * Getter for currentBooks.
	 * 
	 * @return books currently held by this LibraryMember.
	 */
	public ArrayList<Book> getCurrent() {
		return currentBooks;
	}

	/**
	 * Getter for memberType.
	 * 
	 * @return type of this LibraryMember. "A" for Academic and "S" for Student.
	 */
	public String getMemberType() {
		return memberType;
	}

	/**
	 * Getter for timeLimit.
	 * 
	 * @return time limit for deadlines and deadline extensions.
	 */
	public int getTimeLimit() {
		return timeLimit;
	}

	/**
	 * Setter for numMembers.
	 * 
	 * @param n new value for numMembers.
	 */
	public static void setNumMembers(int numMembers) {
		LibraryMember.numMembers = numMembers;
	}
}