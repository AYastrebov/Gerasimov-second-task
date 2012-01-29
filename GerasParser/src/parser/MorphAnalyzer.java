package parser;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.WrongCharaterException;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

public class MorphAnalyzer 
{
	private LuceneMorphology luceneMorph = null;
	private volatile static MorphAnalyzer instance;

	/** Returns singletone class instance */
	public static MorphAnalyzer getInstance() {
		if (instance == null) {
			synchronized (MorphAnalyzer.class) {
				if (instance == null) {
					instance = new MorphAnalyzer();
				}
			}
		}
		return instance;
	}
		
	private MorphAnalyzer()
	{
		try 
		{
			luceneMorph = new RussianLuceneMorphology();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<String> getWordBaseForms(String word)
	{		
		
		String newWord = remove_ARABIC_PRESENTATION_FORMS(word);
		
		List<String> result = null;
		
		if (isStringCyrilic(newWord))
		{
			try 
			{
				result = luceneMorph.getMorphInfo(newWord);
			} 
			catch (WrongCharaterException e) 
			{
				result = new Vector<String>(1);
				result.add(word);
			}
			finally
			{
				String resultString = result.get(0).substring(0, result.get(0).indexOf('|'));
				result.add(0, resultString);
			}
		}
		else
		{
			result = new Vector<String>(1);
			result.add(word);
		}
		
		return result;
	}
	
	private String remove_ARABIC_PRESENTATION_FORMS(String string)
	{
		try 
		{
			Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B.equals(Character.UnicodeBlock.of(string.charAt(0)));
		} 
		catch (IndexOutOfBoundsException e) 
		{
			return string;
		}
		
		if ((Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_B.equals(Character.UnicodeBlock.of(string.charAt(0))) ||
				Character.UnicodeBlock.ARABIC_PRESENTATION_FORMS_A.equals(Character.UnicodeBlock.of(string.charAt(0)))) && 
				string.length() > 1 )
		{
			return string.substring(1);
		}
		return string;
	}
	
	private boolean isCyrillic(char c) 
	{
		return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
	}
	
	private boolean isStringCyrilic(String string)
	{
		for (int i = 0; i < string.length(); i++) 
		{
			if (!isCyrillic(string.charAt(i))) 
			{
				return false;
			}
		}
		
		return true;
	}
}
