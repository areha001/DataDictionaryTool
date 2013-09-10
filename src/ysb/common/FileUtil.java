package ysb.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Vector;

public class FileUtil
{
  public static byte[] getBytes(String paramString)
  {
    byte[] arrayOfByte1;
    FileInputStream localFileInputStream = null;
    try
    {
      File localFile = new File(paramString);
      localFileInputStream = new FileInputStream(localFile);
      arrayOfByte1 = new byte[(int)localFile.length()];
      localFileInputStream.read(arrayOfByte1);
      localFileInputStream.close();
      byte[] arrayOfByte2 = arrayOfByte1;
      return arrayOfByte2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      arrayOfByte1 = null;
      return arrayOfByte1;
    }
    finally
    {
      close(localFileInputStream);
    }
  }

  public static void bytesToFile(byte[] paramArrayOfByte, String paramString)
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = new FileOutputStream(paramString);
      localFileOutputStream.write(paramArrayOfByte);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    finally
    {
      close(localFileOutputStream);
    }
  }

  public static void transfer(String paramString, Writer paramWriter)
  {
    try
    {
      paramWriter.write(paramString);
      paramWriter.flush();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    finally
    {
      close(paramWriter);
    }
  }

  public static void transfer(byte[] paramArrayOfByte, OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream.write(paramArrayOfByte);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    finally
    {
      close(paramOutputStream);
    }
  }

  public static void transfer(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    tranfer(paramInputStream, paramOutputStream, 102400);
  }

  public static void tranfer(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
  {
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = new byte[paramInt];
      int i;
      while ((i = paramInputStream.read(arrayOfByte)) != -1)
      {
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    finally
    {
      close(paramOutputStream, paramInputStream);
    }
  }

  public static String getFileContent(File paramFile, String paramString1, String paramString2)
    throws Exception
  {
    FileInputStream localFileInputStream = null;
    BufferedReader localBufferedReader = null;
    StringBuffer localStringBuffer = new StringBuffer((int)paramFile.length());
    if (paramFile.length() > 0L)
      try
      {
        localFileInputStream = new FileInputStream(paramFile);
        if ((paramString2 != null) && (!(paramString2.equals(""))))
          localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream, "utf-8"));
        else
          localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream));
        String str = "";
        while ((str = localBufferedReader.readLine()) != null)
        {
          localStringBuffer.append(str);
          localStringBuffer.append(paramString1);
        }
        localStringBuffer.setLength(localStringBuffer.length() - paramString1.length());
      }
      catch (Exception localException)
      {
      }
      finally
      {
        close(localFileInputStream);
        close(localBufferedReader);
      }
    return localStringBuffer.toString();
  }

  public static String[] getFileContent(File paramFile, String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    FileInputStream localFileInputStream = null;
    BufferedReader localBufferedReader = null;
    Vector localVector = new Vector();
    if (paramFile.length() > 0L)
      try
      {
    	  localFileInputStream = new FileInputStream(paramFile);
          if ((paramString2 != null) && (!(paramString2.equals(""))))
            localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream, "utf-8"));
          else
            localBufferedReader = new BufferedReader(new InputStreamReader(localFileInputStream));
          String str = "";
          boolean beforeSQL = true;
          StringBuffer localStringBuffer = new StringBuffer();
          while (((str = localBufferedReader.readLine()) != null)){
        	  if(str.indexOf(paramString3) == -1){
		          localStringBuffer.append(str);
		          localStringBuffer.append(paramString1);
        	  }
        	  else{
        		  if(beforeSQL){
        			  beforeSQL = false;
        		  }
        		  else{
        			  localVector.add(localStringBuffer.toString());
        		  }
        		  localStringBuffer = new StringBuffer(str + paramString1);
        	  }
          	}
      }
      catch (Exception localException)
      {
      }
      finally
      {
        close(localFileInputStream);
        close(localBufferedReader);
      }
    String[] arrayOfString = new String[localVector.size()];
    localVector.toArray(arrayOfString);
    return arrayOfString;
  }

  public static String getFileContent(File paramFile, String paramString)
    throws Exception
  {
    return getFileContent(paramFile, C.LINE_S, paramString);
  }

  public static void setFileContent(File paramFile, String paramString1, String paramString2)
    throws Exception
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      byte[] arrayOfByte;
      localFileOutputStream = new FileOutputStream(paramFile);
      if ((paramString2 != null) && (!(paramString2.equals(""))))
        arrayOfByte = paramString1.getBytes(paramString2);
      else
        arrayOfByte = paramString1.getBytes();
      localFileOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
    }
    catch (Exception localException)
    {
    }
    finally
    {
      close(localFileOutputStream);
    }
  }

  public static void appendAtTheBeginning(File paramFile, String paramString1, String paramString2)
    throws Exception
  {
    String str = getFileContent(paramFile, paramString2);
    str = paramString1 + str;
    setFileContent(paramFile, str, paramString2);
  }

  public static void appendAtTheEnd(File paramFile, String paramString1, String paramString2)
    throws Exception
  {
    String str = getFileContent(paramFile, paramString2);
    str = str + paramString1;
    setFileContent(paramFile, str, paramString2);
  }

  public static void close(InputStream paramInputStream)
  {
    try
    {
      paramInputStream.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void close(Reader paramReader)
  {
    try
    {
      paramReader.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void close(OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void close(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    close(paramOutputStream, paramInputStream);
  }

  public static void close(Writer paramWriter)
  {
    if (paramWriter != null)
      try
      {
        paramWriter.close();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
  }

  public static void close(OutputStream paramOutputStream, InputStream paramInputStream)
  {
    try
    {
      if (paramOutputStream != null)
      {
        paramOutputStream.close();
        paramOutputStream = null;
      }
      if (paramInputStream != null)
      {
        paramInputStream.close();
        paramInputStream = null;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}