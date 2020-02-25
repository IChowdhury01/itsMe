package sp2020.attachablesmartlock.Users;

import java.util.ArrayList;

/**
 * This class represents a user's friends list.
 * Friends are stored in an ArrayList.
 * A max size can be set for viewing friends on the UI.
 * A user can add another user as a friend with addFriend(targetUser).
 */

public class FriendsList {
    private ArrayList<Friend> usersFriendsList;
    private int maxSize;

    FriendsList() {
        usersFriendsList = new ArrayList<Friend>();
        maxSize = 100;  // default size
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }




    public Boolean addFriend (User userToAdd) {
        Friend newFriend = new Friend(userToAdd);

        // Loop through friendslist. If there's already a friend with the same userID,
        // he's already been added. Return false.
        for (Friend f :
                usersFriendsList) {
            if (f.getUID() == newFriend.getUID()) {
                return false;
            }
        }

        // Otherwise, add the user to the friends list and return true;
        this.usersFriendsList.add(newFriend);

        return true;
    }


    public void removeFriend (User userToRemove) {
        // Loop through friendslist. If a friend has the same ID as the given user, remove him.
        for (Friend f :
                usersFriendsList) {
            if (f.getUID() == userToRemove.getUserID()) {
                usersFriendsList.remove(f);
            }
        }
    }

    // Clears friends list.
    public void clearFriends() {
        this.usersFriendsList.clear();
    }

    @Override
    public String toString() {
        String s = "";

        if (usersFriendsList.isEmpty()) {
            s = "No Friends.";
        }
        else {
            for (Friend f :
                    usersFriendsList) {
                s += f.toString();
            }
        }
        return s;
    }
}


