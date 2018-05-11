package Simulation;

import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import Event_grid.Death;
import Event_grid.Move;
import Event_grid.Reproduction;
import Graph.Edge;
import Graph.Coord;
import Graph_grid.*;
import Individual.ComfortComparator;
import Individual.Individual;
import PEC.PEC;

public class Simulation {
	
	private int finalinst, initpop, maxpop, comfortsens, colsnb, rowsnb, xinitial, yinitial, xfinal, yfinal, n_obs, death_param, repro_param, move_param;
	private int cmax, edge_cost;
	private int n_edge_max = 0;
	
	private Coord [] obstacles;
	private Coord xy1_aux;
	private Coord xy2_aux;
	private Coord  xy_i;
	private Coord  xy_f;

	private Edge edge;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	
	private PEC<Individual> pec = new PEC<Individual>();
	private Graph_grid grid;
	private Observation obs = new Observation();
	
	static int t=1;
	int n_events=0;
	
	public Simulation (File inputFile) {
		
		try {
		
			SAXParserFactory fact = SAXParserFactory.newInstance();
			fact.setValidating(true);
			SAXParser saxParser = fact.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {

			int n=0;
			boolean special_zones = false;
			boolean bzone = false;
			boolean bobstacle = false;
			boolean bevent = false;

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

				if (qName.equalsIgnoreCase("simulation")) {
					finalinst = Integer.parseInt(attributes.getValue("finalinst"));
					initpop = Integer.parseInt(attributes.getValue("initpop"));
					maxpop = Integer.parseInt(attributes.getValue("maxpop"));
					comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
				}

				if (qName.equalsIgnoreCase("grid")) {
					colsnb = Integer.parseInt(attributes.getValue("colsnb"));
					rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
				}

				if (qName.equalsIgnoreCase("initialpoint")) {
					xy_i = new Coord (Integer.parseInt(attributes.getValue("xinitial")),Integer.parseInt(attributes.getValue("yinitial")));
				}

				if (qName.equalsIgnoreCase("finalpoint")) {
					xy_f = new Coord (Integer.parseInt(attributes.getValue("xfinal")), Integer.parseInt(attributes.getValue("yfinal")));
				}
				if (qName.equalsIgnoreCase("specialcostzones")) {
					special_zones = true;
				}
				if (qName.equalsIgnoreCase("zone") && special_zones) {
					bzone = true;
					
					xinitial = Integer.parseInt(attributes.getValue("xinitial"));
					yinitial = Integer.parseInt(attributes.getValue("yinitial"));
					xfinal = Integer.parseInt(attributes.getValue("xfinal"));
					yfinal = Integer.parseInt(attributes.getValue("yfinal"));
					
					n_edge_max = n_edge_max + ((xfinal-xinitial)+(yfinal-yinitial))*2;				
					
				}
				if (qName.equalsIgnoreCase("obstacles")) {
					
					n_obs = Integer.parseInt(attributes.getValue("num"));
					obstacles = new Coord [n_obs];
					bobstacle = true;
					
				}
				if (qName.equalsIgnoreCase("obstacle") && bobstacle) {
					obstacles[n] = new Coord (Integer.parseInt(attributes.getValue("xpos")), Integer.parseInt(attributes.getValue("ypos")));
					n++;
				}
				if (qName.equalsIgnoreCase("specialcostzones")) {
					bevent = true;
				}
				if (qName.equalsIgnoreCase("death") && bevent) {
					death_param = Integer.parseInt(attributes.getValue("param"));
					
				}
				if (qName.equalsIgnoreCase("reproduction") && bevent) {
					repro_param = Integer.parseInt(attributes.getValue("param"));
					
				}
				if (qName.equalsIgnoreCase("move") && bevent) {
					move_param = Integer.parseInt(attributes.getValue("param"));
					
				}

			}
			
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				
				if (qName.equalsIgnoreCase("specialcostzones")) {
					special_zones = false;
				}
				if (qName.equalsIgnoreCase("obstacles")) {
					bobstacle = false;
					n=0;
				}
				if (qName.equalsIgnoreCase("events")) {
					bevent = false;
				}

			}
			
			@Override
			public void characters(char ch[], int start, int length) throws SAXException {			
				if (bzone) {
					
					edge_cost = Integer.parseInt(new String(ch, start, length));
					
					if (cmax < edge_cost) {
						cmax = edge_cost;
					}
					for (int z = xinitial; z < xfinal; z++) {		
						
						xy1_aux = new Coord(z,yinitial);
						xy2_aux = new Coord(z+1, yinitial);		
						
						edge = new Edge(xy1_aux, xy2_aux, edge_cost);
						edges.add(edge);
						
						xy1_aux = new Coord(z,yfinal);
						xy2_aux = new Coord(z+1, yfinal);	
						
						edge = new Edge(xy1_aux, xy2_aux, edge_cost);
						edges.add(edge);
						
					}
					
					for (int z = yinitial; z < yfinal; z++) {
						
						xy1_aux = new Coord(xinitial,z);
						xy2_aux = new Coord(xinitial, z+1);		
						
						edge = new Edge(xy1_aux, xy2_aux, edge_cost);
						edges.add(edge);
						
						xy1_aux = new Coord(xfinal, z);
						xy2_aux = new Coord(xfinal, z+1);	
						
						edge = new Edge(xy1_aux, xy2_aux, edge_cost);
						edges.add(edge);
						
					}
					
					bzone = false;
				}
				
			}
			
			@Override
			public void error(SAXParseException e) throws SAXException {
			        System.out.println("Error: " + e.getMessage());
			}
			
			@Override
			public void fatalError(SAXParseException e) throws SAXException {
		        System.out.println("Fatal error: " + e.getMessage());
		    }
			
			
		};
		
		saxParser.parse(inputFile, handler);
		 
		}

		catch (IOException ioe) {
			System.err.println("IO error");
			ioe.printStackTrace();
		}
		
		catch (SAXException se) {
			System.err.println("Parser error");
			se.printStackTrace();
		}
		
		catch (ParserConfigurationException pce) {
			System.err.println("Parser configuration error");
			pce.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Individual.setXy_f(xy_f);
		Individual.setCmax(cmax);
		Individual.setK(comfortsens);
		Individual.setN(colsnb);
		Individual.setM(rowsnb);
		
		Death.setMew(death_param);
		Move.setDelta(move_param);
		Reproduction.setRo(repro_param);
		
		grid = new Graph_grid (rowsnb, colsnb, cmax, n_obs, obstacles, edges);
		
		
		for(int n = 0; n<initpop; n++) {		
			Individual i = new Individual(xy_i, grid.getNodes(), pec, t);
			Individual.getPopulation().add(i);
		}
		return;
		
	}

	public void simulate() {
		while(!pec.getPec().isEmpty() && t<finalinst) {
			while(t == pec.getT()) {
				if(!((Individual) pec.getPec().peek().getZ()).isAlive())
					pec.removeEvPEC();
				else {
					pec.nextEvPEC().processEvent(Individual.getPopulation(), this);							
					if(Individual.getTot_pop()>=maxpop) {
						Comparator<Individual> c = new ComfortComparator();
						PriorityQueue<Individual> new_pop = new PriorityQueue<Individual>(5, c);					
						int count = 0;
						boolean alive;
						while(count<5) {
							if(Individual.getPopulation().peek().isAlive()) {
								Individual i = Individual.getPopulation().poll();
								Individual survivor = new Individual(i.getCost(), i.getLength(), i.isAlive(), i.getComfort(), i.getPath(), i.getNode());
								new_pop.add(survivor);
								count++;
							}else
								Individual.getPopulation().remove();
						}
						for(Individual i : Individual.getPopulation()) {							
							if(i.isAlive()) {
								alive = new Random().nextDouble() < i.getComfort();
								if (alive) {
									Individual survivor = new Individual(i.getCost(), i.getLength(), i.isAlive(), i.getComfort(), i.getPath(), i.getNode());
									new_pop.add(survivor);
									count++;
								}
							}
					
						}
						Individual.setTot_pop(count);
						Individual.setPopulation(new_pop);
					}		
					
				}
			}
			t++;
				
			if(t % (finalinst/20) == 0) {
				Observation.update_observation();	
				System.out.println(obs.toString());				
			}
			
		}
		
	}
	
	public Coord getXy_i() {
		return xy_i;
	}

	public Coord getXy_f() {
		return xy_f;
	}

	public PEC<Individual> getPec() {
		return pec;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Graph_grid getGrid() {
		return grid;
	}
	
}
