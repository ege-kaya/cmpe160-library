package librarymembers;

import java.util.ArrayList;

import books.Book;
/**
 * Defines a class representing a member of the library, Academic.
 * 
 * @author egeka
 *
 */
public class Academic extends LibraryMember {
	/**
	 * Constructor for Academic. Initializes the id field, making it <i> 1 + the
	 * current number of LibraryMember instances <i> and sets the memberType field
	 * as "A", representing Academic. Increments the numMembers field in the parent
	 * class LibraryMember by 1. Declares two ArrayLists, one for all of the Books
	 * previously or currently held by this Academic, and one for the currently held
	 * Books. Initializes the maximum number of books that can be held a time as 20,
	 * the time limit for deadlines and deadline extensions as 50.
	 * 
	 * @param id ID of the newly created Academic.
	 */
	public Academic(int id) {
		super(id, "A");
		this.maxNumberOfBooks = 20;
		this.timeLimit = 50;
		history = new ArrayList<Book>();
		LibraryMember.setNumMembers(LibraryMember.getNumMembers() + 1);
		currentBooks = new ArrayList<Book>();
	}

	/**
	 * Getter for history.
	 * 
	 * @return all Books previously or currently held by this Academic.
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return history;
	}
}