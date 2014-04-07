import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




public class Main {
	public static void main(String[] args) throws IOException {
		int temp=0;
		Map<String, String> map_genre = new HashMap<>();
		Map<String, String> map_nation = new HashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader("/home/cloudera/Desktop/movies.dat"));
		String line = null;
		while ((line = reader.readLine()) != null) {
		
			temp=0;
			List<String> test= new ArrayList<String>();
			String[] tokens=line.split("::",4 );
			String genre=tokens[3];
			String movie=tokens[2];
			String county=tokens[1];
			
			
		
		for(int i=0;i<genre.length();i++)
		{
			if(genre.charAt(i)=='|')
			{
				if(!test.contains(genre.substring(temp, i))) {
					
				    test.add(genre.substring(temp, i));
				}
				temp=i+1;
			}
		}
		if(!test.contains(genre.substring(temp, genre.length()))) {
	
		    test.add(genre.substring(temp, genre.length()));
		}
		Iterator<String> iterator = test.iterator();
		while (iterator.hasNext()) {
			String category=iterator.next();
			if(category.compareTo("")==0)
				category="Other";
			if(!map_genre.isEmpty() && !map_nation.isEmpty())
			{
				if(map_genre.containsKey(category)&&map_nation.containsKey(category))
				{
					String content=map_genre.get(category)+","+movie;
					String country=map_nation.get(category)+","+county;
					map_genre.put(category, content);
					map_nation.put(category, country);
				}
				else
				{
					map_genre.put(category, movie);
					map_nation.put(category, county);
				}
								
			}
			
			else
			{
				map_genre.put(category, movie);
				map_nation.put(category, county);
			}
				
				
		}
	    
			}
		BufferedWriter reader1 = new BufferedWriter(new FileWriter("/home/cloudera/Desktop/output.txt"));
		System.out.println(map_genre.size()+"\n");
		Iterator<Entry<String, String>> entries = map_genre.entrySet().iterator();
		Iterator<Entry<String, String>> entries1 = map_nation.entrySet().iterator();
		
		while (entries.hasNext()&&entries1.hasNext()) {
		
		    Map.Entry<String, String> entry = entries.next();

		    Map.Entry<String, String> entry1 = entries1.next();
		    String key=entry.getKey();
		    String desh=entry1.getValue();
		    String cinema=entry.getValue();
		    String [] key_list=key.split(",");
		    String [] desh_list=desh.split(",");
		    String [] cinema_list=cinema.split(",");
		    String entireline2="NULL";
		    String oldkey="";
		    String entireline1="NULL";
	/*	    for (int i=0; i<desh_list.length && i<cinema_list.length;i++)
		    {
		    	/*String entireline="";
		    	if (i ==0){
		    		entireline += "{\"key \":  \n {\"" + key_list[i] +  "\":  [  \n";
		    		oldkey=key_list[i];
		    	}
		    	else if(key_list[i]!=oldkey)
		    	{
		    		entireline += "]\n } \n{\"" + key_list[i] + " \":  \n [ \n";
		    	}
		    	entireline += "{\"country\": \"" + desh_list[i] + "\",\"movie\"" + cinema_list[i] + "}," + "\n";
		    	reader1.write(entireline);
		    	oldkey=key_list[i];
		    }
		    */
		    
		String entireline="[{\"id\":\"" + entry.getKey() + "\"," +"\"title\":\"" + entry1.getValue() + "," + entry.getValue() + "\"}," + "\n";
		    reader1.write(entireline);}
		
		reader1.close();reader.close();
	}
	
} 