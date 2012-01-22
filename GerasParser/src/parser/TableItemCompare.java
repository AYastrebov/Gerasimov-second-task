package parser;
import java.util.Comparator;


public class TableItemCompare implements Comparator<TableItem>
{

	@Override
	public int compare(TableItem item0, TableItem item1) 
	{
		if (item0.getName().equals(item1.getName())) 
		{
			return 0;
		}
		
		if (item0.getDuplicates() < item1.getDuplicates()) 
		{
			return 1;
		}
		
		return -1;
	}

}
