package org.ccs.sandbox.sqltool.parser;

import java.io.File;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.ccs.sandbox.sqltool.datamodel.mysql.Field;
import org.ccs.sandbox.sqltool.datamodel.mysql.FieldDesc;
import org.ccs.sandbox.sqltool.datamodel.mysql.NavicatSQL;
import org.ccs.sandbox.sqltool.datamodel.mysql.REField;
import ysb.common.FileUtil;
import ysb.common.REUtil;

public class NavicatParser
{
  private static final Logger log = Logger.getLogger(NavicatParser.class);
  private static final String CREATE_TABLE = "CREATE TABLE";
  private static final String INSERT_INTO = "INSERT INTO";
  private String navicatSQLText;
  private String validText;
  private String tableName;
  private String primaryKeyName;
  private String engine;
  private String charset;
  private REField[] reFields;
  private Field[] fields;

  public NavicatParser(String paramString)
  {
    log.debug("Contructor NavicatParser(String navicatSQLText) is called.");
    this.navicatSQLText = paramString;
    makeValidText();
  }

  public NavicatSQL parse()
    throws Exception
  {
    log.debug("makeTableName");
    makeTableName();
    log.debug("makeREFields");
    makeREFields();
    log.debug("makeEngine");
    makeEngine();
    log.debug("makeCharset");
    makeCharset();
    log.debug("makeFields");
    makeFields();
    NavicatSQL localNavicatSQL = new NavicatSQL();
    localNavicatSQL.setTableName(this.tableName);
    localNavicatSQL.setFields(this.fields);
    localNavicatSQL.setCharset(this.charset);
    localNavicatSQL.setEngine(this.engine);
    return localNavicatSQL;
  }

  private void makeFields()
  {
    Field[] arrayOfField = new Field[this.reFields.length];
    for (int i = 0; i < this.reFields.length; ++i)
    {
      REField localREField = this.reFields[i];
      FieldDesc localFieldDesc = new FieldDesc();
      localFieldDesc.setPrimaryKey(localREField.getName().equalsIgnoreCase(this.primaryKeyName));
      localFieldDesc.setLongDesc(localREField.getLongDesc());
      localFieldDesc.setMysqlTypeAndLength(localREField.getMysqlTypeAndLength());
      localFieldDesc.setMysqlType(localREField.getMysqlType());
      localFieldDesc.setLength(localREField.getLength());
      localFieldDesc.setDotLength(localREField.getDotLength());
      localFieldDesc.setNullable((!(localREField.isContainLiteral_NOT_())) || (!(localREField.isContainLiteral_NULL_())));
      localFieldDesc.setComment(localREField.getComment());
      localFieldDesc.setPrimaryKeyDesc((localREField.isContainLiteral_auto_increment_()) ? "自增主键" : "");
      Field localField = new Field(localREField.getName(),localFieldDesc);
      localField.setFieldName(localREField.getName());
      localField.setFieldDesc(localFieldDesc);
      arrayOfField[i] = localField;
    }
    this.fields = arrayOfField;
  }

  private void makeCharset()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "CHARSET=([\\w]+)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.charset = arrayOfString[0];
  }

  private void makeEngine()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "ENGINE=([\\w]+)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.engine = arrayOfString[0];
  }

  private void makePrimaryKey()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "PRIMARY KEY  \\(`([\\w]+)`\\)");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.primaryKeyName = arrayOfString[0];
  }

  private void makeTableName()
  {
    String[] arrayOfString = REUtil.getREGroupSet(this.validText, "CREATE TABLE `([\\w]+)` \\(");
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      this.tableName = arrayOfString[0];
  }

  private void makeREFields()
  {
    makePrimaryKey();
    String[] arrayOfString = REUtil.getREGroupVector(this.validText, "(`([\\w]+)` (([\\w]+)(?:\\(([\\d]+),?(\\d)?\\))?)( unsigned)?( NOT)?( NULL)?( default (?:NULL|'[^']*'))?( COMMENT '([^']+)')?( auto_increment)?)");
    Vector localVector = new Vector();
    REField localREField = new REField();
    int i = 13;
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
    this.validText = this.navicatSQLText;
    if (this.validText.indexOf("CREATE TABLE") != -1)
      this.validText = this.validText.substring(this.validText.indexOf("CREATE TABLE"));
    else
      log.warn("text (CREATE TABLE) cannot be found!");
    if (this.validText.indexOf("INSERT INTO") != -1)
      this.validText = this.validText.substring(0, this.validText.indexOf("INSERT INTO"));
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    String str = FileUtil.getFileContent(new File("c:/test.sql"), null);
    NavicatParser localNavicatParser = new NavicatParser(str);
    NavicatSQL localNavicatSQL = localNavicatParser.parse();
    log.info(localNavicatSQL.getCharset());
    log.info(localNavicatSQL.getEngine());
    log.info(localNavicatSQL.getTableName());
    log.info(localNavicatSQL.getFields()[0].getFieldName());
    log.info(localNavicatSQL.getFields()[0].getFieldDesc().getMysqlType());
    log.info(Integer.valueOf(localNavicatSQL.getFields()[0].getFieldDesc().getLength()));
    log.info(Boolean.valueOf(localNavicatSQL.getFields()[0].getFieldDesc().isPrimaryKey()));
    log.info(Boolean.valueOf(localNavicatSQL.getFields()[0].getFieldDesc().isNullable()));
  }
}