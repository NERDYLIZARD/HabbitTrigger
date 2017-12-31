package com.example.hoppies.habbittrigger;

/**
 * Created on 24-Dec-17.
 */

public class Trigger
{
  public long id;
  public String title;
  public String description;
  public int mode;
  public String[] days;
  public String[] times;
  public String beginningTime;
  public String endingTime;
  public String interval;


  private Trigger(long id, String title, String description, String[] days)
  {
    this.id = id;
    this.title = title;
    this.description = description;
    this.days = days;
  }


  private Trigger(long id, String title, String description, String[] days, String[] times)
  {
    this(id, title, description, days);
    this.times = times;
  }


  private Trigger(long id, String title, String description, String[] days, String beginningTime, String endingTime, String interval)
  {
    this(id, title, description, days);
    this.beginningTime = beginningTime;
    this.endingTime = endingTime;
    this.interval = interval;
  }


  public static final Trigger[] triggers = {
          new Trigger(0, "Vivamus suscipit tortor eget felis porttitor volutpat. Lacinia eget consectetur sed, convallis at tellus.",
                  "Test Description",
//                  new String []{"monday", "wednesday", "friday"},
                  new String []{"monday", "tuesday", "wednesday", "thursday", "friday", "saturday"},
                  new String[]{"08:00", "09:00"}),
          new Trigger(1, "Test Title 2",
                  "Test Description 2",
                  new String []{"monday", "wednesday", "friday"},
                  "09:30", "14:00", "00:20"),
  };

}