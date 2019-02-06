package com.example.aplication.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by juniorbraga on 17/03/17.
 */

public class Utils {

    public static String formataMoeda(double valor){
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal(true);
        String valorFormatado = formatoDois.format(valor);

        return valorFormatado;
    }

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("["+symbol+"]","");

        return s;
    }

    public static String mask(String format, String text){
        String maskedText="";
        int i =0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText += m;
                continue;
            }
            try {
                maskedText += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText;
    }

    public static String getCredencialComEspacos(String numeroCredencialVirtual){
        if(numeroCredencialVirtual.length() >= 16) {
            String str = numeroCredencialVirtual.substring(0, 4)
                    + " "
                    + numeroCredencialVirtual.substring(4, 8)
                    + " "
                    + numeroCredencialVirtual.substring(8, 12)
                    + " "
                    + numeroCredencialVirtual.substring(12, 16);

            return str;
        }else{
            return "";
        }
    }


    public static void hideSoftKeyboardOnMaxLength(final Context context, final EditText ed, final int fieldLength) {
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == fieldLength - 1 && before != 1) {
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            ed.getWindowToken(), 0);
                }
            }
        });
    }

    public static void nextInputOnMaxLength(final Context context, final EditText ed, final View viewNext, final int fieldLength) {
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, final int start, final int before, int count) {
                if (start == fieldLength - 1 && before != 1) {
                    viewNext.requestFocus();
                }
            }
        });
    }


    public static String formatDateService(String currentDate) {

        SimpleDateFormat rs = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;

        try {
            convertedCurrentDate = rs.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (convertedCurrentDate != null) {
            String date = sdf.format(convertedCurrentDate);
            return date;
        } else {
            return "";
        }
    }

    public static String formatDate(String currentDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            date = formatter.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date != null) {
            return formato.format(date);
        } else {
            return "";
        }
    }

    public static boolean compareDate(String infoDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        Calendar calendario = Calendar.getInstance();

        try {
            date = formatter.parse(infoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(calendario.getTime().after(date)) {
            return true;
        }else{
            return false;
        }
    }
}