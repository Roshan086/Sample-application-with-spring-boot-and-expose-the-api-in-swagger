package com.sample.utill;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Raza Muhammad Dahri on 16/1/2020
 * @project Reinsurance
 */
public class Utility {

    public static Integer getDifferenceBetweenDate(Timestamp firstLoginTime, Timestamp currentServerTime) {
        long milliseconds = currentServerTime.getTime() - firstLoginTime.getTime();
        int seconds = (int) milliseconds / 1000;
        return seconds;
    }

    public static Timestamp getCurrentTimestamp() {
        Timestamp currentTimestamp = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            currentTimestamp = new Timestamp(now.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTimestamp;
    }

    public static Timestamp getCurrentTimestampWithExtendedSeconds() {
        int sec = 90;
        long retryDate = System.currentTimeMillis();
        Timestamp original = new Timestamp(retryDate);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Pageable createPageRequest(Map<String, String> params) {
        Pageable pageable = null;
        String page = params.get("page");
        String itemsPerPage = params.get("itemsPerPage");
        String sortBy = params.get("sortBy");
        String direction = params.get("direction");

        if (sortBy != null && !sortBy.equalsIgnoreCase("") && !sortBy.equalsIgnoreCase("undefined")) {
            if (direction != null && !direction.equalsIgnoreCase("") && !sortBy.equalsIgnoreCase("undefined")) {
                if (direction.equalsIgnoreCase("desc")) {
                    pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(itemsPerPage), Sort.by(sortBy).descending());
                } else {
                    pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(itemsPerPage), Sort.by(sortBy));
                }
            } else {
                pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(itemsPerPage), Sort.by(sortBy));
            }
        } else {
            pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(itemsPerPage));
        }
        return pageable;
    }

    public static boolean checkPhoneNumber(String str)
    {
        // Regex to check string
        // contains only digits
        String regex = "[0-9-]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

}
