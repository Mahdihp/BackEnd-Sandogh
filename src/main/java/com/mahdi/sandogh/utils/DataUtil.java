package com.mahdi.sandogh.utils;


import java.sql.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

public class DataUtil {

    public static boolean isValideMobileNumber(String phone) {
        if (phone.length() != 11)
            return false;

        String PhoneNo_PATTERN = "(0)?([ ]|,|-|[()]){0,2}9[0-9]([ ]|,|-|[()]){0,2}(?:[0-9]([ ]|,|-|[()]){0,2}){8}";
        if (phone == null || phone.length() <= 0)
            return false;
        Pattern p = Pattern.compile(PhoneNo_PATTERN);
        Matcher m = p.matcher(phone);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidUUID(String string) {
        try {
            UUID.fromString(string);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String genarateRandomNumber() {
        long round = Math.round(Math.random() * 100000);
        return String.valueOf(round);
    }
    public static String generateOffPackCode(int count) {
        return  RandomStringUtils.randomAlphanumeric(count);
    }
    public static String generateAlphaNumericRandomUserPass(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public synchronized static String generateNumericRandomAccountNumber(int count) {
        Date date =new Date(System.currentTimeMillis());
        int year = date.toLocalDate().getYear();
        return  year + RandomStringUtils.randomNumeric(count);
    }
}
