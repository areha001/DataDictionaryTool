package org.ccs.sandbox.sqltool.datamodel.oracle;

public class Field
{
  private String fieldName;
  private FieldDesc fieldDesc;

  public Field(String paramString, FieldDesc paramFieldDesc)
  {
    this.fieldName = paramString;
    this.fieldDesc = paramFieldDesc;
  }

  public FieldDesc getFieldDesc()
  {
    return this.fieldDesc;
  }

  public void setFieldDesc(FieldDesc paramFieldDesc)
  {
    this.fieldDesc = paramFieldDesc;
  }

  public String getFieldName()
  {
    return this.fieldName;
  }

  public void setFieldName(String paramString)
  {
    this.fieldName = paramString;
  }
}