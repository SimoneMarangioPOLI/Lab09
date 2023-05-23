package it.polito.tdp.borders.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.db.Confini;


public class Model {
	
	 private Graph<Country, DefaultEdge> grafo ;
	 private List<Confini> confini; 
	 private List<Country> allCountry;
	 private Map<String, Country> lista;

	public Model() {
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		this.confini = new LinkedList<Confini>();
		this.allCountry = new LinkedList<Country>();
		this.lista = new TreeMap<String, Country>();
		
	}
		 
		 public void creaGrafo(int anno) {
			 this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class) ;
			 BordersDAO dao = new BordersDAO();
			 confini = dao.getCountrySpecifici(anno);
			 allCountry = dao.loadAllCountries();
			 
			 for(Country c : allCountry) {
				 for(Confini cc : confini) {
					 if(c.getCcode()==cc.getCod()) {
						 lista.put(c.getStateAbb(), c);
					 }
				 }
			 }
			 
			 Graphs.addAllVertices(this.grafo, this.lista.values()) ;
			
			 
			 
			 for(Country c : lista.values()) {
				 String s = c.getStateAbb();
				 List<String> listona = dao.trovaStati(s);
				 for(String ss : listona) {
					 if(c!=null && lista.get(ss)!=null) {
					 this.grafo.addEdge(c, lista.get(ss));
					 }
				 }
			 }
			 
		 }
		 
		 public Boolean isGraphCreated() {
				if (this.grafo.vertexSet().size()>0 )
					return true;
				else 
					return false;
			}
		 
		 public int numeroVertici() {
			 int num = grafo.vertexSet().size();
			  return num;
		 }
		 
		 public Integer calcolaConnesse() {
			 ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(this.grafo);
			 return inspector.connectedSets().size();
		 }
		
		
	}



