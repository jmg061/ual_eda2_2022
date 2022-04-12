package practica02;

import java.awt.Container;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Greedy greedy = new Greedy();

		greedy.load("graphEDAland.txt");
		//System.out.println(greedy.getNet());

		greedy.ConexoBase();
		greedy.ConexoSinPQ();

		//new DibujarGrafo();
		greedy.NoConexo();

	}

}

class DibujarGrafo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Container contenedor;
	//FlowLayout layout;
	static int longitud;
	// Greedy greedy;

	public DibujarGrafo() {

		super("Grafos");

		//greedy = new Greedy();

		// greedy.load("graphEDAlandLargeVertices.txt");

		contenedor = getContentPane();
		//layout = new FlowLayout();
		//contenedor.setLayout(layout);

		setSize(1000, 1000);
		setVisible(true);

	}

	public void paint(Graphics g) {

		// g.drawOval(100,100,50,50);

		BufferedReader br = null;
		String line;
		String[] items = null;
		//int cont;
		

		try {

			br = new BufferedReader(new FileReader("graphEDAlandLargeVertices.txt"));

			while ((line = br.readLine()) != null) {

				/*cont = Integer.parseInt(line);

				for (int i = 0; i < cont; i++) {
					// line=br.readLine();
					// System.out.println(line +" cont: "+cont+" i: " + i);
					net.addVertex(br.readLine());

				}*/

				//cont = Integer.parseInt(br.readLine());

				//for (int i = 0; i < cont; i++) {

					line = br.readLine();
					items = line.split(" ");
					// for(String word : items)
					// System.out.println(word);
					g.drawString(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2]));

				//}
				

			}
			
			items = null;
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
