package com.example.hoppies.habbittrigger;

/**
 * Created by Hoppies on 24-Dec-17.
 */

public class Trigger
{
  private String title;
  private String description;
  private int [] days;
  private String [] at;

  public Trigger(String title, String description, int [] days, String [] at) {
    this.title = title;
    this.description = description;
    this.days = days;
    this.at = at;
  }

  public static final Trigger [] triggers = {
          new Trigger("Test Title",
                  "Test Description",
                  new int [] {1, 2, 3, 4, 5, 6, 7},
                  new String [] {"08:00", "12:00"}),
          new Trigger("Test Title 2",
                  "Test Description 2",
                  new int [] {1, 2, 3, 7},
                  new String [] {"09:30", "11:00"}),
  };

}