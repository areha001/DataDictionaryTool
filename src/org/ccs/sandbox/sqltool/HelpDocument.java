package org.ccs.sandbox.sqltool;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import ysb.common.FileUtil;

public class HelpDocument
{
  private String text;
  protected static HelpDocument me = null;

  private HelpDocument()
  {
    InputStream localInputStream = super.getClass().getClassLoader().getResourceAsStream("help.txt");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    FileUtil.transfer(localInputStream, localByteArrayOutputStream);
    try
    {
      this.text = new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
  }

  public static HelpDocument getInstance()
  {
    if (me == null)
      me = new HelpDocument();
    return me;
  }

  public String getText()
  {
    return this.text;
  }
}