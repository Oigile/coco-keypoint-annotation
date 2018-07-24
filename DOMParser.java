package xml2json;

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
		dom.getKeypoints("D://Desktop/collection/Annotations/users/Oigile/test1/p1.xml");
		dom.getPersonBounds("D://Desktop/collection/Annotations/users/Oigile/test1/p1.xml");
		dom.getSize("D://Desktop/collection/Annotations/users/Oigile/test1/p1.xml");
	}
	
	public List<String> getPersonBounds(String file){
		List<String> ls3 = new ArrayList<String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			//for all the objects in the xml file
			NodeList xlist = doc.getElementsByTagName("object");
			
			//for person
			for(int j=xlist.getLength()-1;j<xlist.getLength();j++){
				ArrayList<String> list = new ArrayList<String>();
				Node p = xlist.item(j);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element obj = (Element) p;
					NodeList ylist = obj.getChildNodes();

					String name = ylist.item(0).getTextContent();
					list.add(name);

					Node pol = ylist.item(9);
					NodeList zlist = pol.getChildNodes();

					Node ptt = zlist.item(1);
					NodeList qlist = ptt.getChildNodes();
					String x = qlist.item(0).getTextContent();
					String y = qlist.item(1).getTextContent();
					list.add(x);
					list.add(y);

					Node ptt2 = zlist.item(3);
					NodeList qlist2 = ptt2.getChildNodes();
					String x1 = qlist2.item(0).getTextContent();
					String x2 = qlist2.item(1).getTextContent();
					list.add(x1);
					list.add(x2);

					//System.out.println("name:"+ name + ", x:" + x + ", y:" + y + ", rbx:" + x1 +", rby:" + x2 );
					
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
	
	public List<String> getSize(String file){
		List<String> ls2 = new ArrayList<String>();

		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			//get the height and width of the picture------------------------------------------------------------------------------
			NodeList h = doc.getElementsByTagName("nrows");
			String height = h.item(0).getTextContent();
			NodeList w = doc.getElementsByTagName("ncols");
			String width = w.item(0).getTextContent();
			ls2.add(height);
			ls2.add(width);

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
	
	public List<ArrayList<String>> getKeypoints(String file){
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
