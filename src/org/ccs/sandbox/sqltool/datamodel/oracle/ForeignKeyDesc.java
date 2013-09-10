package org.ccs.sandbox.sqltool.datamodel.oracle;

public class ForeignKeyDesc
{
  private String column_name;
  private String ref_table_name;
  private String ref_table_column_name;

  public void setField(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case 0:
      setColumn_name(paramString);
      break;
    case 1:
      setRef_table_name(paramString);
      break;
    case 2:
      setRef_table_column_name(paramString);
    }
  }

  public String getColumn_name()
  {
    return this.column_name;
  }

  public void setColumn_name(String paramString)
  {
    this.column_name = paramString;
  }

  public String getRef_table_column_name()
  {
    return this.ref_table_column_name;
  }

  public void setRef_table_column_name(String paramString)
  {
    this.ref_table_column_name = paramString;
  }

  public String getRef_table_name()
  {
    return this.ref_table_name;
  }

  public void setRef_table_name(String paramString)
  {
    this.ref_table_name = paramString;
  }
}