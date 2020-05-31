	import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;



public class ShowGraph {
	
	 public static void main(String[] args) {
		    DirectedSparseGraph<String, String> g = new DirectedSparseGraph<String, String>();
		    g.addVertex("R1");
		    g.addVertex("R2");
		    g.addEdge("Edge1", "R1", "R2");
		    g.addEdge("Edge2", "R2", "R1");
		   // g.addEdge("Edge3", "Vertex3", "Vertex1");
		   /* VisualizationImageServer<String,String> vs =
		    new VisualizationImageServer<String,String>(
		    new CircleLayout<String, String>(g), new Dimension(200, 200));*/
		   VisualizationViewer<String, String> vv= new VisualizationViewer<String, String>(new CircleLayout<String, String>(g));
		   vv.getRenderContext().setEdgeLabelTransformer((Transformer<String, String>) new Transformer<String, String>()  {
			    @Override
			    public String transform(String edgeName) {
			       String label;                
			       //map your label to your edgeName
			       return edgeName;
			    }
			});
		   vv.getRenderContext().setVertexLabelTransformer(i -> {
			      return "i";
			    });
		   // g.addVertex("Vertex3");
		   
		  
		    JFrame frame = new JFrame();
		    frame.getContentPane().add(vv);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.pack();
		    frame.setVisible(true);
		  }
		  public void Show(UndirectedSparseGraph<String, String> g)
		  {
			   VisualizationViewer<String, String> vv= new VisualizationViewer<String, String>(new CircleLayout<String, String>(g));
			   vv.getRenderContext().setEdgeLabelTransformer((Transformer<String, String>) new Transformer<String, String>()  {
				    @Override
				    public String transform(String edgeName) {
			
				       return edgeName;
				    }
				});
			   vv.getRenderContext().setVertexLabelTransformer(i -> {
				      return i;
				    });
				 
				    JFrame frame = new JFrame();
				    frame.getContentPane().add(vv);
				   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.pack();
				    frame.setVisible(true);
		  }
		  
}
