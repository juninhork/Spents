package com.example.aplication.utils.datetextfield;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by juniorbraga on 27/09/16.
 */
public class CustomDateUtil {

    private static final int POSITION_ZERO = 0;
    private static final int POSITION_ONE = 1;
    private static final int POSITION_TWO = 2;

    private static final int ONE_VALUE = 1;

    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 10;

    static private final String SEPARATOR = "/";
    public static final String BASE_DATE_FORMAT = "dd/MM/yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(BASE_DATE_FORMAT);
    public static Calendar sCalendar = Calendar.getInstance();

    static public String getCurrentYear() {
        return sCalendar.get(Calendar.YEAR) + "";
    }

    static public String getCurrentMonth() {
        String result = (sCalendar.get(Calendar.MONTH) + ONE_VALUE) + "";
        return result.length() == ONE_VALUE ? "0" + result : result;
    }

    static public String getCurrentDay() {
        String result = sCalendar.get(Calendar.DAY_OF_MONTH) + "";
        return result.length() == ONE_VALUE ? "0" + result : result;
    }

    static public String getDateFormatted() {
        return getCurrentDay() + SEPARATOR + getCurrentMonth() + SEPARATOR + getCurrentYear();
    }


    /**
     * Verifica se uma data é anterior a outra.
     *
     * @param baseDate      Data base para a verificação.
     * @param dateToCompare Data a ser comparada.
     * @return true caso baseDate seja anterior a dateToCompare.
     * @throws ParseException
     */
    public static boolean isBeforeDate(String baseDate, String dateToCompare) throws ParseException {

        Date baseDateFormated = SIMPLE_DATE_FORMAT.parse(baseDate);
        Date dateToCompareFormated = SIMPLE_DATE_FORMAT.parse(dateToCompare);

        return baseDateFormated.before(dateToCompareFormated);
    }


    /**
     * Verifica se uma data é anterior a outra.
     *
     * @param baseDate      Data base para a verificação.
     * @param dateToCompare Data a ser comparada.
     * @return true caso baseDate seja posterior a dateToCompare.
     * @throws ParseException
     */
    public static boolean isAfterDate(String baseDate, String dateToCompare) throws ParseException {

        Date baseDateFormated = SIMPLE_DATE_FORMAT.parse(baseDate);
        Date dateToCompareFormated = SIMPLE_DATE_FORMAT.parse(dateToCompare);

        return baseDateFormated.after(dateToCompareFormated);
    }

    static public Boolean verifyIfFistDateIsGreater(String firstDate, String secondDate) {
        if (!firstDate.isEmpty() && !secondDate.isEmpty()) {
            Calendar firstCalendar = Calendar.getInstance();
            Calendar secondCalendar = Calendar.getInstance();
            String[] firstDateArray = firstDate.split(SEPARATOR);
            String[] secondDateArray = secondDate.split(SEPARATOR);
            firstCalendar.set(Integer.parseInt(firstDateArray[POSITION_TWO]), Integer.parseInt(firstDateArray[POSITION_ONE]), Integer.parseInt(firstDateArray[POSITION_ZERO]));
            secondCalendar.set(Integer.parseInt(secondDateArray[POSITION_TWO]), Integer.parseInt(secondDateArray[POSITION_ONE]), Integer.parseInt(secondDateArray[POSITION_ZERO]));
            return firstCalendar.compareTo(secondCalendar) == ONE_VALUE;
        }
        return false;
    }

    /**
     * Extrai o ano de uma data.
     *
     * @param date Data para ser extrarída o ano.
     * @return Ano da data.
     * @throws ParseException
     */
    public static String getYearFromDate(String date) throws ParseException {
        return getDateField(Calendar.YEAR, date);
    }

    /**
     * Extrai o mês de uma data.
     *
     * @param date Data para ser extrarída o mês.
     * @return mês da data.
     * @throws ParseException
     */
    public static String getMonthFromDate(String date) throws ParseException {
        return getDateField(Calendar.MONTH, date);
    }


    /**
     * Retorna o nome do mês de uma data.
     * TODO Rever lógica
     *
     * @param date Data para ser extrarída o mês.
     * @return nome do mês da data.
     * @throws ParseException
     */
    public static String getMonthName(String date) throws ParseException {

        String month = getMonthFromDate(date);
        int monthIndex = Integer.parseInt(month);


        switch (monthIndex) {

            case 1:
                month = "Janeiro";
                break;

            case 2:
                month = "Fevereiro";
                break;

            case 3:
                month = "Março";
                break;

            case 4:
                month = "Abril";
                break;

            case 5:
                month = "Maio";
                break;

            case 6:
                month = "Junho";
                break;

            case 7:
                month = "Julho";
                break;

            case 8:
                month = "Agosto";
                break;

            case 9:
                month = "Setembro";
                break;

            case 10:
                month = "Outubro";
                break;

            case 11:
                month = "Novembro";
                break;

            case 12:
                month = "Dezembro";
                break;
            default:
                month = "0";
        }

        return month;
    }

    /**
     * Extrai o dia do mês de uma data.
     *
     * @param date Data para ser extrarída o mês.
     * @return mês da data.
     * @throws ParseException
     */
    public static String getDayFromDate(String date) throws ParseException {
        return getDateField(Calendar.DAY_OF_MONTH, date);
    }


    /**
     * Extrai uma parte expecífica de uma data.
     *
     * @param field Parte da data a ser extraída.
     * @param date  Data origem da parte a ser extraída.
     * @return Parte extraída da data. (dia, mês ou ano).
     * @throws ParseException
     */
    private static String getDateField(int field, String date) throws ParseException {

        Date formattedDate = SIMPLE_DATE_FORMAT.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedDate);


        int dateField;

        switch (field) {

            case Calendar.DAY_OF_WEEK:
                dateField = calendar.get(Calendar.DAY_OF_WEEK);
                break;
            case Calendar.MONTH:
                dateField = calendar.get(Calendar.MONTH) + ONE_VALUE;
                break;
            case Calendar.YEAR:
                dateField = calendar.get(Calendar.YEAR);
                break;

            default:
                dateField = calendar.get(Calendar.DATE);
                break;
        }

        String extractedDateField = String.valueOf(dateField);
        if (dateField > LOWER_BOUND && dateField < UPPER_BOUND) {
            extractedDateField = "0".concat(extractedDateField);
        }

        return extractedDateField;
    }

    /**
     * Get Today Date.
     *
     * @return
     */
    public static String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    public static String getDateFifteenAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -15);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    /**
     * Returns the actual date minums numberOfDays.
     *
     * @param numberOfDays
     * @return The start date formatted.
     */
    public static String getStartDateForPrompt(int numberOfDays) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.add(Calendar.DATE, -numberOfDays);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    public static String getOneMonthAgo() {


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return SIMPLE_DATE_FORMAT.format(cal.getTime());
    }

    public static String getThreeMonthAgo() {


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -3);
        return SIMPLE_DATE_FORMAT.format(cal.getTime());
    }

    public static String getSixMonthAgo() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        return SIMPLE_DATE_FORMAT.format(cal.getTime());
    }

    public static String getOneYearAgo() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -12);
        return SIMPLE_DATE_FORMAT.format(cal.getTime());
    }

    public static String getTwoYearsAgo() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -24);
        return SIMPLE_DATE_FORMAT.format(cal.getTime());
    }

    public static String getFormattedDate(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String getConvertLongToDate(Long longDate) {
        Date date = new Date(longDate);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String getConvertLongToHours(Long longDate){
        Date date = new Date(longDate);
        SIMPLE_DATE_FORMAT.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        final String hourStr = hour < 10 ? "0" + hour : String.valueOf(hour);
        final String minStr = minutes < 10 ? "0" + minutes : String.valueOf(minutes);

        return hourStr + ":" + minStr;
    }

    public static String getCompareDate(String date) throws ParseException {

        String dateNotification = date;
        String dateToday = CustomDateUtil.getTodayDate();
        String dataYesterday = CustomDateUtil.getYesterdayDate();

        if (dateNotification.contains(dateToday)) {
            return "Hoje";
        } else if (dateNotification.contains(dataYesterday)) {
            return "Ontem";
        } else {
            return dateNotification;
        }
    }

}
