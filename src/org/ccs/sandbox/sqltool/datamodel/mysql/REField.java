package org.ccs.sandbox.sqltool.datamodel.mysql;

public class REField
{
  public static final String REGEX = "(`([\\w]+)` (([\\w]+)(?:\\(([\\d]+),?(\\d)?\\))?)( unsigned)?( NOT)?( NULL)?( default (?:NULL|'[^']*'))?( COMMENT '([^']+)')?( auto_increment)?)";
  public static final int REGEX_GROUP_COUNT = 13;
  public static final int FIELD_LONG_DESC = 0;
  public static final int FIELD_NAME = 1;
  public static final int FIELD_MYSQL_TYPE_AND_LENGTH = 2;
  public static final int FIELD_MYSQL_TYPE = 3;
  public static final int FIELD_LENGTH = 4;
  public static final int FIELD_DOT_LENGTH = 5;
  public static final int FIELD_CONTAIN_LITERAL_UNSIGNED = 6;
  public static final int FIELD_CONTAIN_LITERAL_NOT = 7;
  public static final int FIELD_CONTAIN_LITERAL_NULL = 8;
  public static final int FIELD_CONTAIN_LITERAL_DEFAULT = 9;
  public static final int FIELD_CONTAIN_LITERAL_COMMENT = 10;
  public static final int FIELD_COMMENT = 11;
  public static final int FIELD_CONTAIN_LITERAL_auto_increment_ = 12;
  private String longDesc;
  private String name;
  private String mysqlTypeAndLength;
  private String mysqlType;
  private int length;
  private int dotLength;
  private boolean containLiteral_unsigned_;
  private boolean containLiteral_NOT_;
  private boolean containLiteral_NULL_;
  private boolean containLiteral_default_;
  private boolean containLiteral_COMMENT_;
  private boolean containLiteral_auto_increment_;
  private String comment;

  public void setField(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case 0:
      setLongDesc(paramString);
      break;
    case 1:
      setName(paramString);
      break;
    case 2:
      setMysqlTypeAndLength(paramString);
      break;
    case 3:
      setMysqlType(paramString);
      break;
    case 4:
      if (paramString == null)
        return;
      setLength(new Integer(paramString).intValue());
      break;
    case 5:
      if (paramString == null)
        return;
      setDotLength(new Integer(paramString).intValue());
      break;
    case 6:
      setContainLiteral_unsigned_(paramString != null);
      break;
    case 7:
      setContainLiteral_NOT_(paramString != null);
      break;
    case 8:
      setContainLiteral_NULL_(paramString != null);
      break;
    case 9:
      setContainLiteral_default_(paramString != null);
      break;
    case 10:
      setContainLiteral_COMMENT_(paramString != null);
      break;
    case 11:
      if (!(isContainLiteral_COMMENT_()))
        return;
      setComment(paramString);
      break;
    case 12:
      setContainLiteral_auto_increment_(paramString != null);
    }
  }

  public String getComment()
  {
    return this.comment;
  }

  public void setComment(String paramString)
  {
    this.comment = paramString;
  }

  public boolean isContainLiteral_COMMENT_()
  {
    return this.containLiteral_COMMENT_;
  }

  public void setContainLiteral_COMMENT_(boolean paramBoolean)
  {
    this.containLiteral_COMMENT_ = paramBoolean;
  }

  public boolean isContainLiteral_default_()
  {
    return this.containLiteral_default_;
  }

  public void setContainLiteral_default_(boolean paramBoolean)
  {
    this.containLiteral_default_ = paramBoolean;
  }

  public boolean isContainLiteral_NOT_()
  {
    return this.containLiteral_NOT_;
  }

  public void setContainLiteral_NOT_(boolean paramBoolean)
  {
    this.containLiteral_NOT_ = paramBoolean;
  }

  public boolean isContainLiteral_NULL_()
  {
    return this.containLiteral_NULL_;
  }

  public void setContainLiteral_NULL_(boolean paramBoolean)
  {
    this.containLiteral_NULL_ = paramBoolean;
  }

  public boolean isContainLiteral_unsigned_()
  {
    return this.containLiteral_unsigned_;
  }

  public void setContainLiteral_unsigned_(boolean paramBoolean)
  {
    this.containLiteral_unsigned_ = paramBoolean;
  }

  public int getDotLength()
  {
    return this.dotLength;
  }

  public void setDotLength(int paramInt)
  {
    this.dotLength = paramInt;
  }

  public int getLength()
  {
    return this.length;
  }

  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }

  public String getMysqlType()
  {
    return this.mysqlType;
  }

  public void setMysqlType(String paramString)
  {
    this.mysqlType = paramString;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String getLongDesc()
  {
    return this.longDesc;
  }

  public void setLongDesc(String paramString)
  {
    this.longDesc = paramString;
  }

  public String getMysqlTypeAndLength()
  {
    return this.mysqlTypeAndLength;
  }

  public void setMysqlTypeAndLength(String paramString)
  {
    this.mysqlTypeAndLength = paramString;
  }

  public boolean isContainLiteral_auto_increment_()
  {
    return this.containLiteral_auto_increment_;
  }

  public void setContainLiteral_auto_increment_(boolean paramBoolean)
  {
    this.containLiteral_auto_increment_ = paramBoolean;
  }
}