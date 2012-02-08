package parser;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JTable;


public class TableManager 
{
	private MyTableModel tableModel = null;
	private JTable table = null;
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
		
	private TableManager()
	{
		this.table = new JTable();
	}
	
	//Считаем количество дубликатов
	private int getDuplicates(Vector<String> data, String key) 
	{		
		return Collections.frequency(data, key);
	}
	
	//Избавляемся от дубликатов
	private Vector<TableItem> getRidOfDuplicateElements(Vector<TableItem> tableData) 
	{
		Vector<TableItem> noDuplicates = new Vector<TableItem>();

		SortedSet<TableItem> mySet = new TreeSet<TableItem>(new TableItemComparator());
		mySet.addAll(tableData);
		
		noDuplicates.addAll(mySet);
		
		for (TableItem tableItem : noDuplicates) 
		{
			System.out.println("Word: " + tableItem.getName() + " at position: " + noDuplicates.indexOf(tableItem));
		}
		
		return noDuplicates;
	}
	
	//Задаем данные для таблицы
	public void setTableData(Vector<String> data)
	{
		Vector<TableItem> tableData = new Vector<TableItem>();
		
		for (String stringItem : data) 
		{
			//System.out.println("Word: " + stringItem + " at position: " + data.indexOf(stringItem));
			TableItem tItem = new TableItem(stringItem, getDuplicates(data, stringItem));
			tableData.add(tItem);
		}
		tableData = getRidOfDuplicateElements(tableData);
		this.tableModel = new MyTableModel(tableData);
		this.table.setModel(tableModel);
	}
			
	public JTable getTable()
	{
		return table;
	}
}
