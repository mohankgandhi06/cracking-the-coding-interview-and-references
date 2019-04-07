package q.chapter.mediumDifficulty;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class YLRUCache {

    public static final Integer CACHE_SIZE = 5;

    public LRUCache lruCache;

    public YLRUCache() {
        this.lruCache = new LRUCache(CACHE_SIZE);
    }

    public static void main(String[] args) {
        YLRUCache cache = new YLRUCache();
        UserData alex = new UserData(1, "Alex");
        UserData steve = new UserData(2, "Steve");
        UserData chris = new UserData(3, "Chris");
        UserData nolan = new UserData(4, "Nolan");
        UserData amy = new UserData(5, "Amy");
        UserData roger = new UserData(6, "Roger");
        UserData goku = new UserData(7, "Goku");
        UserData vegeta = new UserData(8, "Vegeta");
        cache.lruCache.createCache(alex);
        cache.lruCache.createCache(steve);
        cache.lruCache.createCache(chris);
        cache.lruCache.createCache(nolan);
        cache.lruCache.createCache(amy);
        cache.lruCache.createCache(roger);
        cache.lruCache.createCache(goku);
        cache.lruCache.createCache(vegeta);
        cache.lruCache.createCache(alex);
        cache.lruCache.createCache(goku);
        cache.lruCache.updateCache(goku, "I am a super saiyan");
    }
}

class LRUCache {
    public Map<Integer, Cache> map;
    public DoublyLinkedList evictionQueue;

    public LRUCache(Integer size) {
        this.map = new HashMap<>();
        this.evictionQueue = new DoublyLinkedList(size);
    }

    public boolean createCache(UserData userData) {
        /* Create a new entry in the map and then add to
         * Doubly Linked List at the top */
        boolean isCreated = false;
        boolean isRemoved;
        if (this.evictionQueue.isFull()) {
            System.out.println("Cache is Full. Deleting " + this.evictionQueue.getTail().getUserId());
            isRemoved = removeCache(this.evictionQueue.getTail().getUserId());
            if (isRemoved) {
                isCreated = addCache(userData.getUserId(), "You Killed One Cache");
            }
        } else {
            isCreated = addCache(userData.getUserId(), "Howdy");
        }
        if (!isCreated) {
            System.out.println("Could not create the cache for " + userData.getUserName() + ". May be a duplicate is present. Try different user id");
        } else {
            System.out.println("Created the Cache for: " + userData.getUserName());
        }
        return isCreated;
    }

    private boolean addCache(int userId, String cacheData) {
        Cache tempCache = findCache(userId);
        if (tempCache == null) {
            Cache cache = new Cache(userId, cacheData);
            this.map.put(userId, cache);
            if (this.evictionQueue.isEmpty()) {
                this.evictionQueue.setTail(cache);
            } else {
                this.evictionQueue.getHead().setPrevious(cache);
            }
            cache.setNext(this.evictionQueue.getHead());
            this.evictionQueue.setHead(cache);
            this.evictionQueue.setSize(this.evictionQueue.getSize() + 1);
            return true;
        }
        return false;
    }

    private boolean removeCache(int userId) {
        /* Remove from the map then remove from the eviction queue */
        Cache cache = findCache(userId);
        if (cache != null) {
            this.map.remove(userId);
            cache.getPrevious().setNext(null);
            this.evictionQueue.setTail(cache.getPrevious());
            this.evictionQueue.setSize(this.evictionQueue.getSize() - 1);
            return true;
        }
        return false;
    }

    private Cache findCache(int userId) {
        return this.map.get(userId);
    }

    public boolean updateCache(UserData userData, String updatedData) {
        boolean isUpdatePerformed = performUpdate(userData.getUserId(), updatedData);
        if (isUpdatePerformed) {
            isUpdatePerformed = moveCacheToRecentlyUsed(userData.getUserId());
        }
        if (isUpdatePerformed) {
            System.out.println("Updated the data: " + findCache(userData.getUserId()).getData());
        }
        return isUpdatePerformed;
    }

    private boolean performUpdate(int userId, String updatedData) {
        Cache cache = findCache(userId);
        if (cache != null) {
            cache.setData(updatedData);
            cache.setLastUsed(Calendar.getInstance().getTime());
            return true;
        }
        return false;
    }

    private boolean moveCacheToRecentlyUsed(int userId) {
        Cache cache = findCache(userId);
        if (cache != null) {
            /* Removing the link between the cache and its current position */
            cache.getNext().setPrevious(cache.getPrevious());
            cache.getPrevious().setNext(cache.getNext());

            /* Move to the last of the eviction queue */
            cache.setNext(this.evictionQueue.getHead());
            this.evictionQueue.getHead().setPrevious(cache);
            this.evictionQueue.setHead(cache);
            return true;
        }
        return false;
    }
}

class DoublyLinkedList {
    private Cache head;
    private Cache tail;
    private int size;
    private int capacity;

    public DoublyLinkedList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public Cache getHead() {
        return head;
    }

    public void setHead(Cache head) {
        this.head = head;
    }

    public Cache getTail() {
        return tail;
    }

    public void setTail(Cache tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}

class UserData {
    private int userId;
    private String userName;
    private String personalData;

    public UserData(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }
}

class Cache {
    private Cache next;
    private Cache previous;
    private int userId;
    private String data;
    private Date lastUsed;

    public Cache(int userId, String data) {
        this.userId = userId;
        this.data = data;
        this.lastUsed = new Date();
    }

    public Cache getNext() {
        return next;
    }

    public void setNext(Cache next) {
        this.next = next;
    }

    public Cache getPrevious() {
        return previous;
    }

    public void setPrevious(Cache previous) {
        this.previous = previous;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}