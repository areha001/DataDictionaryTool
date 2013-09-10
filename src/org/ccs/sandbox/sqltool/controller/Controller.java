package org.ccs.sandbox.sqltool.controller;

import java.io.File;
import java.io.FileOutputStream;
import org.ccs.sandbox.sqltool.ConfigSet;
import org.ccs.sandbox.sqltool.datamodel.mysql.NavicatSQL;
import org.ccs.sandbox.sqltool.datamodel.oracle.ToadSQL;
import org.ccs.sandbox.sqltool.input.NavicatFileReader;
import org.ccs.sandbox.sqltool.input.ToadFileReader;
import org.ccs.sandbox.sqltool.output.MSWordWriter;
import org.ccs.sandbox.sqltool.parser.NavicatParser;
import org.ccs.sandbox.sqltool.parser.ToadParser;
import ysb.common.Config;
import ysb.common.FileUtil;

public class Controller
{
  public static final int MYSQL = 1;
  public static final int ORACLE = 2;
  private String sqlText;
  private NavicatSQL navicatSQL;
  private ToadSQL toadSQL;

  public String readSQLScript(String paramString1, String paramString2, int paramInt)
    throws Exception
  {
    Object localObject;
    switch (paramInt)
    {
    case 1:
      localObject = new NavicatFileReader();
      this.sqlText = ((NavicatFileReader)localObject).read(new File(paramString1), paramString2);
      break;
    case 2:
      localObject = new ToadFileReader();
      this.sqlText = ((ToadFileReader)localObject).read(new File(paramString1), paramString2);
    }
    return ((String)this.sqlText);
  }

  public void setSqlText(String paramString)
  {
    this.sqlText = paramString;
  }

  public Object parse(int paramInt)
    throws Exception
  {
    if ((this.sqlText != null) && (this.sqlText.trim().length() > 0))
    {
      Object localObject;
      switch (paramInt)
      {
      case 1:
        localObject = new NavicatParser(this.sqlText);
        this.navicatSQL = ((NavicatParser)localObject).parse();
        return this.navicatSQL;
      case 2:
        localObject = new ToadParser(this.sqlText);
        this.toadSQL = ((ToadParser)localObject).parse();
        return this.toadSQL;
      }
    }
    return null;
  }

  public void write2MSWord(File paramFile1, File paramFile2, int paramInt)
    throws Exception
  {
    if ((this.navicatSQL != null) || (this.toadSQL != null))
    {
      MSWordWriter localMSWordWriter = new MSWordWriter();
      if ((paramFile1 == null) || (!(paramFile1.exists())))
      {
        String str = ConfigSet.getInstance().get("template.location");
        FileUtil.transfer(super.getClass().getResourceAsStream("dict2.doc"), new FileOutputStream(str));
        paramFile1 = new File(str);
      }
      switch (paramInt)
      {
      case 1:
        localMSWordWriter.write(this.navicatSQL, paramFile1, paramFile2);
        break;
      case 2:
        localMSWordWriter.write(this.toadSQL, paramFile1, paramFile2);
      }
    }
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    Controller localController = new Controller();
    localController.readSQLScript("c:/test.sql", "UTF-8", 1);
    localController.parse(1);
    localController.write2MSWord(null, new File("c:/file_output.doc"), 1);
  }

  public NavicatSQL getNavicatSQL()
  {
    return this.navicatSQL;
  }

  public ToadSQL getToadSQL()
  {
    return this.toadSQL;
  }
}