package practica02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Greedy {
	
	private Network<String> net;
	
	public Greedy() {
		
		net = new Network<>();
		
	}
	
	public void load(String file) {
		
		BufferedReader br = null;
		String line;
		String[] items = null;
		int cont;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			
			cont = Integer.parseInt(br.readLine());
			
			
			if(cont==0) 
				net.setDirected(false);
			
				while((line = br.readLine()) != null) {
					
					cont = Integer.parseInt(line);
					
					for(int i = 0; i<cont; i++) {
						//line=br.readLine();
						//System.out.println(line +" cont: "+cont+" i: " + i);
						net.addVertex(br.readLine());
						
					}
					
					cont = Integer.parseInt(br.readLine());
					
					for(int i = 0; i<cont; i++) {
						
						line = br.readLine();
						items = line.split(" ");
						//for(String word : items)
							//System.out.println(word);
						net.addEdge(items[0], items[1], Integer.parseInt(items[2]));
						
					}
					items = null;
					
				}
		
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Network<String> getNet() {
		return net;
	}
	
	

}
