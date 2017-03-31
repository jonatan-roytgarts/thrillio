package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFrienlyEligible() {
		//Test 1: Movie genre is horror
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles", "Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.HORROR, 8.5);
		
		boolean isKidFriendlyEligible = movie.isKidFrienlyEligible();
		
		assertFalse("For Horror Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		//Test 2: Movie genre is thriller
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941, new String[] {"Orson Welles", "Joseph Cotten"}, new String[] {"Orson Welles"}, MovieGenre.THRILLERS, 8.5);
		
		isKidFriendlyEligible = movie.isKidFrienlyEligible();
		
		assertFalse("For Thrillers Genre - isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
	}
}
