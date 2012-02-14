package parser;

public class TableItem  implements Comparable<TableItem>
{
	private String name;
	private int duplicates;
	
	public TableItem(String name, int duplicates) 
	{
		this.name = name;
		this.duplicates = duplicates;
	}
	
	@Override
	public boolean equals(Object arg0) 
	{
		TableItem compareObject = (TableItem) arg0;
		return (this.getName().equals(compareObject.getName()) && this.getDuplicates() == compareObject.getDuplicates());
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getDuplicates() 
	{
		return duplicates;
	}
	
	public void setDuplicates(int duplicates) 
	{
		this.duplicates = duplicates;
	}

	@Override
	public int compareTo(TableItem o) 
	{
		// TODO Auto-generated method stub
		
		
		
		if (getName().equals(o.getName())) 
		{
			return 0;
		}
		
		if (this.getDuplicates() < o.getDuplicates()) 
		{
			return 1;
		}
		
		return -1;
	}
}
