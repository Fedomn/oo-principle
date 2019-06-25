package com.tw.length;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthTest {

  @Test
  void should_return_5_is_shorter_than_6_when_compare_given_two_length_with_one_is_5_and_the_other_is_6() {
    // given
    Length shorterLength = new Length(5);
    Length longerLength = new Length(6);

    // when
    String compareResult = shorterLength.compare(longerLength);

    // then
    assertEquals(compareResult, "5 is shorter than 6");
  }

  @Test
  void should_return_5_is_longer_than_3_when_compare_given_two_length_with_one_is_5_and_the_other_is_3() {
    // given
    Length longerLength = new Length(5);
    Length shorterLength = new Length(3);

    // when
    String compareResult = longerLength.compare(shorterLength);

    // then
    assertEquals(compareResult, "5 is longer than 3");
  }

  @Test
  void should_return_5_is_as_long_as_5_when_compare_given_two_length_with_one_is_5_and_the_other_is_5() {
    // given
    Length length = new Length(5);
    Length otherLength = new Length(5);

    // when
    String compareResult = length.compare(otherLength);

    // then
    assertEquals(compareResult, "5 is as long as 5");
  }

}
