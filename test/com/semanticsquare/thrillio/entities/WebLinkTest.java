package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.semanticsquare.thrillio.managers.BookmarkManager;

public class WebLinkTest {

	@Test
	public void testIsKidFrienlyEligible() {
		// Test 1: porn in url -- false
		WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "", "http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",	"http://www.javaworld.com");

		boolean isKidFriendlyEligible = webLink.isKidFrienlyEligible();
		
		assertFalse("For porn in url - isKidFriendlyEligible() must return flase", isKidFriendlyEligible);

		// Test 2: porn in title -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Porn, Part 2", "", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");

		isKidFriendlyEligible = webLink.isKidFrienlyEligible();
		
		assertFalse("For porn in title - isKidFriendlyEligible() must return flase", isKidFriendlyEligible);


		// Test 3: adult in host -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.adult.com");

		isKidFriendlyEligible = webLink.isKidFrienlyEligible();
		
		assertFalse("For adult in host - isKidFriendlyEligible() must return flase", isKidFriendlyEligible);


		// Test 4: adult in url, but not in host part -- true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "", "http://www.javaadultworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");

		isKidFriendlyEligible = webLink.isKidFrienlyEligible();
		
		assertTrue("For adult in url - isKidFriendlyEligible() must return flase", isKidFriendlyEligible);


		// Test 5: adult in title only -- true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Adult, Part 2", "", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");

		isKidFriendlyEligible = webLink.isKidFrienlyEligible();
		
		assertTrue("For adult in title - isKidFriendlyEligible() must return flase", isKidFriendlyEligible);

	}

}
