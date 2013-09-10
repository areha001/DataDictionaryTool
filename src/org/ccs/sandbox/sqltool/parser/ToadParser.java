package org.ccs.sandbox.sqltool.parser;

import java.io.File;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.ccs.sandbox.sqltool.datamodel.oracle.CommentDesc;
import org.ccs.sandbox.sqltool.datamodel.oracle.Field;
import org.ccs.sandbox.sqltool.datamodel.oracle.FieldDesc;
import org.ccs.sandbox.sqltool.datamodel.oracle.ForeignKeyDesc;
import org.ccs.sandbox.sqltool.datamodel.oracle.REField;
import org.ccs.sandbox.sqltool.datamodel.oracle.ToadSQL;

import ysb.common.FileUtil;
import ysb.common.REUtil;

public class ToadParser
{
  private static final Logger log = Logger.getLogger(ToadParser.class);
  private static final String CREATE_TABLE = "CREATE TABLE";
  private static final String TABLESPACE = "TABLESPACE";
  private String toadSQLText;
  private String validText;
  private String tableName;
  private String primaryKeyName;
  private REField[] reFields;
  private Field[] fields;
  private String uniqueName;
  private ForeignKeyDesc[] fks;
  private CommentDesc[] comments;

  public ToadParser(String paramString)
  {
    log.debug("Contructor ToadParser(String toadSQLText) is called.");
    this.toadSQLText = paramString;
    makeValidText();
  }

  public ToadSQL parse()
    throws Exception
  {
    log.debug("makeTableName");
    makeTableName();
    log.debug("makeREFields");
    makeREFields();
    log.debug("makeFields");
    makeFields();
    ToadSQL localToadSQL = new ToadSQL();
    localToadSQL.setTableName(this.tableName);
    localToadSQL.setFields(this.fields);
    return localToadSQL;
  }

  private void makeFields()
  {
    Field[] arrayOfField = new Field[this.reFields.length];
    for (int i = 0; i < this.reFields.length; ++i)
    {
      int j;
      boolean bool;
      REField localREField = this.reFields[i];
      FieldDesc localFieldDesc = new FieldDesc();
      localFieldDesc.setPrimaryKey(localREField.getName().equalsIgnoreCase(this.primaryKeyName));
      log.debug(localREField.getName());
      localFieldDesc.setLongDesc(localREField.getLongDesc());
      log.debug(localREField.getLongDesc());
      localFieldDesc.setOracleTypeAndLength(localREField.getOracleTypeAndLength().replaceAll(" BYTE", ""));
      log.debug(localREField.getOracleTypeAndLength());
      localFieldDesc.setOracleType(localREField.getOracleType());
      log.debug(localREField.getOracleType());
      localFieldDesc.setLength(localREField.getLength());
      log.debug(Integer.valueOf(localREField.getLength()));
      localFieldDesc.setDotLength(localREField.getDotLength());
      log.debug(Integer.valueOf(localREField.getDotLength()));
      localFieldDesc.setNullable((!(localREField.isContainLiteral_NOT_())) || (!(localREField.isContainLiteral_NULL_())));
      localFieldDesc.setNullable((localFieldDesc.isPrimaryKey()) ? false : localFieldDesc.isNullable());
      localFieldDesc.setUnique(localREField.getName().equalsIgnoreCase(this.uniqueName));
      if (this.fks != null)
        for (j = 0; j < this.fks.length; ++j)
        {
          bool = localREField.getName().equalsIgnoreCase(this.fks[j].getColumn_name());
          if (bool)
          {
            localFieldDesc.setForeignKey(true);
            localFieldDesc.setForeignKeyDesc(this.fks[j]);
            localFieldDesc.setComment("\n引用" + this.fks[j].getRef_table_name() + "表" + this.fks[j].getRef_table_column_name());
            break;
          }
        }
      if (this.comments != null)
        for (j = 0; j < this.comments.length; ++j)
        {
          bool = localREField.getName().equalsIgnoreCase(this.comments[j].getColumn_name());
          if (bool)
          {
            localFieldDesc.setComment(this.comments[j].getComment() + ((localFieldDesc.getComment() == null) ? "" : localFieldDesc.getComment()));
            log.debug("new comment <" + localFieldDesc.getComment() + "> added !!!!!!!!!!!!!!!!!!!!!!");
            break;
          }
        }
      Field localField = new Field(localREField.getName(), localFieldDesc);
      localField.setFieldName(localREField.getName());
      localField.setFieldDesc(localFieldDesc);
      arrayOfField[i] = localField;
    }
    this.fields = arrayOfField;
  }

  private void makePrimaryKey()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "PRIMARY KEY \\(([\\w]+)\\)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.primaryKeyName = arrayOfString[0];
  }

  private void makeUnique()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "UNIQUE \\(([\\w]+)\\)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
    {
      this.uniqueName = arrayOfString[0];
      log.debug("UNIQUE: " + this.uniqueName);
    }
  }

  private void makeForeignKey()
  {
    String[] arrayOfString = REUtil.getREGroupVector(this.validText, " FOREIGN KEY \\(([\\w]+)\\)\\s+REFERENCES ([\\w]+) \\(([\\w]+)\\)");
    int i = 3;
    Vector localVector = new Vector();
    ForeignKeyDesc localForeignKeyDesc = new ForeignKeyDesc();
    for (int j = 0; j < arrayOfString.length; ++j)
    {
      localForeignKeyDesc.setField(j % i, arrayOfString[j]);
      if (j % i == i - 1)
      {
        localVector.add(localForeignKeyDesc);
        localForeignKeyDesc = new ForeignKeyDesc();
      }
    }
    ForeignKeyDesc[] arrayOfForeignKeyDesc = new ForeignKeyDesc[localVector.size()];
    localVector.toArray(arrayOfForeignKeyDesc);
    this.fks = arrayOfForeignKeyDesc;
  }

  private void makeComment()
  {
    int i = this.toadSQLText.indexOf("COMMENT ON COLUMN");
    if (i != -1)
    {
      String str = this.toadSQLText.substring(i);
      log.debug("COMMENTS: " + str);
      String[] arrayOfString = REUtil.getREGroupVector(str, "COMMENT ON COLUMN [\\w]+\\.([\\w]+) IS '([^']+)'");
      int j = 2;
      Vector localVector = new Vector();
      CommentDesc localCommentDesc = new CommentDesc();
      for (int k = 0; k < arrayOfString.length; ++k)
      {
        localCommentDesc.setField(k % j, arrayOfString[k]);
        if (k % j == j - 1)
        {
          localVector.add(localCommentDesc);
          localCommentDesc = new CommentDesc();
        }
      }
      log.debug("找到" + localVector.size() + "条comment");
      CommentDesc[] arrayOfCommentDesc = new CommentDesc[localVector.size()];
      localVector.toArray(arrayOfCommentDesc);
      this.comments = arrayOfCommentDesc;
    }
  }

  private void makeTableName()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.toadSQLText, "CREATE TABLE ([\\w]+)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.tableName = arrayOfString[0];
  }

  private void makeREFields()
  {
    makePrimaryKey();
    makeUnique();
    makeForeignKey();
    makeComment();
    String[] arrayOfString = REUtil.getREGroupVector(this.validText, " (([\\w]+) (([\\w]+)(?:\\(([\\d]+)(?: BYTE?)?,?(\\d)?\\))?)( DEFAULT \\w+)?( NOT)?( NULL)?),");
    Vector localVector = new Vector();
    REField localREField = new REField();
    int i = 9;
    for (int j = 0; j < arrayOfString.length; ++j)
    {
      localREField.setField(j % i, arrayOfString[j]);
      if (j % i == i - 1)
      {
        localVector.add(localREField);
        localREField = new REField();
      }
    }
    REField[] arrayOfREField = new REField[localVector.size()];
    localVector.toArray(arrayOfREField);
    this.reFields = arrayOfREField;
  }

  private void makeValidText()
  {
    this.validText = this.toadSQLText;
    if (this.validText.indexOf("CREATE TABLE") != -1)
      this.validText = this.validText.substring(this.validText.indexOf("CREATE TABLE"));
    else
      log.warn("text (CREATE TABLE) cannot be found!");
    if (this.validText.indexOf("TABLESPACE") != -1)
      this.validText = this.validText.substring(0, this.validText.indexOf("TABLESPACE"));
    this.validText = this.validText.replaceAll(" +", " ");
    this.validText = this.validText.substring(this.validText.indexOf("("), this.validText.lastIndexOf(")"));
    if (this.validText.indexOf("CONSTRAINT") == -1)
      this.validText = this.validText.trim() + ",";
    log.debug(" =================== validText ==============");
    log.debug(this.validText);
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    String str = FileUtil.getFileContent(new File("c:/test.sql"), null);
    ToadParser localToadParser = new ToadParser(str);
    ToadSQL localToadSQL = localToadParser.parse();
    log.info(localToadSQL.getCharset());
    log.info(localToadSQL.getEngine());
    log.info(localToadSQL.getTableName());
    log.info(localToadSQL.getFields()[0].getFieldName());
    log.info(Integer.valueOf(localToadSQL.getFields()[0].getFieldDesc().getLength()));
    log.info(Boolean.valueOf(localToadSQL.getFields()[0].getFieldDesc().isPrimaryKey()));
    log.info(Boolean.valueOf(localToadSQL.getFields()[0].getFieldDesc().isNullable()));
  }
}