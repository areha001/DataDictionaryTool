package org.ccs.sandbox.sqltool.datamodel;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

public class MSWord
{
  private static final Logger log = Logger.getLogger(MSWord.class);
  ActiveXComponent word;
  Dispatch documents;
  Dispatch document;
  Dispatch selection;
  private Dispatch dTables;
  private Dispatch dTable;
  private Dispatch dCell;
  private Dispatch dRows;
  private Dispatch dRow;
  private File template;

  public MSWord(File paramFile)
  {
    this.template = paramFile;
  }

  public void open()
    throws IOException
  {
    this.word = new ActiveXComponent("Word.Application");
    this.word.setProperty("Visible", new Variant(true));
    this.documents = this.word.getProperty("Documents").toDispatch();
    if ((this.template != null) && (this.template.exists()))
      this.document = Dispatch.call(this.documents, "Open", this.template.getPath()).toDispatch();
    else
      this.document = Dispatch.call(this.documents, "Add").toDispatch();
    this.selection = this.word.getProperty("Selection").toDispatch();
  }

  public void addNewTable(int paramInt1, int paramInt2)
  {
    this.dTables = Dispatch.get(this.document, "Tables").toDispatch();
    Dispatch localDispatch = Dispatch.get(this.selection, "Range").toDispatch();
    this.dTable = Dispatch.call(this.dTables, "Add", localDispatch, new Integer(paramInt1), new Integer(paramInt2), new Integer(1), new Integer(0)).toDispatch();
    select(this.dTable);
    moveDown(1);
  }

  public void duplicateLastRow(int paramInt1, int paramInt2)
  {
    searchRow(getRowCount(paramInt1));
    Dispatch.call(this.dRow, "select");
    Dispatch.call(this.selection, "Copy");
    for (int i = 0; i < paramInt2; ++i)
      Dispatch.call(this.selection, "Paste");
  }

  public void copyLastRow(int paramInt)
  {
    searchRow(getRowCount(paramInt));
    Dispatch.call(this.dRow, "select");
    Dispatch.call(this.selection, "Copy");
  }

  public int getRowCount(int paramInt)
  {
    searchRows(paramInt);
    int i = Dispatch.get(this.dRows, "count").changeType((short)3).getInt();
    return i;
  }

  public void copyRow(int paramInt1, int paramInt2)
  {
    searchTable(paramInt1);
    searchRows();
    this.dRow = searchRow(paramInt2);
    Dispatch.call(this.dRow, "select");
    Dispatch.call(this.selection, "Copy");
  }

  public Dispatch searchRow(int paramInt)
  {
    this.dRow = Dispatch.invoke(this.dRows, "item", 1, new Object[] { new Integer(paramInt) }, new int[1]).toDispatch();
    return this.dRow;
  }

  public Dispatch searchRows(int paramInt)
  {
    searchTable(paramInt);
    this.dRows = Dispatch.get(this.dTable, "rows").toDispatch();
    return this.dRows;
  }

  public Dispatch searchRows()
  {
    this.dRows = Dispatch.get(this.dTable, "rows").toDispatch();
    return this.dRows;
  }

  public void paste()
  {
    Dispatch.call(this.selection, "Paste");
  }

  public void putTextToTable(int paramInt1, int paramInt2, String[] paramArrayOfString, int paramInt3)
  {
    searchTable(paramInt1);
    for (int k = 0; k < paramArrayOfString.length; ++k)
    {
      int i = k / paramInt3 + paramInt2;
      int j = k % paramInt3 + 1;
      searchCell(i, j);
      select(this.dCell);
      String str = paramArrayOfString[k];
      if (str != null)
      {
        str = str.replaceAll("(\\u005C)+r", "");
        str = str.replaceAll("(\\u005C)+", "$1");
        String[] arrayOfString = str.split("\\u005Cn");
        for (int l = 0; l < arrayOfString.length; ++l)
        {
          write(arrayOfString[l]);
          if ((arrayOfString.length > 1) && (l != arrayOfString.length - 1))
            println();
        }
      }
    }
  }

  public void putTextToCell(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    searchCell(paramInt1, paramInt2, paramInt3);
    select(this.dCell);
    Dispatch.put(this.selection, "Text", paramString);
  }

  private void select(Dispatch paramDispatch)
  {
    Dispatch.call(paramDispatch, "Select");
  }

  public Dispatch searchCell(int paramInt1, int paramInt2, int paramInt3)
  {
    searchTable(paramInt1);
    this.dCell = Dispatch.call(this.dTable, "Cell", new Variant(paramInt2), new Variant(paramInt3)).toDispatch();
    return this.dCell;
  }

  public Dispatch searchCell(int paramInt1, int paramInt2)
  {
    this.dCell = Dispatch.call(this.dTable, "Cell", new Variant(paramInt1), new Variant(paramInt2)).toDispatch();
    return this.dCell;
  }

  public Dispatch searchTable(int paramInt)
  {
    searchTables();
    this.dTable = Dispatch.call(this.dTables, "Item", new Variant(paramInt)).toDispatch();
    return this.dTable;
  }

  public void searchTables()
  {
    this.dTables = Dispatch.get(this.document, "Tables").toDispatch();
  }

  public void println(String paramString)
  {
    write(paramString);
    home();
    end();
    cmd("TypeParagraph");
  }

  public void print(String paramString)
  {
    home();
    end();
    write(paramString);
  }

  public void write(String paramString)
  {
    Dispatch.put(this.selection, "Text", paramString);
  }

  public void println()
  {
    home();
    end();
    cmd("TypeParagraph");
  }

  public void saveAs(File paramFile)
    throws Exception
  {
    if (paramFile != null)
    {
      if (!(paramFile.exists()))
        paramFile.createNewFile();
      Dispatch localDispatch = Dispatch.call(this.word, "WordBasic").getDispatch();
      Dispatch.invoke(localDispatch, "FileSaveAs", 1, new Object[] { paramFile.getPath(), new Variant(true), new Variant(false) }, new int[1]);
    }
  }

  public void close(boolean paramBoolean)
  {
    Dispatch.call(this.document, "Close", new Variant(paramBoolean));
    this.word.invoke("Quit", new Variant[0]);
  }

  public void moveDown(int paramInt)
  {
    for (int i = 0; i < paramInt; ++i)
      Dispatch.call(this.selection, "MoveDown");
  }

  public void moveLeft(int paramInt)
  {
    for (int i = 0; i < paramInt; ++i)
      Dispatch.call(this.selection, "MoveLeft");
  }

  public void moveRight(int paramInt)
  {
    for (int i = 0; i < paramInt; ++i)
      Dispatch.call(this.selection, "MoveRight");
  }

  public void insertRowsBelow(int paramInt1, int paramInt2)
  {
    int i = getRowCount(paramInt1);
    Dispatch localDispatch = searchCell(paramInt1, i, 1);
    select(localDispatch);
    for (int j = 0; j < paramInt2; ++j)
      cmd("InsertRowsBelow");
  }

  public void cmd(String paramString)
  {
    Dispatch.call(this.selection, paramString);
  }

  public void cmd(String paramString, Object paramObject)
  {
    Dispatch.call(this.selection, paramString, paramObject);
  }

  public void home()
  {
    Dispatch.call(this.selection, "HomeKey", "5");
  }

  public void end()
  {
    Dispatch.call(this.selection, "EndKey", "5");
  }

  public void goToBegin()
  {
    Dispatch.call(this.selection, "HomeKey", "6");
  }

  public void goToEnd()
  {
    Dispatch.call(this.selection, "EndKey", "6");
  }

  public void autoFitTable(int paramInt)
  {
    searchTable(paramInt);
    Dispatch.call(this.dTable, "AutoFitBehavior", Integer.valueOf(1));
    Dispatch.call(this.dTable, "AutoFitBehavior", Integer.valueOf(2));
  }

  public void makeTables(int paramInt)
  {
    cmd("WholeStory");
    cmd("Copy");
    for (int i = 0; i < paramInt; ++i)
      cmd("PasteAndFormat", Integer.valueOf(0));
  }

  public void makeTables(String[] paramArrayOfString)
  {
    cmd("WholeStory");
    cmd("Cut");
    for (int i = 0; i < paramArrayOfString.length; ++i)
    {
      log.info("TABLE-NAME ------>" + paramArrayOfString[i]);
      Dispatch.put(this.selection, "Text", paramArrayOfString[i].toUpperCase() + "表");
      moveRight(1);
      cmd("TypeParagraph");
      cmd("PasteAndFormat", Integer.valueOf(0));
    }
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    MSWord localMSWord = new MSWord(new File("c:/dict2.doc"));
    localMSWord.open();
    localMSWord.makeTables(5);
  }
}