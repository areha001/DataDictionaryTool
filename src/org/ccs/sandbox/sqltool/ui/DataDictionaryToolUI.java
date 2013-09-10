package org.ccs.sandbox.sqltool.ui;

import java.io.File;

import org.apache.log4j.Logger;
import org.ccs.sandbox.sqltool.ConfigSet;
import org.ccs.sandbox.sqltool.controller.BatchProcessor;
import org.ccs.sandbox.sqltool.controller.Controller;
import org.eclipse.swt.widgets.FileDialog;

import ysb.common.C;
import ysb.common.FileUtil;
import ysb.swt.dialog.Function;
import ysb.swt.dialog.LRCompositeWithMenu;
import ysb.swt.dialog.ShellUtil;

public class DataDictionaryToolUI extends LRCompositeWithMenu
{
  public static String[] fileNames;
  private static final Logger log = Logger.getLogger(DataDictionaryToolUI.class);
  public static final String TARGET_DOC = ConfigSet.getInstance().get("target.doc");
  public static void main(String[] paramArrayOfString)
  {
    DataDictionaryToolUI localDataDictionaryToolUI = new DataDictionaryToolUI();
    shell.setText("MySQL/Oracle数据字典生成工具");
    ShellUtil.makeShellMaximized(display, shell);
    ShellUtil.makeShellCentered(display, shell);
    localDataDictionaryToolUI.makeComponents(shell);
    localDataDictionaryToolUI.createSysMenuBar(shell);
    localDataDictionaryToolUI.readAndDispatch(display, shell);
  }

  public int getLeft()
  {
    return 1;
  }

  public int getRight()
  {
    return 100;
  }

  protected Function[] getFunctions()
  {
    return null;
  }

  protected Function[] getSysFunctions1()
  {
    LoadMultiFile localLoadMultiFile = new LoadMultiFile();
    MySQLMultiFileGenerator localMySQLMultiFileGenerator = new MySQLMultiFileGenerator();
    LoadBigFile localLoadBigFile = new LoadBigFile();
    MySQLBigFileGenerator localMySQLBigFileGenerator = new MySQLBigFileGenerator();
    return new Function[]{ localLoadMultiFile, localMySQLMultiFileGenerator, localLoadBigFile, localMySQLBigFileGenerator };
  }

  protected String getSysFunctions1Text()
  {
    return "&MySQL";
  }

  protected Function[] getSysFunctions2()
  {
    LoadSimpleFile localLoadSimpleFile = new LoadSimpleFile();
    SimpleFileGenerator localSimpleFileGenerator = new SimpleFileGenerator();
    return new Function[] { localLoadSimpleFile, localSimpleFileGenerator };
  }

  protected String getSysFunctions2Text()
  {
    return "&Oracle";
  }
/**IMPORTANT*/
  private class MySQLBigFileGenerator
  implements Function
  {
    public void execute()
    {
      BatchProcessor localBatchProcessor = new BatchProcessor();
      try
      {
        int i = 1;
        localBatchProcessor.readBigSQLScript(DataDictionaryToolUI.this.srcText.getText(), DataDictionaryToolUI.this.loadEncodingCombo.getText(), i);
        localBatchProcessor.parse(i);
        localBatchProcessor.write2MSWord(new File(TARGET_DOC), i);
      }
      catch (Throwable localThrowable)
      {
    	  localThrowable.printStackTrace();
    	  DataDictionaryToolUI.this.error(localThrowable);
      }
    }

    public String getText()
    {
      return "B2: 生成数据字典(一个sql文件包括多个表结构)";
    }
  }

  private class LoadBigFile
  implements Function
  {
    public void execute()
    {
      FileDialog localFileDialog = new FileDialog(DataDictionaryToolUI.shell, 4096);
      String str = localFileDialog.open();
      if (str != null)
        try
        {
          DataDictionaryToolUI.this.srcText.setText(str);
        }
        catch (Exception localException)
        {
          	DataDictionaryToolUI.this.error(localException);
        }
    }

    public String getText()
    {
      return "B1：载入SQL文件(一个sql文件包括多个表结构)";
    }
  }

  private class MySQLMultiFileGenerator
  implements Function
  {
    public void execute()
    {
      BatchProcessor localBatchProcessor = new BatchProcessor();
      try
      {
        int i = 1;
        localBatchProcessor.readSQLScript(DataDictionaryToolUI.fileNames, DataDictionaryToolUI.this.loadEncodingCombo.getText(), i);
        localBatchProcessor.parse(i);
        localBatchProcessor.write2MSWord(new File(TARGET_DOC), i);
      }
      catch (Throwable localException)
      {
      	DataDictionaryToolUI.this.error(localException);
      }
    }

    public String getText()
    {
      return "A2: 生成数据字典(一个sql文件对应一张表结构,可选多个文件)";
    }
  }

  private class LoadMultiFile
  implements Function
  {
    public void execute()
    {
      FileDialog localFileDialog = new FileDialog(DataDictionaryToolUI.this.getShell(), 4098);
      String str = localFileDialog.open();
      String[] arrayOfString = localFileDialog.getFileNames();
      DataDictionaryToolUI.fileNames = new String[arrayOfString.length];
      if (str != null)
        try
        {
          for (int i = 0; i < arrayOfString.length; ++i)
          {
            DataDictionaryToolUI.fileNames[i] = localFileDialog.getFilterPath() + C.FILE_S + arrayOfString[i];
            DataDictionaryToolUI.this.srcText.append(DataDictionaryToolUI.fileNames[i]);
            DataDictionaryToolUI.this.srcText.append(C.LINE_S);
          }
        }
        catch (Exception localException)
        {
        	DataDictionaryToolUI.this.error(localException);
        }
    }

    public String getText()
    {
      return "A1：载入SQL文件(一个sql文件对应一张表结构,可选多个文件)";
    }
  }

  private class SimpleFileGenerator
  implements Function
  {
    public void execute()
    {
      int i;
      try
      {
        i = 2;
        DataDictionaryToolUI.log.debug("NEW Controller...");
        Controller localController = new Controller();
        DataDictionaryToolUI.log.debug("Read SQL SCRIPT From Console");
        localController.setSqlText(DataDictionaryToolUI.this.srcText.getText());
        if (DataDictionaryToolUI.this.srcText.getText().indexOf("`") != -1)
          i = 1;
        DataDictionaryToolUI.log.debug("Begin Parse .........");
        localController.parse(i);
        DataDictionaryToolUI.log.debug("Begin Write to MSWord");
        localController.write2MSWord(null, null, i);
      }
      catch (Exception localException)
      {
      	DataDictionaryToolUI.this.error(localException);
      }
    }

    public String getText()
    {
      return "A2: 生成数据字典()";
    }
  }

  private class LoadSimpleFile
  implements Function
  {
    public void execute()
    {
      FileDialog localFileDialog = new FileDialog(DataDictionaryToolUI.this.getShell(), 4096);
      String str1 = localFileDialog.open();
      if (str1 != null)
        try
        {
          String str2 = FileUtil.getFileContent(new File(str1), DataDictionaryToolUI.this.loadEncodingCombo.getText());
          DataDictionaryToolUI.this.srcText.setText(str2);
        }
        catch (Exception localException)
        {
        	DataDictionaryToolUI.this.error(localException);
        }
    }

    public String getText()
    {
      return "A1：载入SQL文件(一次只能处理一张表)";
    }
  }
}