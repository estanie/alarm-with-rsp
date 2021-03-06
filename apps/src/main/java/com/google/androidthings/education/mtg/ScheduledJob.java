package com.google.androidthings.education.mtg;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;

import java.util.Locale;
import java.util.TimerTask;

/**
 * Created by estanie on 2018-01-16.
 */

public class ScheduledJob extends TimerTask{
        private Display display;

        ScheduledJob(Display display) {
            this.display = display;
        }

        public void run()
        {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+09:00"));
            cal.getTime();
            int getHour = cal.get(Calendar.HOUR) + cal.get(Calendar.AM_PM)*12;
            int getMinute = cal.get(Calendar.MINUTE);

            display.show(String.format(Locale.getDefault(),"%02d%02d", getHour,getMinute));
        }
    }
