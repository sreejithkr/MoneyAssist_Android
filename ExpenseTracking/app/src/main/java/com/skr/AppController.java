package com.skr;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by sreejithkr on 23/06/15.
 */

enum Months {
    January,February,March,April,May,June,July,August,September,October,November,December,NoValue, Months;


    public static Months getEnumFromInt(Integer i) {

        switch (i){
            case 1:
                return January;
            case 2:
                return February;
            case 3:
                return March;
            case 4:
                return April;
            case 5:
                return May;
            case 6:
                return June;
            case 7:
                return July;
            case 8:
                return August;
            case 9:
                return September;
            case 10:
                return October;
            case 11:
                return November;
            case 12:
                return December;


        }

        return January;
    }
}

public class AppController {
    public static final String ei=  "!*ei*!";
    private static AppController ourInstance = new AppController();

    public static AppController getInstance() {
        return ourInstance;
    }

    public static String MonthSpaceDateSpaceYearFormat = "MMMM d, yyyy";
    public static String ThreeWordsMonthSpaceDateSpaceYearFormat = "MMM d, yyyy";
    public static String DateDashMonthDashYearFormat = "dd-MM-yyyy";
    public static String MONTH_YEAR_Format = "MMM yyyy";

    public static final String MY_APP_PREFERENCE = "myapppreference";
    public static final String CurrencyString = "CurrencyString";

    public static String getCurrencyString() {


        return currencyString;
    }
    public static void setCurrencyStringWithContext(Context context) {
        SharedPreferences settings = context.getSharedPreferences(AppController.MY_APP_PREFERENCE, 0);

         currencyString = settings.getString(CurrencyString,"");
    }

    private static void setCurrencyString(String currencyString) {
        AppController.currencyString = currencyString;
    }

    public static void setCurrencyStringToSharePreference(String currencyString,Context context) {
        SharedPreferences settings = context.getSharedPreferences(AppController.MY_APP_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(CurrencyString, currencyString);
        editor.commit();
        setCurrencyStringWithContext(context);

    }

    private static String currencyString = "";

    private AppController() {
    }
    public static String stringFromInt(Integer months) {
        return stringFromMonths(Months.getEnumFromInt(months));
    }


    public static String stringFromMonths(Months months){
        switch (months){
            case January:
                return "January";

            case February:
                return "February";

            case March:
                return "March";

            case April:
                return "April";

            case May:
                return "May";

            case June:
                return "June";

            case July:
                return "July";

            case August:
                return "August";

            case September:
                return "September";

            case October:
                return "October";

            case November:
                return "November";

            case December:
                return "December";



        }
        return "";
    }

    public static Months monthsFromString(String months){
        if(months.equalsIgnoreCase("January")){

            return Months.January;
        }else if(months.equalsIgnoreCase("February")){
            return Months.February;

        }else if(months.equalsIgnoreCase("March")){
            return Months.March;

        }else if(months.equalsIgnoreCase("April")){
            return Months.April;

        }else if(months.equalsIgnoreCase("May")){
            return Months.May;

        }else if(months.equalsIgnoreCase("June")){
            return Months.June;

        }else if(months.equalsIgnoreCase("July")){
            return Months.July;

        }else if(months.equalsIgnoreCase("August")){
            return Months.August;

        }else if(months.equalsIgnoreCase("September")){
            return Months.September;

        }else if(months.equalsIgnoreCase("October")){
            return Months.October;

        }else if(months.equalsIgnoreCase("November")){
            return Months.November;

        }else if(months.equalsIgnoreCase("December")){
            return Months.December;

        }

        return Months.NoValue;
    }

    public static int compareTwoDateString(String firstDate,String secondDate ) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateDashMonthDashYearFormat);
            Date date1 = sdf.parse(firstDate);
            Date date2 = sdf.parse(secondDate);

            Log.d(" date1", sdf.format(date1));
            Log.d("date2",sdf.format(date2));

            if (date1.compareTo(date2) > 0) {
                Log.e("date compare","1");
                return  1;
            } else if (date1.compareTo(date2) < 0) {
                Log.e("date compare","2");
                return -1;
            } else if (date1.compareTo(date2) == 0) {
                Log.e("date compare","3");
                return 0;
            } else {
                Log.e("date compare","4");
                return 0;

            }
        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return 0;
        }
    }
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = Math.abs(d2.getTime() - d1.getTime());
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static long getDifferenceDaysFromDateString(String d1, String d2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateDashMonthDashYearFormat);
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

           return AppController.getDifferenceDays(date1,date2);
        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return 0;
        }
    }

    public static Boolean compareDateSFallsBetweenTwoGivenDate(String date,String startDate,String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(MonthSpaceDateSpaceYearFormat);
            Date dateToBeComapred = sdf.parse(date);
            Date dateStart = sdf.parse(startDate);
            Date dateEnd = sdf.parse(endDate);



            int firstComparisonval = 0;
            if (dateStart.compareTo(dateToBeComapred) > 0) {

                firstComparisonval =   1;
            } else if (dateStart.compareTo(dateToBeComapred) < 0) {

                firstComparisonval =  -1;
            } else if (dateStart.compareTo(dateToBeComapred) == 0) {
                firstComparisonval =  0;
            } else {
                firstComparisonval =  0;

            }
            int secondComparisonval = 0;
            if (dateEnd.compareTo(dateToBeComapred) > 0) {

                secondComparisonval =   1;
            } else if (dateEnd.compareTo(dateToBeComapred) < 0) {

                secondComparisonval =  -1;
            } else if (dateEnd.compareTo(dateToBeComapred) == 0) {
                secondComparisonval =  0;
            } else {
                secondComparisonval =  0;

            }

            if((firstComparisonval ==  0 &&  secondComparisonval == 0) ||(firstComparisonval ==  -1 &&  secondComparisonval == 1) || (firstComparisonval == 0 && secondComparisonval == 1) || (firstComparisonval == -1 && secondComparisonval == 0) ){
                return true;

            }else{
                return  false;
            }

        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return true;
        }
    }
    public static Boolean compareDatesFallsBetweenTwoGivenDate_dd_mm_yyyy(String date,String startDate,String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateDashMonthDashYearFormat);
            Date dateToBeComapred = sdf.parse(date);
            Date dateStart = sdf.parse(startDate);
            Date dateEnd = sdf.parse(endDate);



            int firstComparisonval = 0;
            if (dateStart.compareTo(dateToBeComapred) > 0) {

                firstComparisonval =   1;
            } else if (dateStart.compareTo(dateToBeComapred) < 0) {

                firstComparisonval =  -1;
            } else if (dateStart.compareTo(dateToBeComapred) == 0) {
                firstComparisonval =  0;
            } else {
                firstComparisonval =  0;

            }
            int secondComparisonval = 0;
            if (dateEnd.compareTo(dateToBeComapred) > 0) {

                secondComparisonval =   1;
            } else if (dateEnd.compareTo(dateToBeComapred) < 0) {

                secondComparisonval =  -1;
            } else if (dateEnd.compareTo(dateToBeComapred) == 0) {
                secondComparisonval =  0;
            } else {
                secondComparisonval =  0;

            }

            if((firstComparisonval ==  0 &&  secondComparisonval == 0) || (firstComparisonval ==  -1 &&  secondComparisonval == 1) || (firstComparisonval == 0 && secondComparisonval == 1) || (firstComparisonval == -1 && secondComparisonval == 0) ){
                return true;

            }else{
                return  false;
            }

        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return true;
        }
    }

    public static  String pareseDate_in_DD_dash_MM_dash_YYYY_to_Month_comma_Day_space_Year(String dd_mm_YYYY){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(AppController.DateDashMonthDashYearFormat);
            Date date =  sdf.parse(dd_mm_YYYY);
            sdf = new SimpleDateFormat(AppController.ThreeWordsMonthSpaceDateSpaceYearFormat, Locale.ENGLISH);
            return sdf.format(date);
        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return "";
        }
    }
    public static String pareseDate_in_Month_comma_Day_space_Year_to_DD_dash_MM_dash_YYYY(String Month_comma_Day_space_Year){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(AppController.ThreeWordsMonthSpaceDateSpaceYearFormat);
            Date date =  sdf.parse(Month_comma_Day_space_Year);
            sdf = new SimpleDateFormat(AppController.DateDashMonthDashYearFormat, Locale.ENGLISH);
            return sdf.format(date);
        }catch (Exception e){
            Log.d("exception in formatting date","******************************");
            return "";
        }
    }

}
