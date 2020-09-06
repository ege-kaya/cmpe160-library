package books;

import librarymembers.LibraryMember;
import interfaces.*;

/**
 * Defines a class representing a Printed book, which can be borrowed or read in
 * the library with no membership restriction. Is the child of Book, implements
 * ReadInLibrary and Borrow.
 * 
 * @author egeka
 *
 */
public class Printed extends Book implements ReadInLibrary, Borrow {
	/**
	 * Deadline until which this Printed must be returned by its holder. If it is
	 * not returned in time, a fee must be paid by the holder.
	 */
	private int deadLine;
	/**
	 * Whether the deadLine has been extended by the holder. The deadline can be
	 * extended only once.
	 */
	private boolean isExtended;

	/**
	 * Constructor for Printed. Initializes the bookID field, making it <i> 1 + the
	 * current number of Book instances <i> and sets the bookType field as "P",
	 * representing Printed. Increments the numBooks field in the parent class Book
	 * by 1.
	 * 
	 * @param bookID ID of the newly created Printed.
	 */
	public Printed(int bookID) {
		super(bookID, "P");
		Book.setNumBooks(Book.getNumBooks() + 1);
	}

	/**
	 * Borrows this Printed. Sets the isTaken field to true, adds this Printed to
	 * the reader's history ArrayList and the currentBooks ArrayList. Increments the
	 * number of books held by the reader by 1. Sets the whoHas field as the reader.
	 * Sets the deadline field as the <i> current tick + the time limit of the
	 * holder (50 for Academics and 20 for Students).
	 * 
	 * @param member LibraryMember borrowing this Printed.
	 * @param tick   current time.
	 */
	@Override
	public void borrowBook(LibraryMember member, int tick) {
		this.isTaken = true;
		if (!member.getTheHistory().contains(this)) {
			member.addToHistory(this);
		}
		member.addToCurrent(this);
		member.setNumCurrentBooks(member.getNumCurrentBooks() + 1);
		this.whoHas = member;
		this.deadLine = tick + member.getTimeLimit();

	}

	/**
	 * Extends the deadline for this Printed by the holder's timeLimit. Sets
	 * isExtended to true.
	 * 
	 * @param member LibraryMember holding this Printed.
	 * @param tick   current time.
	 */
	@Override
	public void extend(LibraryMember member, int tick) {
		this.isExtended = true;
		this.deadLine += member.getTimeLimit();
	}

	/**
	 * Reads this Printed in the library without borrowing. Sets the isTaken field
	 * to true, adds this Printed to the reader's history ArrayList and the
	 * currentBooks ArrayList. Increments the number of books held by the reader by
	 * 1. Sets the whoHas field as the reader.
	 * 
	 * @param member LibraryMember reading this Printed.
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

	/**
	 * Returns this Printed. Sets isTaken field to false, removes this Printed from
	 * the previous holder's currentBooks ArrayList. Decrements the number of books
	 * held by the returning LibraryMember by 1. Sets the whoHas field to null. Sets
	 * the deadLine to 0 and isExtended to false.
	 * 
	 * @param member LibraryMember returning this Printed.
	 */
	@Override
	public void returnBook(LibraryMember member) {
		this.isTaken = false;
		member.removeFromCurrent(this);
		if (deadLine != 0) {
			member.setNumCurrentBooks(member.getNumCurrentBooks() - 1);
		}
		this.whoHas = null;
		this.deadLine = 0;
		this.isExtended = false;
	}

	/**
	 * Getter for deadLine.
	 * 
	 * @return value of deadLine.
	 */
	public int getDeadLine() {
		return deadLine;
	}

	/**
	 * Setter for deadLine.
	 * 
	 * @param deadLine new value of deadLine.
	 */
	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}

	/**
	 * Getter for isExtended.
	 * 
	 * @return true if the deadline has been extended before, false otherwise.
	 */
	public boolean isExtended() {
		return isExtended;
	}

	/**
	 * Setter for isExtended.
	 * 
	 * @param isExtended new value of isExtended.
	 */
	public void setExtended(boolean isExtended) {
		this.isExtended = isExtended;
	}
}