package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {
	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items ...");
		int bookmarkCount = 0;
		
		for (Bookmark[] bookmarkList : bookmarks) {
			for(Bookmark bookmark : bookmarkList) {
				// Bookmarking!!
				if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						
						System.out.println("New Item Bookmarked --" + bookmark);
					}
				}
				// Mark as kid-friendly
				if(user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {
					if(bookmark.isKidFrienlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", " +	bookmark);
						}
					}
					
					// Sharing the Book mark
					if (getKidFriendlyStatusDecision(bookmark).equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
						boolean isShared = getShareDecesion();
						if(isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
		}
	}
	private static boolean getShareDecesion() {
		return Math.random() < 0.5 ? true : false;
	}
	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED : (Math.random() >= 0.4 && Math.random() < 0.8 ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN);
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}
	/*
	public static void bookmark(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is bookmarking");
		
		for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
			int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
			int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
			
			Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
			
			BookmarkController.getInstance().saveUserBookmark(user, bookmark);
			
			System.out.println(bookmark);
		}
	}
	*/

}
