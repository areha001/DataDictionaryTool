package org.ccs.sandbox.sqltool.output;

import java.io.File;
import java.util.Vector;
import org.ccs.sandbox.sqltool.datamodel.MSWord;
import org.ccs.sandbox.sqltool.datamodel.mysql.Field;
import org.ccs.sandbox.sqltool.datamodel.mysql.FieldDesc;
import org.ccs.sandbox.sqltool.datamodel.mysql.NavicatSQL;

public class BatchWordWriter
{
  public int[] rows;
  public int[] cols;
  public File template;
  public File destination;
  public String[][] s;
  public NavicatSQL[] navicatSQLs;
  public String[] tableNames;

  public void write(NavicatSQL[] paramArrayOfNavicatSQL, File paramFile1, File paramFile2)
    throws Throwable
  {
    this.navicatSQLs = paramArrayOfNavicatSQL;
    int x = paramArrayOfNavicatSQL.length;
    this.template = paramFile1;
    this.destination = paramFile2;
    this.rows = new int[paramArrayOfNavicatSQL.length];
    this.cols = new int[paramArrayOfNavicatSQL.length];
    this.s = new String[paramArrayOfNavicatSQL.length][];
    this.tableNames = new String[paramArrayOfNavicatSQL.length];
    for (int i = 0; i < paramArrayOfNavicatSQL.length; ++i)
    {
      NavicatSQL localNavicatSQL = paramArrayOfNavicatSQL[i];
      this.rows[i] = localNavicatSQL.getFields().length;
      this.cols[i] = 5;
      this.tableNames[i] = localNavicatSQL.getTableName();
      Field[] arrayOfField = localNavicatSQL.getFields();
      Vector localVector = new Vector();
      for (int j = 0; j < arrayOfField.length; ++j)
      {
        Field localField = arrayOfField[j];
        localVector.add("" + (j + 1));
        localVector.add(localField.getFieldName());
        localVector.add(localField.getFieldDesc().getMysqlTypeAndLength());
        localVector.add((localField.getFieldDesc().isNullable()) ? "null" : "not null");
        String str = localField.getFieldDesc().getComment();
        if ((((str == null) || (str.trim().length() == 0))) && (localField.getFieldDesc().isPrimaryKey()))
          str = localField.getFieldDesc().getPrimaryKeyDesc();
        localVector.add(str);
      }
      this.s[i] = new String[localVector.size()];
      localVector.toArray(this.s[i]);
    }
    write();
  }

  private void write()
    throws Throwable
  {
    MSWord localMSWord = new MSWord(this.template);
    localMSWord.open();
    if ((this.template != null) && (this.template.exists()))
    {
      localMSWord.makeTables(this.tableNames);
      for (int i = 0; i < this.navicatSQLs.length; ++i)
      {
        localMSWord.insertRowsBelow(i + 1, this.rows[i] - 1);
        localMSWord.putTextToTable(i + 1, 2, this.s[i], this.cols[i]);
        localMSWord.autoFitTable(i + 1);
      }
    }
    localMSWord.saveAs(this.destination);
  }
}