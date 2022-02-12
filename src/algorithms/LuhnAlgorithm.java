package algorithms;

public class LuhnAlgorithm {
    public static String luhnAlgorithm(String bin, String accountIdentifier) {
        String checksum;
        String cardNumber = bin + accountIdentifier;

        int sum = 0;
        for (int position = 0; position < cardNumber.length(); position++) {
            int number = Character.getNumericValue(cardNumber.charAt(position));

            if (position % 2 == 0) {
                number *= 2;
                if (number > 9) {
                    sum += number - 9;
                }
                else {
                    sum += number;
                }
            } else {
                sum += number;
            }
        }

        int modul10 = sum % 10;
        checksum = (modul10 == 0) ? String.valueOf(0) : String.valueOf(10 - modul10);

        return checksum;
    }

    public static boolean luhnAlgorithmTest(String cardNumber) {
        boolean check = false;
        String checksum = luhnAlgorithm(cardNumber.substring(0, 6), cardNumber.substring(6, 15));
        if (checksum.equals(cardNumber.substring(15))) {
            check = true;
        }
        return check;
    }
}
