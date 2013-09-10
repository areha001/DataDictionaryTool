package ysb.common;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REUtil
{
  public static String[] getREGroupVector(String paramString1, String paramString2)
  {
    Pattern localPattern = Pattern.compile(paramString2, 34);
    Vector localVector = new Vector();
    Matcher localMatcher = localPattern.matcher(paramString1);
    while (localMatcher.find())
      for (int i = 1; i <= localMatcher.groupCount(); ++i)
        localVector.add(localMatcher.group(i));
    String[] arrayOfString = new String[localVector.size()];
    return ((String[])(String[])localVector.toArray(arrayOfString));
  }

  public static String[] getREGroupSet(String paramString1, String paramString2)
  {
    Pattern localPattern = Pattern.compile(paramString2, 34);
    HashSet localHashSet = new HashSet();
    Matcher localMatcher = localPattern.matcher(paramString1);
    while (localMatcher.find())
      for (int i = 1; i <= localMatcher.groupCount(); ++i)
        localHashSet.add(localMatcher.group(i));
    String[] arrayOfString = new String[localHashSet.size()];
    return ((String[])(String[])localHashSet.toArray(arrayOfString));
  }

  public static boolean matches(String paramString1, String paramString2)
  {
    Pattern localPattern = Pattern.compile(paramString2, 34);
    Matcher localMatcher = localPattern.matcher(paramString1);
    return localMatcher.matches();
  }
}