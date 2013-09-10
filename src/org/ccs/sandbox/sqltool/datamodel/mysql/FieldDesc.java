package org.ccs.sandbox.sqltool.datamodel.mysql;

import java.io.Serializable;

public class FieldDesc
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String longDesc;
  private boolean nullable;
  private boolean isPrimaryKey;
  private String mysqlTypeAndLength;
  private String mysqlType;
  private int length;
  private int dotLength;
  private String comment;
  private String primaryKeyDesc;

  public String getComment()
  {
    return this.comment;
  }

  public void setComment(String paramString)
  {
    this.comment = paramString;
  }

  public int getDotLength()
  {
    return this.dotLength;
  }

  public void setDotLength(int paramInt)
  {
    this.dotLength = paramInt;
  }

  public boolean isPrimaryKey()
  {
    return this.isPrimaryKey;
  }

  public void setPrimaryKey(boolean paramBoolean)
  {
    this.isPrimaryKey = paramBoolean;
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

  public boolean isNullable()
  {
    return this.nullable;
  }

  public void setNullable(boolean paramBoolean)
  {
    this.nullable = paramBoolean;
  }

  public String getPrimaryKeyDesc()
  {
    return this.primaryKeyDesc;
  }

  public void setPrimaryKeyDesc(String paramString)
  {
    this.primaryKeyDesc = paramString;
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
}