package org.ccs.sandbox.sqltool.input;

import java.io.File;
import ysb.common.FileUtil;

public class NavicatFileReader
{
  public String read(File paramFile, String paramString)
    throws Exception
  {
    return FileUtil.getFileContent(paramFile, paramString);
  }

  public String read(File paramFile)
    throws Exception
  {
    return FileUtil.getFileContent(paramFile, null);
  }
}