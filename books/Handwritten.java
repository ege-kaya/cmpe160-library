
package books;

import librarymembers.LibraryMember;
import interfaces.ReadInLibrary;

/**
 * Defines a class representing a Handwritten book, which can not be borrowed
 * but only read in the library by Academics. Is the child of Book, implements
 * ReadInLibrary.
 * 
 * @author egeka
 *
 */
public class Handwritten extends Book implements ReadInLibrary {
	/**
	 * Constructor for Handwritten. Initializes the bookID field, making it <i> 1 +
	 * the current number of Book instances <i> and sets the bookType field as "H",
	 * representing Handwritten. Increments the numBooks field in the parent class
	 * Book by 1.
	 * 
	 * @param bookID ID of the newly created Handwritten.
	 */
	public Handwritten(int bookID) {
		super(bookID, "H");
		Book.setNumBooks(Book.getNumBooks() + 1);
	}

	/**
	 * Returns this Handwritten. Sets isTaken field to false, removes this
	 * Handwritten from the previous holder's currentBooks ArrayList. Decrements the
	 * number of books held by the returning LibraryMember by 1. Sets the whoHas
	 * field to null.
	 * 
	 * @param member LibraryMember returning this Handwritten.
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.isTaken = false;
		member.removeFromCurrent(this);
		this.whoHas = null;
	}

	/**
	 * Reads this Handwritten in the library. Sets the isTaken field to true, adds
	 * this Handwritten to the reader's history ArrayList and the currentBooks
	 * ArrayList. Increments the number of books held by the reader by 1. Sets the
	 * whoHas field as the reader.
	 * 
	 * @param member LibraryMember returning this Handwritten.
	 */
	@Override
	public void readBook(LibraryMember member) {
		this.isTaken = true;
		if (!member.getTheHistory().contains(this)) {
			member.addToHistory(this);
		}
		member.addToCurrent(this);
		this.whoHas = member;
	}
}