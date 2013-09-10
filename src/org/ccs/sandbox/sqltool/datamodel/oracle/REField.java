package org.ccs.sandbox.sqltool.datamodel.oracle;

import org.apache.log4j.Logger;

public class REField
{
  private static final Logger log = Logger.getLogger(REField.class);
  public static final String REGEX = " (([\\w]+) (([\\w]+)(?:\\(([\\d]+)(?: BYTE?)?,?(\\d)?\\))?)( DEFAULT \\w+)?( NOT)?( NULL)?),";
  public static final int REGEX_GROUP_COUNT = 9;
  public static final int FIELD_LONG_DESC = 0;
  public static final int FIELD_NAME = 1;
  public static final int FIELD_ORACLE_TYPE_AND_LENGTH = 2;
  public static final int FIELD_ORACLE_TYPE = 3;
  public static final int FIELD_LENGTH = 4;
  public static final int FIELD_DOT_LENGTH = 5;
  public static final int FIELD_CONTAIN_LITERAL_DEFAULT = 6;
  public static final int FIELD_CONTAIN_LITERAL_NOT = 7;
  public static final int FIELD_CONTAIN_LITERAL_NULL = 8;
  private String longDesc;
  private String name;
  private String oracleTypeAndLength;
  private String oracleType;
  private int length;
  private int dotLength;
  private boolean containLiteral_NOT_;
  private boolean containLiteral_NULL_;
  private boolean containLiteral_DEFAULT_;

  public void setField(int paramInt, String paramString)
  {
    log.debug(paramInt + "======>" + paramString);
    switch (paramInt)
    {
    case 0:
      setLongDesc(paramString);
      break;
    case 1:
      setName(paramString);
      break;
    case 2:
      setOracleTypeAndLength(paramString);
      break;
    case 3:
      setOracleType(paramString);
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
    case 7:
      setContainLiteral_NOT_(paramString != null);
      break;
    case 8:
      setContainLiteral_NULL_(paramString != null);
      break;
    case 6:
      setContainLiteral_DEFAULT_(paramString != null);
    }
  }

  public boolean isContainLiteral_DEFAULT_()
  {
    return this.containLiteral_DEFAULT_;
  }

  public void setContainLiteral_DEFAULT_(boolean paramBoolean)
  {
    this.containLiteral_DEFAULT_ = paramBoolean;
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

  public String getLongDesc()
  {
    return this.longDesc;
  }

  public void setLongDesc(String paramString)
  {
    this.longDesc = paramString;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
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
}