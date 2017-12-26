package com.example.hoppies.habbittrigger;

/**
 * Created on 24-Dec-17.
 */

public class Trigger
{
  public long id;
  public String title;
  public String description;
  public int[] days;
  public String[] times;


  private Trigger(long id, String title, String description, int[] days, String[] times)
  {
    this.id = id;
    this.title = title;
    this.description = description;
    this.days = days;
    this.times = times;
  }


  public static final Trigger[] triggers = {
          new Trigger(1, "Test Title",
                  "Test Description",
                  new int[]{1, 3},
                  new String[]{"08:00", "09:00"}),
          new Trigger(2, "Test Title 2",
                  "Test Description 2",
                  new int[]{1, 2, 3, 7},
                  new String[]{"09:30", "11:00"}),
  };

}