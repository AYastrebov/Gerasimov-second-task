package parser;

public class SymbolsPreview extends PreviewFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7151423441205895191L;

	public SymbolsPreview() 
	{
		super("Исключения", getSpecialSymbolsPreview());
	}

	private static String getSpecialSymbolsPreview() 
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Предлоги:");
		builder.append(System.getProperty("line.separator"));
		
		for (String pred : DataManager.getPredlogi()) 
		{
			builder.append(pred + " ");
		}
		
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		
		builder.append("Частицы:");
		builder.append(System.getProperty("line.separator"));
		
		for (String part : DataManager.getParticles()) 
		{
			builder.append(part + " ");
		}
		
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		
		builder.append("Символы:");
		builder.append(System.getProperty("line.separator"));
		
		for (String spec : DataManager.getSpecial()) 
		{
			builder.append(spec + " ");
		}
		
		return builder.toString();
	}
}
