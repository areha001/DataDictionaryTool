package org.ccs.sandbox.sqltool.controller;

import java.io.File;
import java.io.FileOutputStream;
import org.ccs.sandbox.sqltool.ConfigSet;
import org.ccs.sandbox.sqltool.datamodel.mysql.NavicatSQL;
import org.ccs.sandbox.sqltool.datamodel.oracle.ToadSQL;
import org.ccs.sandbox.sqltool.input.NavicatFileReader;
import org.ccs.sandbox.sqltool.output.BatchWordWriter;
import org.ccs.sandbox.sqltool.parser.NavicatParser;
import ysb.common.C;
import ysb.common.Config;
import ysb.common.FileUtil;

public class BatchProcessor
{
  public static final int MYSQL = 1;
  public static final int ORACLE = 2;
  private String[] sqlText;
  private NavicatSQL[] navicatSQL;
  private ToadSQL[] toadSQL;

  public String[] readSQLScript(String[] paramArrayOfString, String paramString, int paramInt)
    throws Exception
  {
    switch (paramInt)
    {
    case 1:
      NavicatFileReader localNavicatFileReader = new NavicatFileReader();
      this.sqlText = new String[paramArrayOfString.length];
      for (int i = 0; i < paramArrayOfString.length; ++i)
        this.sqlText[i] = localNavicatFileReader.read(new File(paramArrayOfString[i]), paramString);
    case 2:
    }
    return this.sqlText;
  }

  public String[] readBigSQLScript(String paramString1, String paramString2, int paramInt)
    throws Exception
  {
    switch (paramInt)
    {
    case 1:
      NavicatFileReader localNavicatFileReader = new NavicatFileReader();
      String[] arrayOfString = FileUtil.getFileContent(new File(paramString1.replaceAll(C.LINE_S, "")),C.LINE_S , paramString2, "DROP TABLE");
      this.sqlText = new String[arrayOfString.length];
      for (int i = 0; i < arrayOfString.length; ++i)
        this.sqlText[i] = arrayOfString[i];
    case 2:
    }
    return this.sqlText;
  }

  public void setSqlText(String[] paramArrayOfString)
  {
    this.sqlText = paramArrayOfString;
  }

  public Object[] parse(int paramInt)
    throws Exception
  {
    this.navicatSQL = new NavicatSQL[this.sqlText.length];
    if ((this.sqlText != null) && (this.sqlText.length > 0))
      switch (paramInt)
      {
      case 1:
        for (int i = 0; i < this.sqlText.length; ++i)
        {
          String str = this.sqlText[i];
          NavicatParser localNavicatParser = new NavicatParser(str);
          this.navicatSQL[i] = localNavicatParser.parse();
        }
      case 2:
      }
    return this.navicatSQL;
  }

  public void write2MSWord(File paramFile, int paramInt)
    throws Throwable
  {
    String str = ConfigSet.getInstance().get("template.location");
    FileUtil.transfer(super.getClass().getResourceAsStream("dict2.doc"), new FileOutputStream(str));
    File localFile = new File(str);
    write2MSWord(localFile, paramFile, paramInt);
  }

  public void write2MSWord(File paramFile1, File paramFile2, int paramInt)
    throws Throwable
  {
    if ((this.navicatSQL != null) || (this.toadSQL != null))
    {
      BatchWordWriter localBatchWordWriter = new BatchWordWriter();
      if ((paramFile1 == null) || (!(paramFile1.exists())))
      {
        String str = ConfigSet.getInstance().get("template.location");
        FileUtil.transfer(super.getClass().getResourceAsStream("dict2.doc"), new FileOutputStream(str));
        paramFile1 = new File(str);
      }
      switch (paramInt)
      {
      case 1:
        localBatchWordWriter.write(this.navicatSQL, paramFile1, paramFile2);
      case 2:
      }
    }
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
  }

  public NavicatSQL[] getNavicatSQL()
  {
    return this.navicatSQL;
  }

  public ToadSQL[] getToadSQL()
  {
    return this.toadSQL;
  }
}