package parser;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JTable;


public class TableManager 
{
	private MyTableModel tableModel = null;
	private volatile static TableManager instance;

	/** Returns singletone class instance */
	public static TableManager getInstance() {
		if (instance == null) {
			synchronized (TableManager.class) {
				if (instance == null) {
					instance = new TableManager();
				}
			}
		}
		return instance;
	}
	
	private JTable table;
	
	private TableManager()
	{
		
	}
	
	public void setTableData(Vector<String> data)
	{
		Vector<TableItem> tableData = new Vector<TableItem>();
		
		for (String stringItem : data) 
		{
			TableItem tItem = new TableItem(stringItem, getDuplicates(data, stringItem));
			tableData.add(tItem);
		}
		tableData = getRidOfDuplicateElements(tableData);
		this.tableModel = new MyTableModel(tableData);
		this.table.setModel(tableModel);
	}
	
	private Vector<TableItem> getRidOfDuplicateElements(Vector<TableItem> tableData) 
	{
		Vector<TableItem> noDuplicates = new Vector<TableItem>();

		SortedSet<TableItem> mySet = new TreeSet<TableItem>(new TableItemCompare());
		mySet.addAll(tableData);
		
		noDuplicates.addAll(mySet);
		
		return noDuplicates;
	}

	private int getDuplicates(Vector<String> data, String key) 
	{		
		return Collections.frequency(data, key);
	}

	public boolean hasTable() 
	{
		return (this.table != null);
	}
	
	public void setTable(JTable table)
	{
		this.table = table;
	}
	
}
