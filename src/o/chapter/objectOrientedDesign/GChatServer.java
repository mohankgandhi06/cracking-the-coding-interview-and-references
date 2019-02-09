package o.chapter.objectOrientedDesign;

import java.util.List;

public class GChatServer {

    /* Question: Explain how you would design a chat server. In particular, provide details about
     * the various backend components, classes and methods. What would bethe hardest problems to solve. */

    /* Login Module */
    /* User Login to the system and the new person will initially provided friends list from their mail contacts or
     * phone contacts. Once they add them then if the person already exists in the database(already a user of the
     * application then their id is stored in the list. If not then the request is sent in mail or mobile number
     * which ever has been the point of interaction i.e. source of their existence)*/

    /* Chat Session Module */
    /* Once the user login then he will be shown the list of friends who are currently online and the user can initiate
     * the chat. The window will open and fetch the recent conversation between them and they can proceed after that */
    /* If the person is sending a message it will have the sender's id [person's id] and the receiver's id and time
     * and other misc details. Now the Messaging Algorithm will have to be taken care for the web */

    /* Group Chat Settings Module */
    /* This is similar to the Normal Chat but here no of people involved will be high and the Messaging Algorithm will
     * have to deliver everyone the message properly and there needs to be some mechanism for handling retrying if the
     * delivery failed. */

    /* Settings Module */

    /* Messaging Algorithm in the Web */
    /* This will be challenging module since it concerned with the web layer. i.e. sending of the packets of data
     * are capable of delivering to wrong address or some it needs to be secure and fault free and if it fails then
     * there has to be a mechanism in place for retry as mentioned before and the notification mechanism once delivered
     * properly */
}

/* In this classes we are using the ids like a database because the assumption is that it will be unique in that way
 * If there is no possible of duplication and other complexities then we can use the names in those places.
 * Also we can avoid the calls to the database by caching the friends list initially on load and then proceed using the
 * caching until next refresh */

class ChatUser {
    private int id;
    private String name;
    private String shortName;
    private List<Integer> friendsId;
    private List<Chat> recentChats;
    private boolean online;

    public ChatUser(String name, String shortName, List<Integer> friendsId, List<Chat> recentChats) {
        this.name = name;
        this.shortName = shortName;
        this.friendsId = friendsId;
        this.recentChats = recentChats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Integer> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(List<Integer> friendsId) {
        this.friendsId = friendsId;
    }

    public List<Chat> getRecentChats() {
        return recentChats;
    }

    public void setRecentChats(List<Chat> recentChats) {
        this.recentChats = recentChats;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}

class Chat {
    private int id;
    private String name;
    private List<Message> messages;

    public Chat(String name, List<Message> messages) {
        this.name = name;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}

class Message {
    private int id;
    private String message;
    private int senderId;
    private int receiverId;
    private String time;
    private boolean haveReceived;

    public Message(String message, int senderId, int receiverId, String time) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isHaveReceived() {
        return haveReceived;
    }

    public void setHaveReceived(boolean haveReceived) {
        this.haveReceived = haveReceived;
    }
}