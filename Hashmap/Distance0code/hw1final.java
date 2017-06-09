import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.File;
import java.util.LinkedHashMap;

class hw1final
{
	public static void main(String args[])
	{
		
		try
		{
			File folder=new File("Datafiles");
			for(File fil1:folder.listFiles())
			{
				if(fil1.isDirectory())
				{
					
					for(File fil:fil1.listFiles())
					{
						long start = System.currentTimeMillis();
						Set<String> set = new HashSet<String>();
						InputStream fis = new FileInputStream(fil);
						InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
						BufferedReader br = new BufferedReader(isr);
						String fstr=fil.getName().substring(0,	fil.getName().indexOf('.'));
						PrintWriter writer = new PrintWriter("output/"+fstr+"1.txt", "UTF-8");
						LinkedHashMap<Integer, ArrayList> map = new LinkedHashMap<>();
						String line;
							while ((line = br.readLine()) != null) 
							{
								if(map.get(line.hashCode())!=null)
								{
									if(map.get(line.hashCode()).contains(line))
									{
										set.add(line);
									}
									else
									{
										ArrayList a1=new ArrayList(map.get(line.hashCode()));
										a1.add(line);
										map.put(line.hashCode(),new ArrayList(a1));
									}
								}
								else
								{
									ArrayList a1=new ArrayList();
									a1.add(line);
									map.put(line.hashCode(),new ArrayList(a1));
								}
							}
							//System.out.println("doneeeeeeeeeeeee");
							for(String s: set)
							{
								writer.println(s);
							}
							
							writer.close();
							//set.clear();
						map.clear();
						long end =System.currentTimeMillis();
						long total=end-start;
						System.out.println("total time for "+fil.getName()+" is "+total+" number of lines is"+set.size());
					}

					
				}
			
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
}