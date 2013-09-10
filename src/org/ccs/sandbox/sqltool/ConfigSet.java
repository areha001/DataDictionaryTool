package org.ccs.sandbox.sqltool;

import ysb.common.Config;

public class ConfigSet extends Config
{
  private ConfigSet()
  {
    init("sys.properties");
  }

  public static Config getInstance()
  {
    if (me == null)
      me = new ConfigSet();
    return me;
  }
}