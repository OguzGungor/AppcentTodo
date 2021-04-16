package com.appCent.toDoApp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilMethods {

    public static Date checkDateFormat(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try{
            //System.out.println(format.parse(date));
            return format.parse(date);
        }catch(ParseException ex){
            return null;
        }
    }
}
