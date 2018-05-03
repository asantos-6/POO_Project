package xml_parser;


import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;




public class UserHandler  {
	
	public static void main(String [] argv) throws Exception {
		
		File inputFile = new File(""+argv[0]+"");
		
		try {
		
		SAXParserFactory fact = SAXParserFactory.newInstance();
		SAXParser saxParser = fact.newSAXParser();
		
		
		
		DefaultHandler handler = new DefaultHandler() {

			int i=0, j=0, n=0 ;
			boolean special_zones = false;
			boolean bzone = false;
			boolean bobstacle = false;
			boolean bevent = false;
			int finalinst, initpop, maxpop, comfortsens, colsnb, rowsnb, xinitial, yinitial, xfinal, yfinal, obsnum, death_param, repro_param, move_param ;
			int [] [] zones, obstacle;


			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

				System.out.println("Start Element :" + qName);

				if (qName.equalsIgnoreCase("simulation")) {
					finalinst = Integer.parseInt(attributes.getValue("finalinst"));
					initpop = Integer.parseInt(attributes.getValue("initpop"));
					maxpop = Integer.parseInt(attributes.getValue("maxpop"));
					comfortsens = Integer.parseInt(attributes.getValue("comfortsens"));
				}

				if (qName.equalsIgnoreCase("grid")) {
					colsnb = Integer.parseInt(attributes.getValue("colsnb"));
					rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
					zones = new int [5] [((colsnb-1)*(rowsnb)) + ((colsnb)*(rowsnb-1))];
				}

				if (qName.equalsIgnoreCase("initialpoint")) {
					xinitial = Integer.parseInt(attributes.getValue("xinitial"));
					yinitial = Integer.parseInt(attributes.getValue("yinitial"));
				}

				if (qName.equalsIgnoreCase("finalpoint")) {
					xfinal = Integer.parseInt(attributes.getValue("xfinal"));
					yfinal = Integer.parseInt(attributes.getValue("yfinal"));
				}
				if (qName.equalsIgnoreCase("specialcostzones")) {
					special_zones = true;
				}
				if (qName.equalsIgnoreCase("zone") && special_zones) {
					bzone = true;
					zones [0] [j] = Integer.parseInt(attributes.getValue("xinitial"));
					zones [1] [j] = Integer.parseInt(attributes.getValue("yinitial"));
					zones [2] [j] = Integer.parseInt(attributes.getValue("xfinal"));
					zones [3] [j] = Integer.parseInt(attributes.getValue("yfinal"));
				}
				if (qName.equalsIgnoreCase("obstacles")) {
					obsnum = Integer.parseInt(attributes.getValue("num"));
					obstacle = new int [2] [obsnum];
					bobstacle = true;
					
				}
				if (qName.equalsIgnoreCase("obstacles") && bobstacle) {
					obstacle [0] [n] = Integer.parseInt(attributes.getValue("xpos"));
					obstacle [1] [n] = Integer.parseInt(attributes.getValue("ypos"));
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

			public void endElement(String uri, String localName, String qName) throws SAXException {

				System.out.println("End Element :" + qName);
				
				if (qName.equalsIgnoreCase("specialcostzones")) {
					special_zones = false;
				}
				if (qName.equalsIgnoreCase("obstacles")) {
					bobstacle = false;
				}
				if (qName.equalsIgnoreCase("events")) {
					bevent = false;
				}

			}

			public void characters(char ch[], int start, int length) throws SAXException {
				
				if (bzone) {
					zones [5] [j] = Integer.parseInt(new String(ch, start, length));
					j++;
					bzone = false;
				}
				
			}
		};
		
		
			
		
		
		
		saxParser.parse(inputFile, handler);
		 
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
