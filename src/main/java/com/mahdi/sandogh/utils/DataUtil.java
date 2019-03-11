package com.mahdi.sandogh.utils;


import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public static String longToDate(long date) {
        ULocale locale = new ULocale("fa_IR@calendar=persian");

        Calendar persianCalendar = Calendar.getInstance(locale);
        persianCalendar.setTimeInMillis(date);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return df.format(persianCalendar.getTime());
    }
    public synchronized static int generateNumericRandomAccountNumber() {
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        Calendar persianCalendar = Calendar.getInstance(locale);

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
        System.out.println(df.format(persianCalendar.getTime()));


        System.out.println(persianCalendar.get(Calendar.YEAR));         // 1395
        System.out.println(persianCalendar.get(Calendar.MONTH));        // 3
        System.out.println(persianCalendar.get(Calendar.DAY_OF_MONTH)); // 10
//        return  Calendar.YEAR + RandomStringUtils.randomNumeric(count);
        return persianCalendar.get(Calendar.YEAR);
    }
}
