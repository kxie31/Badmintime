/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

import android.os.Build;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

/**
 * Class: Converters. Converters class contains all converters used by the room data base to change data types
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class Converters {
    static Calendar cal = Calendar.getInstance();
    static int offset = cal.get(Calendar.ZONE_OFFSET);
    static ZoneOffset zone = ZoneOffset.ofTotalSeconds(offset/1000);

    /**
     * this method converts LocalTime into seconds
     * @param t localTime value
     * @return total seconds
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static Integer fromLocalTime(LocalTime t){
        if (t == null){
            return null;
        }
        return t.toSecondOfDay();
    }

    /**
     * this method converts seconds into LocalTime
     * @param i seconds
     * @return result LocalTime
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalTime toLocalTime(Integer i){
        if (i == null){
            return null;
        }
        return LocalTime.ofSecondOfDay(i);
    }

    /**
     * this method converts LocalDate into epoch days
     * @param d LocalDate
     * @return EpochDays
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static Long fromLocalDate(LocalDate d){
        if (d == null){
            return null;
        }
        return d.toEpochDay();
    }

    /**
     * This method converts days into LocalDate
     * @param l days
     * @return result LocalDate
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate toLocalDate(Long l){
        if (l == null){
            return null;
        }
        return LocalDate.ofEpochDay(l);
    }

    /**
     * this method converts LocalDateTime into epoch seconds
     * @param l LocalDateTime
     * @return opoch seconds
     */
    @TypeConverter
    public static Long fromLocalDateTime(LocalDateTime l){
        if (l == null){
            return null;
        }
        return l.toEpochSecond(zone);
    }

    /**
     * this method converts seconds into LocalDateTime
     * @param l seconds
     * @return LocalDateTime
     */
    @TypeConverter
    public static LocalDateTime toLocalDateTime(Long l){
        if(l == null){
            return null;
        }
        return LocalDateTime.ofEpochSecond(l,0,zone);
    }

}
