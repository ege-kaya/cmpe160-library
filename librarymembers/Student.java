package librarymembers;

import java.util.ArrayList;

import books.Book;

/**
 * Defines a class representing a member of the library, Student.
 * 
 * @author egeka
 *
 */
public class Student extends LibraryMember {
	/**
	 * Constructor for Student. Initializes the id field, making it <i> 1 + the
	 * current number of LibraryMember instances <i> and sets the memberType field
	 * as "S", representing Student. Increments the numMembers field in the parent
	 * class LibraryMember by 1. Declares two ArrayLists, one for all of the Books
	 * previously or currently held by this Student, and one for the currently held
	 * Books. Initializes the maximum number of books that can be held a time as 10,
	 * the time limit for deadlines and deadline extensions as 20.
	 * 
	 * @param id ID of the newly created Student.
	 */
	public Student(int id) {
		super(id, "S");
		this.maxNumberOfBooks = 10;
		this.timeLimit = 20;
		history = new ArrayList<Book>();
		LibraryMember.setNumMembers(LibraryMember.getNumMembers() + 1);
		currentBooks = new ArrayList<Book>();
	}
	/**
	 * Getter for history.
	 * @return all Books previously or currently held by this Student.
	 */
	@Override
	public ArrayList<Book> getTheHistory() {
		return history;
	}
}