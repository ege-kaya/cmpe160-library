package library;

import java.util.Scanner;
import books.Book;
import books.Handwritten;
import books.Printed;
import librarymembers.Academic;
import librarymembers.LibraryMember;
import librarymembers.Student;

/**
 * Defines a Library class representing a structure that will perform the
 * required validity checks for each operation before redirecting them to their
 * corresponding Book classes.
 * 
 * @author egeka
 * 
 */
public class Library {
	/**
	 * Reads from the input file.
	 */
	private Scanner scanner;
	/**
	 * Holds the books kept in this Library. Size of 10^6 as specified, with 5 extra
	 * for good measure.
	 */
	private Book[] books = new Book[1000000];
	/**
	 * Holds the members of this Library. Size of 10^6 as specified, with 5 extra
	 * for good measure.
	 */
	private LibraryMember[] members = new LibraryMember[1000000];
	/**
	 * Total fee collected by this Library due to books returned past their
	 * deadlines.
	 */
	private int totalFee;

	/**
	 * Constructor for Library. Takes scanner as input from the LibrarySimulator
	 * class.
	 * 
	 * @param scanner input from the LibrarySimulator class that is creating this
	 *                instance of Library.
	 */
	public Library(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Adds a book to this Library. Checks whether the Book is a Printed ("P") or
	 * Handwritten ("H"), then adds the corresponding type to the books array of
	 * this Library.
	 */
	public void addBook() {
		String type = scanner.next().toUpperCase();
		if (type.equals("P")) {
			Printed p = new Printed(Book.getNumBooks() + 1);
			books[p.getbookID() - 1] = p;
		} else if (type.equals("H")) {
			Handwritten h = new Handwritten(Book.getNumBooks() + 1);
			books[h.getbookID() - 1] = h;
		}
	}

	/**
	 * Adds a member to this Library. Checks whether the LibraryMember is a Student
	 * ("S") or an Academic ("A"), then adds the corresponding type to the members
	 * array of this Library.
	 */
	public void addMember() {
		String type = scanner.next().toUpperCase();
		if (type.equals("S")) {
			Student s = new Student(LibraryMember.getNumMembers() + 1);
			members[s.getID() - 1] = s;
		} else if (type.equals("A")) {
			Academic a = new Academic(LibraryMember.getNumMembers() + 1);
			members[a.getID() - 1] = a;
		}
	}

	/**
	 * Borrows a book from this Library. First stores the ID of the book and the ID
	 * of the member requesting the borrow operation in two variables. Then checks
	 * whether the book and the member with the specified IDs exist, whether the
	 * book is currently taken, whether the book is a Printed (since Handwrittens
	 * can not be borrowed), and whether the requesting member has exceeded the
	 * number of books they are allowed to borrow. Finally, calls the noDebts method
	 * of this Library to see whether the requesting user is holding a book past its
	 * deadline. If all tests are passed, calls the borrowBook method of the
	 * corresponding book with the requesting member and the current time.
	 * 
	 * @param tick current time in the system
	 */
	public void borrowBook(int tick) {
		int idBook = scanner.nextInt();
		int idMember = scanner.nextInt();
		Book thisBook = books[idBook - 1];
		LibraryMember thisMember = members[idMember - 1];
		if (thisBook != null && thisMember != null && !thisBook.getIsTaken() && thisBook.getbookType().equals("P")
				&& (thisMember.getNumCurrentBooks() < thisMember.getMaxNumberOfBooks()) 
				&& noDebts(thisMember, tick)) {
			((Printed) thisBook).borrowBook(thisMember, tick);
		}
	}

	/**
	 * Returns a book to this Library. First stores the ID of the book and the ID of
	 * the member requesting the return operation in two variables. Then checks
	 * whether the book and the member with the specified IDs exist, whether the
	 * book is actually held by the returning member. Then checks whether the book
	 * is a Printed and performs a deadline check. If the deadline has been passed,
	 * adds the corresponding value to the totalFee collected by this Library. If
	 * all tests are passed, calls the returnBook method of the corresponding book
	 * with the requesting member and the current time.
	 * 
	 * @param tick current time in the system
	 */
	public void returnBook(int tick) {
		int idBook = scanner.nextInt();
		int idMember = scanner.nextInt();
		Book thisBook = books[idBook - 1];
		LibraryMember thisMember = members[idMember - 1];
		if (thisBook != null && thisMember != null && thisMember.getCurrent().contains(thisBook)) {
			if (thisBook.getbookType().equals("P")) {
				if (tick > ((Printed) thisBook).getDeadLine() && ((Printed) thisBook).getDeadLine() != 0) {
					this.totalFee += (tick - ((Printed) thisBook).getDeadLine());
				}
			}
			thisBook.returnBook(thisMember);
		}
	}

	/**
	 * Extends the deadline of a book borrowed from this Library. First stores the
	 * ID of the book and the ID of the member requesting the extend operation in
	 * two variables. Then checks whether the book and the member with the specified
	 * IDs exist, whether the book is actually held by the extending member. Then
	 * checks whether the specified book is a Printed, and whether the deadline has
	 * been extended before. Finally, checks whether the deadline has been passed.
	 * If all tests are passed, calls the extend method of the corresponding book
	 * with the requesting member and the current time.
	 * 
	 * @param tick current time in the system
	 */
	public void extendBook(int tick) {
		int idBook = scanner.nextInt();
		int idMember = scanner.nextInt();
		Book thisBook = books[idBook - 1];
		LibraryMember thisMember = members[idMember - 1];
		if (thisBook != null && thisMember != null && thisMember.getCurrent().contains(thisBook)
				&& thisBook.getbookType().equals("P") && !((Printed) thisBook).isExtended()
				&& tick <= ((Printed) thisBook).getDeadLine()) {
			((Printed) thisBook).extend(thisMember, tick);
		}
	}

	/**
	 * Reads a book in this library. First stores the ID of the book and the ID of
	 * the member requesting the extend operation in two variables. Then checks
	 * whether the book and the member with the specified IDs exist and whether the
	 * book is currently taken. Then checks if the specified book is a Handwritten,
	 * and only allows Academics to borrow it if so.
	 */
	public void readInLibrary() {
		int idBook = scanner.nextInt();
		int idMember = scanner.nextInt();
		Book thisBook = books[idBook - 1];
		LibraryMember thisMember = members[idMember - 1];
		if (thisBook != null && thisMember != null && !thisBook.getIsTaken()) {
			if (thisBook.getbookType().equals("H") && thisMember.getMemberType().equals("A")) {
				((Handwritten) thisBook).readBook(thisMember);
			} else if (thisBook.getbookType().equals("P")) {
				((Printed) thisBook).readBook(thisMember);
			}
		}
	}

	/**
	 * Checks whether the specified member is holding a book past its deadline.
	 * 
	 * @param thisMember member on which the test will be applied
	 * @param tick       current time in the system
	 * @return true if the member is holding no books past their deadline, false if
	 *         otherwise
	 */
	private boolean noDebts(LibraryMember thisMember, int tick) {
		for (int i = 0; i < thisMember.getNumCurrentBooks(); i++) {
			if (thisMember.getCurrent().get(i).getbookType().equals("P")
					&& tick > ((Printed) thisMember.getCurrent().get(i)).getDeadLine()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Getter for totalFee.
	 * 
	 * @return totalFee collected by this library due to books turned in past their
	 *         deadline.
	 */
	public int getTotalFee() {
		return totalFee;
	}

	/**
	 * Setter for totalFee.
	 * 
	 * @param totalFee new value of the fee collected by this library due to books
	 *                 turned in past their deadline.
	 */
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * Getter for books.
	 * 
	 * @return books held in this Library.
	 */
	public Book[] getBooks() {
		return books;
	}

	/**
	 * Getter for members.
	 * 
	 * @return members registered to this Library.
	 */
	public LibraryMember[] getMembers() {
		return members;
	}
}