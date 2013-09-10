package org.ccs.sandbox.sqltool.output;

import java.io.File;
import java.util.Vector;
import org.ccs.sandbox.sqltool.datamodel.MSWord;
import org.ccs.sandbox.sqltool.datamodel.mysql.*;
import org.ccs.sandbox.sqltool.datamodel.oracle.*;

public class MSWordWriter
{
  public int rows;
  public int cols;
  public File template;
  public File destination;
  public String[] s;

  public void write(NavicatSQL paramNavicatSQL, File paramFile1, File paramFile2)
    throws Exception
  {
    this.rows = paramNavicatSQL.getFields().length;
    this.cols = 5;
    this.template = paramFile1;
    this.destination = paramFile2;
    org.ccs.sandbox.sqltool.datamodel.mysql.Field[] arrayOfField = paramNavicatSQL.getFields();
    Vector localVector = new Vector();
    for (int i = 0; i < arrayOfField.length; ++i)
    {
    	org.ccs.sandbox.sqltool.datamodel.mysql.Field localField = arrayOfField[i];
      localVector.add("" + (i + 1));
      localVector.add(localField.getFieldName());
      localVector.add(localField.getFieldDesc().getMysqlTypeAndLength());
      localVector.add((localField.getFieldDesc().isNullable()) ? "null" : "not null");
      String str = localField.getFieldDesc().getComment();
      if ((((str == null) || (str.trim().length() == 0))) && (localField.getFieldDesc().isPrimaryKey()))
        str = localField.getFieldDesc().getPrimaryKeyDesc();
      localVector.add(str);
    }
    this.s = new String[localVector.size()];
    localVector.toArray(this.s);
    write();
  }

  public void write(ToadSQL paramToadSQL, File paramFile1, File paramFile2)
    throws Exception
  {
    this.rows = paramToadSQL.getFields().length;
    this.cols = 5;
    this.template = paramFile1;
    this.destination = paramFile2;
    org.ccs.sandbox.sqltool.datamodel.oracle.Field[] arrayOfField = paramToadSQL.getFields();
    Vector localVector = new Vector();
    for (int i = 0; i < arrayOfField.length; ++i)
    {
    	org.ccs.sandbox.sqltool.datamodel.oracle.Field localField = arrayOfField[i];
      localVector.add("" + (i + 1));
      localVector.add(localField.getFieldName());
      localVector.add(localField.getFieldDesc().getOracleTypeAndLength());
      String str1 = "";
      str1 = str1 + ((localField.getFieldDesc().isNullable()) ? "NULL" : "NOT NULL");
      str1 = str1 + ((localField.getFieldDesc().isPrimaryKey()) ? "\nPK" : "");
      str1 = str1 + ((localField.getFieldDesc().isForeignKey()) ? "\nFK" : "");
      str1 = str1 + ((localField.getFieldDesc().isUnique()) ? "\nUNIQUE" : "");
      localVector.add(str1);
      String str2 = localField.getFieldDesc().getComment();
      localVector.add(str2);
    }
    this.s = new String[localVector.size()];
    localVector.toArray(this.s);
    write();
  }

  private void write()
    throws Exception
  {
    MSWord localMSWord = new MSWord(this.template);
    localMSWord.open();
    if ((this.template != null) && (this.template.exists()))
    {
      localMSWord.insertRowsBelow(1, this.rows - 1);
      localMSWord.putTextToTable(1, 2, this.s, this.cols);
    }
    else
    {
      localMSWord.addNewTable(this.rows, this.cols);
      localMSWord.putTextToTable(1, 1, this.s, this.cols);
    }
    localMSWord.autoFitTable(1);
    localMSWord.saveAs(this.destination);
  }

  public void write(NavicatSQL[] paramArrayOfNavicatSQL, File paramFile1, File paramFile2)
    throws Throwable
  {
    for (int i = 0; i < paramArrayOfNavicatSQL.length; ++i)
    {
      NavicatSQL localNavicatSQL = paramArrayOfNavicatSQL[i];
      int j = localNavicatSQL.getFields().length;
      int k = 5;
      org.ccs.sandbox.sqltool.datamodel.mysql.Field[] arrayOfField = localNavicatSQL.getFields();
      Vector localVector = new Vector();
      for (int l = 0; l < arrayOfField.length; ++l)
      {
    	  org.ccs.sandbox.sqltool.datamodel.mysql.Field localField = arrayOfField[l];
        localVector.add("" + (l + 1));
        localVector.add(localField.getFieldName());
        localVector.add(localField.getFieldDesc().getMysqlTypeAndLength());
        localVector.add((localField.getFieldDesc().isNullable()) ? "null" : "not null");
        String str = localField.getFieldDesc().getComment();
        if ((((str == null) || (str.trim().length() == 0))) && (localField.getFieldDesc().isPrimaryKey()))
          str = localField.getFieldDesc().getPrimaryKeyDesc();
        localVector.add(str);
      }
      this.s = new String[localVector.size()];
      localVector.toArray(this.s);
      MSWord localMSWord = new MSWord(this.template);
      localMSWord.open();
      if ((this.template != null) && (this.template.exists()))
      {
        localMSWord.insertRowsBelow(1, j - 1);
        localMSWord.putTextToTable(1, 2, this.s, k);
      }
      else
      {
        localMSWord.addNewTable(j, k);
        localMSWord.putTextToTable(1, 1, this.s, k);
      }
      localMSWord.autoFitTable(1);
      localMSWord.saveAs(this.destination);
    }
  }
}