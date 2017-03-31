package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookTest {

	@Test
	public void testIsKidFrienlyEligible() {
		// Test 1
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.PHILOSOPHY,	4.3);
		
		boolean isKidFriendlyEligible = book.isKidFrienlyEligible();
		
		assertFalse("Book Genre is Philosophy - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications", new String[] {"Henry David Thoreau"}, BookGenre.SELF_HELP,	4.3);
		
		isKidFriendlyEligible = book.isKidFrienlyEligible();
		
		assertFalse("Book Genre is Self Help - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		
	}

}
