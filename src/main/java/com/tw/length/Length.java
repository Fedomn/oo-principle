package com.tw.length;

public class Length {

  private final int value;

  public Length(int value) {
    this.value = value;
  }

  public String compare(Length target) {
    String compareResult;

    if (value == target.value) {
      compareResult = "is as long as";
    } else {
      compareResult = value > target.value ? "is longer than" : "is shorter than";
    }

    return String.format("%d %s %d", value, compareResult, target.value);
  }
}
