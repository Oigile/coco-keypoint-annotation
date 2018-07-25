package xml2json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {

	public static void main(String args[]){
		DOMParser dom = new DOMParser();
	}
	
	public String getFileName(File file){
		//List<Integer> ls2 = new ArrayList<Integer>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String filename = "error if this message shows";
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			NodeList h = doc.getElementsByTagName("filename");
			String filename1 = h.item(0).getTextContent();
			filename = filename1;
		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return filename;
	}
	
	public List<Integer> getPersonBounds(File file){
		List<Integer> ls3 = new ArrayList<Integer>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			//for all the objects in the xml file
			NodeList xlist = doc.getElementsByTagName("object");
			
			//for person
			for(int j=xlist.getLength()-1;j<xlist.getLength();j++){
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				Node p = xlist.item(j);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element obj = (Element) p;
					NodeList ylist = obj.getChildNodes();

					//String name = ylist.item(0).getTextContent();
					//list.add(name);

					Node pol = ylist.item(9);
					NodeList zlist = pol.getChildNodes();

					Node ptt = zlist.item(1);
					NodeList qlist = ptt.getChildNodes();
					String x = qlist.item(0).getTextContent();
					String y = qlist.item(1).getTextContent();
					
					int xint = Integer.parseInt(x);
					int yint = Integer.parseInt(y);
					
					list.add(xint);
					list.add(yint);

					Node ptt2 = zlist.item(3);
					NodeList qlist2 = ptt2.getChildNodes();
					String x1 = qlist2.item(0).getTextContent();
					String x2 = qlist2.item(1).getTextContent();
					
					int x1int = Integer.parseInt(x1);
					int x2int = Integer.parseInt(x2);
					
					// the person bounding box start from x and y as upper left corner and is given a width and height from there
					// these values can be seen as difx and dif y 
					int difx = x1int - xint;
					int dify = x2int - yint;
					
					list.add(difx);
					list.add(dify);
					
					ls3 = list;
				}
			}

		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println(ls3);
		
		return ls3;
	}
	
	public List<Integer> getSize(File file){
		List<Integer> ls2 = new ArrayList<Integer>();

		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			NodeList h = doc.getElementsByTagName("nrows");
			String height = h.item(0).getTextContent();
			NodeList w = doc.getElementsByTagName("ncols");
			String width = w.item(0).getTextContent();
			
			int hint = Integer.parseInt(height);
			int wint = Integer.parseInt(width);
			
			ls2.add(hint);
			ls2.add(wint);

		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println(ls2);
		
		return ls2;
	}
	
	public List<ArrayList<String>> getKeypoints(File file){
		List<ArrayList<String>> ls = new ArrayList<ArrayList<String>>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			//for all the objects in the xml file
			NodeList xlist = doc.getElementsByTagName("object");
			for(int i=0; i<xlist.getLength()-1;i++){
				ArrayList<String> list = new ArrayList<String>();
				
				Node p = xlist.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element obj = (Element) p;
					NodeList ylist = obj.getChildNodes();

					String name = ylist.item(0).getTextContent();
					String occluded = ylist.item(3).getTextContent();
					list.add(name);
					list.add(occluded);
					//polygon
					Node pol = ylist.item(8);
					NodeList zlist = pol.getChildNodes();

					//pt
					Node ptt = zlist.item(1);
					NodeList qlist = ptt.getChildNodes();

					String x = qlist.item(0).getTextContent();
					String y = qlist.item(1).getTextContent();
					list.add(x);
					list.add(y);

					//System.out.println(list);
					
					ls.add(list);
					//System.out.print(ls);
				}
			}

		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println(ls);
		
		return ls;
	}
}
