package ysb.swt.dialog;

import org.ccs.sandbox.sqltool.HelpDocument;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class LRCompositeWithMenu extends LRComposite
{
  protected Menu menuBar;

  protected String getCopyright()
  {
    return "CopyRight2007-2008\nMSN:smart.yin@hotmail.com\nEmail:missionstart@126.com\n主页：http://hi.baidu.com/uniquejava";
  }
  protected String getCopyrightPiggySnow()
  {
    return " 此版本最终由 PiggySnow 修改，修复word 2007 下不能输出的问题。 主页：http://www.piggysnow.com/";
  }

  public static void main(String[] paramArrayOfString)
  {
    LRCompositeWithMenu localLRCompositeWithMenu;
    try
    {
      localLRCompositeWithMenu = new LRCompositeWithMenu();
      ShellUtil.makeShellCentered(display, shell);
      localLRCompositeWithMenu.createSysMenuBar(shell);
      localLRCompositeWithMenu.makeComponents(shell);
      localLRCompositeWithMenu.readAndDispatch(display, shell);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected Function[] getSysFunctions1()
  {
    return null;
  }

  protected Function[] getSysFunctions2()
  {
    return null;
  }

  protected void createSysMenuBar(Shell paramShell)
  {
    int i;
    MenuItem localMenuItem4;
    this.menuBar = new Menu(paramShell, 2);
    MenuItem localMenuItem1 = new MenuItem(this.menuBar, 64);
    localMenuItem1.setText("&File");
    Menu localMenu = new Menu(paramShell, 4);
    localMenuItem1.setMenu(localMenu);
    Object localObject = new MenuItem(localMenu, 8);
    ((MenuItem)localObject).setText("&Save");
    new MenuItem(localMenu, 2);
    MenuItem localMenuItem2 = new MenuItem(localMenu, 8);
    localMenuItem2.setText("&Exit\t ESC");
    localMenuItem2.setAccelerator(27);
    localMenuItem2.addSelectionListener(new SelectionAdapter()
    {
    @Override
      public void widgetSelected(SelectionEvent e)
      {
        shell.dispose();
      }
    });
    localMenuItem1 = new MenuItem(this.menuBar, 64);
    localMenuItem1.setText(getSysFunctions1Text());
    localMenu = new Menu(paramShell, 4);
    localMenuItem1.setMenu(localMenu);
    localObject = getSysFunctions1();
    if (localObject != null){
    	Function[] functions = (Function[])localObject;
      for (i = 0; i < functions.length; ++i)
      {
    	  final Function   localFunction = functions[i];
        localMenuItem4 = new MenuItem(localMenu, 8);
        localMenuItem4.setText(localFunction.getText());
        localMenuItem4.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
          {
        	  localFunction.execute();
          }
        });
      }
    }
    localMenuItem1 = new MenuItem(this.menuBar, 64);
    localMenuItem1.setText(getSysFunctions2Text());
    localMenu = new Menu(paramShell, 4);
    localMenuItem1.setMenu(localMenu);
    localObject = getSysFunctions2();
    if (localObject != null){

    	Function[] functions = (Function[])localObject;
      for (i = 0; i < functions.length; ++i)
      {
    	  final Function   localFunction = functions[i];
        localMenuItem4 = new MenuItem(localMenu, 8);
        localMenuItem4.setText(localFunction.getText());
        localMenuItem4.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
          {
        	  localFunction.execute();
          }
        });
      }
    }
    localMenuItem1 = new MenuItem(this.menuBar, 64);
    localMenuItem1.setText("&Help");
    localMenu = new Menu(paramShell, 4);
    localMenuItem1.setMenu(localMenu);
    localObject = new MenuItem(localMenu, 8);
    ((MenuItem)localObject).setText("查看帮助文档");
    ((MenuItem)localObject).addSelectionListener(new SelectionAdapter()
    {
        @Override
        public void widgetSelected(SelectionEvent e)
      {
        HelpDocumentDialog localHelpDocumentDialog = new HelpDocumentDialog(getShell());
        localHelpDocumentDialog.setHelpText(HelpDocument.getInstance().getText());
        localHelpDocumentDialog.open();
      }
    });
    MenuItem localMenuItem3 = new MenuItem(localMenu, 8);
    localMenuItem3.setText("关于 科比代码 工作室");
    localMenuItem3.addSelectionListener(new SelectionAdapter()
    {
        @Override
        public void widgetSelected(SelectionEvent e)
      {
        MessageDialog.openInformation(null, "CobeCode Studio", getCopyright());
      }
    });
    paramShell.setMenuBar(this.menuBar);

    localMenuItem4 = new MenuItem(localMenu, 8);
    localMenuItem4.setText("关于 PiggySnow 工作室");
    localMenuItem4.addSelectionListener(new SelectionAdapter()
    {
        @Override
        public void widgetSelected(SelectionEvent e)
      {
        MessageDialog.openInformation(null, "PiggySnow 工作室", getCopyrightPiggySnow());
      }
    });
    paramShell.setMenuBar(this.menuBar);
  }

  protected String getSysFunctions1Text()
  {
    return "F&unction1";
  }

  protected String getSysFunctions2Text()
  {
    return "F&unction2";
  }
}