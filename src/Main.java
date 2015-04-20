import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        checkForFraud(System.in);
    }

    public static void checkForFraud(InputStream input) {
        InputReader inputReader = new InputReader(input);
        int lines = inputReader.nextInt();

        String orderId, dealId, email, street, city, state, zip, creditCard, condition;
        HashMap<String, Order> orders = new HashMap<String, Order>();
        final HashSet<Integer> fraud = new HashSet<>();
        String[] temp;
        Order o;

        while (lines-- > 0) {
            orderId = inputReader.next();
            dealId = inputReader.next();
            email = inputReader.next();
            street = inputReader.next();
            city = inputReader.next();
            state = inputReader.next();
            zip = inputReader.next();
            creditCard = inputReader.next();

//            E-mail and addresses (including city and state) are case insensitive: bugs@bunny.com is the same as BuGS@BuNNy.COM and 123 Sesame St. is the same as 123 SeSAME st.
            email = email.toLowerCase();
            street = street.toLowerCase();
            city = city.toLowerCase();
            state = state.toLowerCase();

//            The username portion of an e-mail address can have ignored characters. A . in an e-mail is flat out ignored, so bugs1@bunny.com, and bugs.1@bunny.com are the same e-mail address.
//            A + in an e-mail means the plus and everything after is ignored, so bugs@bunny.com and bugs+10@bunny.com are the same e-mail address.
            temp = email.split("@");
            temp[0] = temp[0].split("\\+")[0];
            email = temp[0].replace(".", "") + "@" + temp[1];

//            Street addresses often have abbreviated words. 123 Sesame St. and 123 Sesame Street are the same address.
//            IL and Illinois are the same state. For the purposes of not making this a typing problem, you can assume that the
//            only abbreviated words you need to worry about are Street/St. and Road/Rd, and the only states you need to worry about are IL, CA (California) and NY (New York).
            state = state.replace("illinois", "il").replace("california", "ca").replace("new york", "ny");
            street = street.replace("street", "st.").replace("road", "rd.");

//            Zip code may or may not contain a dash -
            zip = zip.replace("-", "");

            //Done standardizing data

//            An order is considered fraudulent if any of the following conditions apply:

//            Two orders have the same e-mail address and deal id, but different credit card information, regardless of street address.
            condition = email + dealId;
            o = orders.get(condition);
            if (o == null) {
                orders.put(condition, new Order(creditCard, orderId));
            } else if (!o.creditCard.equals(creditCard)) {
                //have to use this in the case that there are x amount of valid orders, but 1 invalid order
                o.valid = false;
                o.addId(orderId);
            } else {
                o.addId(orderId);
            }

//            Two orders have the same Address/City/State/Zip and deal id, but different credit card information, regardless of e-mail address.
            condition = street + city + state + zip + dealId;
            o = orders.get(condition);
            if (o == null) {
                orders.put(condition, new Order(creditCard, orderId));
            } else if (!o.creditCard.equals(creditCard)) {
                //have to use this in the case that there are x amount of valid orders, but 1 invalid order
                o.valid = false;
                o.addId(orderId);
            } else {
                o.addId(orderId);
            }

            //add the orderid of each invalid order to frauds
            orders.forEach((k, v) -> {
                if (!v.valid)
                    v.orderId.forEach(order -> fraud.add(Integer.parseInt(order)));
            });
        }

        //sort frauds
        Integer[] sortedFrauds = fraud.toArray(new Integer[fraud.size()]);
        Arrays.sort(sortedFrauds);

        if (sortedFrauds.length > 0) {
            System.out.print(sortedFrauds[0]);
        }
        for (int i = 1; i < sortedFrauds.length; i++)
            System.out.print("," + sortedFrauds[i]);
        }
    }

    class Order {
        String creditCard;
        List<String> orderId;
        boolean valid;

        public Order(String creditCard, String orderId) {
            this.creditCard = creditCard;
            this.orderId = new ArrayList<String>();
            this.orderId.add(orderId);
            this.valid = true;
        }

        public void addId(String orderId) {
            this.orderId.add(orderId);
        }
    }
