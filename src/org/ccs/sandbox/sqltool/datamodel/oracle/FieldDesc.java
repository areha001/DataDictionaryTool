package org.ccs.sandbox.sqltool.datamodel.oracle;

import java.io.Serializable;

public class FieldDesc
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String longDesc;
  private boolean nullable;
  private boolean isPrimaryKey;
  private boolean isUnique;
  private String oracleTypeAndLength;
  private String oracleType;
  private int length;
  private int dotLength;
  private String comment;
  private String primaryKeyDesc;
  private boolean isForeignKey;
  private ForeignKeyDesc foreignKeyDesc;

  public ForeignKeyDesc getForeignKeyDesc()
  {
    return this.foreignKeyDesc;
  }

  public void setForeignKeyDesc(ForeignKeyDesc paramForeignKeyDesc)
  {
    this.foreignKeyDesc = paramForeignKeyDesc;
  }

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

  public boolean isUnique()
  {
    return this.isUnique;
  }

  public void setUnique(boolean paramBoolean)
  {
    this.isUnique = paramBoolean;
  }

  public int getLength()
  {
    return this.length;
  }

  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }

  public String getLongDesc()
  {
    return this.longDesc;
  }

  public void setLongDesc(String paramString)
  {
    this.longDesc = paramString;
  }

  public boolean isNullable()
  {
    return this.nullable;
  }

  public void setNullable(boolean paramBoolean)
  {
    this.nullable = paramBoolean;
  }

  public String getOracleType()
  {
    return this.oracleType;
  }

  public void setOracleType(String paramString)
  {
    this.oracleType = paramString;
  }

  public String getOracleTypeAndLength()
  {
    return this.oracleTypeAndLength;
  }

  public void setOracleTypeAndLength(String paramString)
  {
    this.oracleTypeAndLength = paramString;
  }

  public String getPrimaryKeyDesc()
  {
    return this.primaryKeyDesc;
  }

  public void setPrimaryKeyDesc(String paramString)
  {
    this.primaryKeyDesc = paramString;
  }

  public boolean isForeignKey()
  {
    return this.isForeignKey;
  }

  public void setForeignKey(boolean paramBoolean)
  {
    this.isForeignKey = paramBoolean;
  }
}