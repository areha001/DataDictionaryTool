package ysb.common;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public abstract class Config
{
  protected static Config me = null;
  protected static Properties props = null;

  protected void init(String paramString)
  {
    if (props == null)
    {
      props = new Properties();
      InputStream localInputStream = super.getClass().getClassLoader().getResourceAsStream(paramString);
      try
      {
        props.load(localInputStream);
      }
      catch (Exception localException)
      {
        System.err.println(super.getClass().getPackage() + "/" + paramString);
      }
    }
  }

  protected static Config getInstance()
  {
    return null;
  }

  public String get(String paramString)
  {
    return props.getProperty(paramString);
  }
}