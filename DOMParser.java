package xml2json;

import java.io.IOException;

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
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("D://Desktop/collection/Annotations/users/Oigile/test1/p1.xml");

			//get the height and width of the picture------------------------------------------------------------------------------
			NodeList h = doc.getElementsByTagName("nrows");
			String height = h.item(0).getTextContent();
			NodeList w = doc.getElementsByTagName("ncols");
			String width = w.item(0).getTextContent();

			System.out.println("height: " + height + ", width:" + width);

			//for all the objects in the xml file----------------------------------------------------------------------------------
			NodeList xlist = doc.getElementsByTagName("object");
			for(int i=0; i<xlist.getLength()-1;i++){
				Node p = xlist.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element obj = (Element) p;
					NodeList ylist = obj.getChildNodes();

					String name = ylist.item(0).getTextContent();
					String occluded = ylist.item(3).getTextContent();

					//polygon
					Node pol = ylist.item(8);
					NodeList zlist = pol.getChildNodes();
					//pt
					Node ptt = zlist.item(1);
					NodeList qlist = ptt.getChildNodes();

					String x = qlist.item(0).getTextContent();
					String y = qlist.item(1).getTextContent();

					System.out.println("name:"+ name + ", occluded:"+ occluded + ", x:" + x + ", y:" + y);
				}
			}
			//for person
			for(int j=xlist.getLength()-1;j<xlist.getLength();j++){
				Node p = xlist.item(j);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element obj = (Element) p;
					NodeList ylist = obj.getChildNodes();

					String name = ylist.item(0).getTextContent();
		
					Node pol = ylist.item(9);
					NodeList zlist = pol.getChildNodes();

					Node ptt = zlist.item(1);
					NodeList qlist = ptt.getChildNodes();
					String x = qlist.item(0).getTextContent();
					String y = qlist.item(1).getTextContent();
					
					Node ptt2 = zlist.item(3);
					NodeList qlist2 = ptt2.getChildNodes();
					String x1 = qlist2.item(0).getTextContent();
					String x2 = qlist2.item(1).getTextContent();
					
					System.out.println("name:"+ name + ", x:" + x + ", y:" + y + ", rbx:" + x1 +", rby:" + x2 );

				}
			}

	} catch (ParserConfigurationException e){
		e.printStackTrace();
	} catch (SAXException e){
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}
}
}
