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
import Individual.Individual;
import PEC.PEC;

public class Simulation {
	
	protected int finalinst, initpop, maxpop, comfortsens, colsnb, rowsnb, xinitial, yinitial, xfinal, yfinal, n_obs, death_param, repro_param, move_param;
	protected int cmax, edge_cost;
	protected int n_edge_max = 0;
	
	protected Coord [] obstacles;
	protected Coord xy1_aux;
	protected Coord xy2_aux;
	protected Coord  xy_i;
	protected Coord  xy_f;

	protected Edge edge;
	protected ArrayList<Edge> edges = new ArrayList<Edge>();
	
	protected PEC pec = new PEC();
	protected Graph_grid grid;
	protected Observation obs = new Observation();
	
	protected static int t=0;
	protected int n_events=0;
	
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

				//System.out.println("Start Element :" + qName);

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

				//System.out.println("End Element :" + qName);
				
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
			
			
		};//end DefaultHandler
		
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
		
		/*
		System.out.println("Instante Final: " + finalinst);
		System.out.println("População Inicial: " + initpop);
		System.out.println("Max. Pop. : "+ maxpop);
		System.out.println("Comfort Sensibility" + comfortsens);
		System.out.println("M: " + colsnb);
		System.out.println("N: "+ rowsnb );
		System.out.println("X inicial : " + xy_i.x + " Y inicial : "+ xy_i.y);
		System.out.println("X final : " + xy_f.x + " Y final : "+ xy_f.y);
		System.out.println("Zona especial : \n Xi :" + xinitial + "\n Yi : " + yinitial + "\n Xf :" + xfinal + "\n Yf : " + yfinal );
		for (int i=0; i<n_obs; i++) {
			System.out.println("\n Obstacle " + (i+1) + ": X - "+ obstacles[i].x +" Y - " + obstacles[i].y );
		}
		System.out.println("Death Param: " + death_param );
		System.out.println("Repro Param: " + repro_param);
		System.out.println("Move Param: " + move_param);
		System.out.println("Edge Cost " + edge_cost);
		System.out.println("N_Edge Max " + n_edge_max);
		System.out.println("\n Edge List : ");
		int i= 1;
		for(Edge e: edges) {
		System.out.println("\n edge " + i +  " : " + e);
		i++;
		}
		*/	
	}

	public void simulate() {
		while(!pec.getPec().isEmpty() || t<finalinst) {
			
			pec.nextEvPEC().processEvent(Individual.getPopulation(), this);
			
			if(Individual.getTot_pop()==maxpop) {
				int n = 0;

				for(Individual i : Individual.getPopulation()) {
					if(n>5) {
						 boolean alive = new Random().nextDouble() < i.getComfort();
						i.setAlive(alive);
					}else if(i.isAlive())
							n++;
				}
			}
			
			if(t % (finalinst/20) == 0) {
				Observation.update_observation();
				
				System.out.println(obs.toString());				
			}
			t++;
		}
		
	}
	
	public Coord getXy_i() {
		return xy_i;
	}

	public Coord getXy_f() {
		return xy_f;
	}

	public PEC getPec() {
		return pec;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Graph_grid getGrid() {
		return grid;
	}
	

	
}
