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
  public int[] days;
  public String[] times;
  public String beginningTime;
  public String endingTime;
  public String interval;


  private Trigger(long id, String title, String description, int[] days)
  {
    this.id = id;
    this.title = title;
    this.description = description;
    this.days = days;
  }


  private Trigger(long id, String title, String description, int[] days, String[] times)
  {
    this(id, title, description, days);
    this.times = times;
  }


  private Trigger(long id, String title, String description, int[] days, String beginningTime, String endingTime, String interval)
  {
    this(id, title, description, days);
    this.beginningTime = beginningTime;
    this.endingTime = endingTime;
    this.interval = interval;
  }


  public static final Trigger[] triggers = {
          new Trigger(0, "Vivamus suscipit tortor eget felis porttitor volutpat. Lacinia eget consectetur sed, convallis at tellus.",
                  "Test Description",
                  new int[]{1, 3},
                  new String[]{"08:00", "09:00"}),
          new Trigger(1, "Test Title 2",
                  "Test Description 2",
                  new int[]{1, 2, 3, 7},
                  "09:30", "14:00", "00:20"),
  };

}