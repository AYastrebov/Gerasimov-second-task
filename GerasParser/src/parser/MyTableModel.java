package parser;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8551310900044082864L;
	private Vector<TableItem> tableItems;
	private final String[] columnNames = {"#", "Строка", "Совпадения", "Вероятность"};
	private final DecimalFormat df = new DecimalFormat("#.####");
	
	//Считаем вероятность
	private String getProbability(TableItem item) 
	{
		double probability = 0;
		int duplicatesSum = 0;
		for (TableItem tItem : tableItems) 
		{
			duplicatesSum = duplicatesSum + tItem.getDuplicates();
		}
		
		probability = (double) item.getDuplicates() / duplicatesSum;
		
		return df.format(probability);
	}
	
	public MyTableModel(Vector<TableItem> tableItems) 
	{
		this.setTableItems(tableItems);
	}

	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return columnNames[column];
	}

	@Override
	public int getRowCount() 
	{
		return tableItems.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		TableItem item = tableItems.get(row);
		
		switch (column) 
		{
		case 0:
			return row;
		case 1:
			return item.getName();			
		case 2:
			return item.getDuplicates();
		case 3:
			return getProbability(item);
		default:
			break;
		}
		return "error!";
		
	}	

	public Vector<TableItem> getTableItems() 
	{
		return tableItems;
	}

	public void setTableItems(Vector<TableItem> tableItems) 
	{
		this.tableItems = tableItems;
	}

}
