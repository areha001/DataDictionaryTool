package org.ccs.sandbox.sqltool.datamodel.oracle;

public class CommentDesc
{
  private String column_name;
  private String comment;

  public void setField(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case 0:
      setColumn_name(paramString);
      break;
    case 1:
      setComment(paramString);
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

  public String getComment()
  {
    return this.comment;
  }

  public void setComment(String paramString)
  {
    this.comment = paramString;
  }
}