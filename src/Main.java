public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader(System.in);
        int lines = inputReader.nextInt();

        String orderId, dealId, email, street, city, state, zip, creditCard;

        String[] temp;
        while (lines-- > 0) {
            orderId = inputReader.next();
            dealId = inputReader.next();
            email = inputReader.next();
            street = inputReader.next();
            city = inputReader.next();
            state = inputReader.next();
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
            email = temp[0].replaceAll("\\.", "") + "@" + temp[1];
            System.out.println(email);


        }
    }
}
