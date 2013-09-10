package ysb.swt.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HelpDocumentDialog extends Dialog
{
  private String helpText;

  public String getHelpText()
  {
    return this.helpText;
  }

  public void setHelpText(String paramString)
  {
    this.helpText = paramString;
  }

  protected HelpDocumentDialog(Shell paramShell)
  {
    super(paramShell);
  }

  protected Point getInitialSize()
  {
    return new Point(620, 600);
  }

  protected int getShellStyle()
  {
    return (super.getShellStyle() | 0x10 | 0x400);
  }

  protected Control createContents(Composite paramComposite)
  {
    paramComposite.getShell().setText("帮助文档");
    paramComposite.setLayout(new FillLayout());
    Composite localComposite = new Composite(paramComposite, 0);
    localComposite.setLayout(new GridLayout());
    Text localText = new Text(localComposite, 770);
    localText.setLayoutData(new GridData(1808));
    localText.setEditable(false);
    localText.setText(getHelpText());
    return localComposite;
  }
}