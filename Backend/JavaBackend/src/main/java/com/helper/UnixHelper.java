package com.helper;

import org.joda.time.DateTime;

public class UnixHelper {
  public static Long roundToMinute(DateTime datetime, int minute) {
    datetime = datetime.withMinuteOfHour(minute);
    datetime = datetime.withSecondOfMinute(0);
    datetime = datetime.withMillisOfSecond(0);

    return datetime.getMillis();
  }

  public static Long roundToMinute(Long chrono, int minute) {
    DateTime datetime = new DateTime(chrono);
    datetime = datetime.withMinuteOfHour(minute);
    datetime = datetime.withSecondOfMinute(0);
    datetime = datetime.withMillisOfSecond(0);

    return datetime.getMillis();
  }

  public static Long roundToDate(DateTime datetime) {
    datetime = datetime.withHourOfDay(0);
    datetime = datetime.withMinuteOfHour(0);
    datetime = datetime.withSecondOfMinute(0);
    datetime = datetime.withMillisOfSecond(0);

    return datetime.getMillis();
  }

  public static Long roundToDate(Long unix) {
    DateTime datetime = new DateTime(unix);
    datetime = datetime.withHourOfDay(0);
    datetime = datetime.withMinuteOfHour(0);
    datetime = datetime.withSecondOfMinute(0);
    datetime = datetime.withMillisOfSecond(0);

    return datetime.getMillis();
  }

  public static boolean equalUnixDate(Long unix1, Long unix2) {
    DateTime dt1 = new DateTime(unix1);
    DateTime dt2 = new DateTime(unix2);

    return dt1.getYear() == dt2.getYear()
      && dt1.getMonthOfYear() == dt2.getMonthOfYear()
      && dt1.getDayOfMonth() == dt2.getDayOfMonth();
  }
}
