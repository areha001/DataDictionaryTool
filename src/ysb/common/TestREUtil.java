package ysb.common;

import java.io.PrintStream;

public class TestREUtil
{
  public static void main(String[] paramArrayOfString)
  {
    String str1 = "abc";
    String str2 = "^(abc).*";
    System.out.println(REUtil.matches(str1, str2));
    String[] arrayOfString = REUtil.getREGroupVector(str1, str2);
    for (int i = 0; i < arrayOfString.length; ++i)
      System.out.println(arrayOfString[i]);
    System.out.println("OK");
  }
}