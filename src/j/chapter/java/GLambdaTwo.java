package j.chapter.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class GLambdaTwo {
    public static void main(String[] args) {
        /*  Normal Way  */
        DeliverGoods deliverGoodsOne = new DeliverGoods();
        Good productOne = deliverGoodsOne.deliveryAction();
        System.out.println();
        System.out.println("Product Delivered: ");
        System.out.println("ID: " + productOne.getGoodId()
                + " \nName: " + productOne.getGoodName()
                + " \nPrice: " + productOne.getGoodPrice()
                + " \nExpiry Date: " + productOne.getExpiryDate());

        /*  Lambda Way  */
        DeliverGoods deliverGoodsTwo = new DeliverGoods();
        Good productTwo = deliverGoodsTwo.deliveryActionInLambda();
        System.out.println();
        System.out.println("Product Delivered: ");
        System.out.println("ID: " + productTwo.getGoodId()
                + " \nName: " + productTwo.getGoodName()
                + " \nPrice: " + productTwo.getGoodPrice()
                + " \nExpiry Date: " + productTwo.getExpiryDate());

        List<Good> deliverGoodsList = new ArrayList<>();
        deliverGoodsList.add(new Good(1, "Happy Holidays Mug", BigDecimal.valueOf(249), new Date()));
        deliverGoodsList.add(new Good(2, "Blender", BigDecimal.valueOf(2449), new Date()));
        deliverGoodsList.add(new Good(3, "Parachute", BigDecimal.valueOf(12049), new Date()));
        deliverGoodsList.add(new Good(4, "Cycle", BigDecimal.valueOf(10000), new Date()));
        deliverGoodsList.add(new Good(5, "Robot", BigDecimal.valueOf(15999), new Date()));
        deliverGoodsList.add(new Good(6, "Lemon Juice", BigDecimal.valueOf(12), new Date()));

        int[] spoiledGoods = new int[ 10 ];
        int i = 0;
        deliverGoodsList.forEach(deliverGood -> {
            /*deliverGoodsList.removeIf(de -> de.getGoodId() == 3);*/
            if (deliverGood.getGoodId() == 3) {
                spoiledGoods[ i ] = 3;
            }
        });

        deliverGoodsList.forEach(deliverGood -> {
            System.out.println(deliverGood.getGoodId() + ") " + deliverGood.getGoodName());
        });

        System.out.println("********************************************");
        List<Good> filteredResult = filteredResult(deliverGoodsList, good -> good.getGoodPrice().compareTo(BigDecimal.valueOf(5000)) == 1);
        filteredResult.forEach(result -> {
            System.out.println(result.getGoodName() + " Price: " + result.getGoodPrice());
        });
        System.out.println("********************************************");
    }

    public final static Good deliverThis(DeliverInterface deliverInterface, Good item, User user) {
        return deliverInterface.deliver(item, user);
    }

    public static List<Good> filteredResult(List<Good> goodsList, Predicate<Good> filterCondition) {
        List<Good> result = new ArrayList<>();
        for (Good item : goodsList) {
            if (filterCondition.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}

class DeliverGoods {
    public Good deliveryAction() {
        final int i = 0;
        {
            DeliverInterface deliverInterface = new DeliverInterface() {
                @Override
                public Good deliver(Good item, User user) {
                    System.out.println();
                    System.out.println("Anonymous Class Name inside is " + getClass().getSimpleName());
                    System.out.println();
                    System.out.println(item.getGoodName() + " was delivered to " + user.getUserAddress() + " today.");
                    /* The below line will throw error if we don't declare variable 'i' as final because the anonymous
                       class will not have the same instance of the calling method's class. So we need to declare int
                       as final */
                    /*System.out.println("I count is inside Anonymous Class: " + i);*/
                    return item;
                }
            };
            System.out.println("DeliverGoods Class Name is " + getClass().getSimpleName());
            System.out.println("I count is: " + i);
            return GLambdaTwo.deliverThis(deliverInterface, new Good(6, "'Who moved my Cheese?'", BigDecimal.valueOf(349), new Date()), new User(96, "Mohan", "Coimbatore"));
        }
    }

    public Good deliveryActionInLambda() {
        int i = 0;
        DeliverInterface deliverInterface = (item, user) -> {
            System.out.println();
            System.out.println("Lambda Class Name inside is " + getClass().getSimpleName());
            System.out.println();
            System.out.println(item.getGoodName() + " was delivered to " + user.getUserAddress() + " today.");
            System.out.println("I count is inside Anonymous Class: " + i);
            return item;
        };
        System.out.println("DeliverGoods Class Name is " + getClass().getSimpleName());
        Good product = GLambdaTwo.deliverThis(deliverInterface, new Good(8, "Murder on the Orient Express", BigDecimal.valueOf(300), new Date()), new User(96, "Mohan", "Coimbatore"));
        System.out.println("I count is: " + i);
        return product;
    }
}

class Good {
    private int goodId;
    private String goodName;
    private BigDecimal goodPrice;
    private Date expiryDate;

    public Good(int goodId, String goodName, BigDecimal goodPrice, Date expiryDate) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.expiryDate = expiryDate;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

class User {
    private int userId;
    private String userName;
    private BigDecimal userAccountBalance;
    private String userAddress;
    private ArrayList<Good> cartItems;

    public User(int userId, String userName, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getUserAccountBalance() {
        return userAccountBalance;
    }

    public void setUserAccountBalance(BigDecimal userAccountBalance) {
        this.userAccountBalance = userAccountBalance;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public ArrayList<Good> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<Good> cartItems) {
        this.cartItems = cartItems;
    }
}

@FunctionalInterface
interface DeliverInterface {
    public Good deliver(Good item, User user);
}