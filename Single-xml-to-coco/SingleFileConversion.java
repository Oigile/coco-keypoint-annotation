package xml2json;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SingleFileConversion {

	public static void main(String[] args) throws JSONException{		
		//get the data that is not contained in the xml files elsewhere 
		//write json and fill it in 
		//------------------------------------------CHAPTER 1: LOAD XML FILES---------------------------------------
		//load in all the xml files
		/*		File folder = new File("C://Desktop/collection/Annotations/users/Oigile/test1"); //xml files from labelme!
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }
		}*/

		//EXTRACT THE RIGHT DATA FROM THIS

		//------------------------------------CHAPTER 2: EXTRACT INFO FROM XML FILES--------------------------------	
		File file = new File("D://Desktop/collection/Annotations/users/Oigile/test1/p1.xml");
		DOMParser dom = new DOMParser();
		String filename = dom.getFileName(file);
		List<ArrayList<String>> key = dom.getKeypoints(file);
		List<Integer> person = dom.getPersonBounds(file);
		List<Integer> size = dom.getSize(file);

		//------------------------------------------CHAPTER 3: WRITE JSON FILE--------------------------------------		
		//object in which all data is stored
		JSONObject full = new JSONObject();

		//first object within the full object: info
		JSONObject info = new JSONObject();
		info.put("year", "2018"); 
		info.put("version", "v1"); 
		info.put("description", "COCO like dataset from LabelMe .xml files");
		info.put("contributor", "Oigile"); 
		info.put("url", "No website available"); 
		info.put("data_created", "18-7-2018");

		//second part of the full object: images array
		JSONArray images = new JSONArray();
		JSONObject image = new JSONObject();

		Random rand = new Random();
		int id = rand.nextInt(9000000) + 1000000;

		image.put("id", id); 
		image.put("width", size.get(0)); 
		image.put("height", size.get(1)); 
		image.put("file_name", filename);
		image.put("license", 0);
		image.put("flickr_url", "url");
		image.put("coco_url", "url");
		image.put("date_captured","18-7-2018");
		images.put(image);

		//third part of the full object: licenses array
		JSONArray licenses = new JSONArray();
		JSONObject license = new JSONObject();
		license.put("id", 0); 
		license.put("name", "No license"); 
		license.put("url", "my name is url"); 
		licenses.put(license);

		//fourth part of the full object: annotations, did I forget size?
		JSONArray annotations = new JSONArray();
		JSONObject annotation = new JSONObject();

		Random randann = new Random();
		int idann = randann.nextInt(900000) + 100000;
		
		//TODO
		int[] data = keypointList(key);
		//System.out.println("array xyv"+ Arrays.toString(data));
		
		int numbr = numKeypoints(key);

		annotation.put("id", idann);  
		annotation.put("image_id", id); 
		annotation.put("category_id", 0); 
		annotation.put("segmentation", "empty"); // array - not done here, because I don't need it
		annotation.put("area", 0); //!!!
		annotation.put("iscrowd", 0); // !!! assumed zero here
		annotation.put("keypoints",data);//
		annotation.put("num_keypoints", numbr); //!!! num objects -1, can also be retrieved from the xml file
		annotations.put(annotation);

		//bbox
		JSONArray bbox = new JSONArray();
		bbox.put(person.get(0)); //x
		bbox.put(person.get(1)); //y
		bbox.put(person.get(2)); //width
		bbox.put(person.get(3)); //height
		annotation.put("bbox", bbox);

		//keypoints
		/*		JSONArray keypoints = new JSONArray();
		int num_keypoints = 17;
		for(int i = 0;i<=num_keypoints;i++){
			keypoints.put(0); //x
			keypoints.put(0); //y
			keypoints.put(0); //v
		}*/

		JSONArray categories = new JSONArray();
		JSONObject category = new JSONObject();
		category.put("id", 0); 
		category.put("name", "person"); 
		category.put("supercategory", "person");

		//keypoints
		JSONArray keyarr2 = new JSONArray();
		keyarr2.put("nose");
		keyarr2.put("left_eye");
		keyarr2.put("right_eye");
		keyarr2.put("left_ear");
		keyarr2.put("right_ear");
		keyarr2.put("left_shoulder");
		keyarr2.put("right_shoulder");
		keyarr2.put("left_elbow");
		keyarr2.put("right_elbow");
		keyarr2.put("left_wrist");
		keyarr2.put("right_wrist");
		keyarr2.put("left_hip");
		keyarr2.put("right_hip");
		keyarr2.put("left_knee");
		keyarr2.put("right_knee");
		keyarr2.put("left_ankle");
		keyarr2.put("right_ankle");
		category.put("keypoints", keyarr2);

		//skeleton arrays
		JSONArray skel = new JSONArray();
		JSONArray skel1 = new JSONArray();
		skel1.put(14);
		skel1.put(16);
		JSONArray skel2 = new JSONArray();
		skel2.put(14);
		skel2.put(12);
		JSONArray skel3 = new JSONArray();
		skel3.put(17);
		skel3.put(15);
		JSONArray skel4 = new JSONArray();
		skel4.put(15);
		skel4.put(12);

		JSONArray skel5 = new JSONArray();
		skel5.put(12);
		skel5.put(13);

		JSONArray skel6 = new JSONArray();
		skel6.put(6);
		skel6.put(12);

		JSONArray skel7 = new JSONArray();
		skel7.put(7);
		skel7.put(13);

		JSONArray skel8 = new JSONArray();
		skel8.put(6);
		skel8.put(7);
		JSONArray skel9 = new JSONArray();
		skel9.put(6);
		skel9.put(8);
		JSONArray skel10 = new JSONArray();
		skel10.put(7);
		skel10.put(9);
		JSONArray skel11 = new JSONArray();
		skel11.put(8);
		skel11.put(10);
		JSONArray skel12 = new JSONArray();
		skel12.put(9);
		skel12.put(11);
		JSONArray skel13 = new JSONArray();
		skel13.put(2);
		skel13.put(3);
		JSONArray skel14 = new JSONArray();
		skel14.put(1);
		skel14.put(2);
		JSONArray skel15 = new JSONArray();
		skel15.put(1);
		skel15.put(3);
		JSONArray skel16 = new JSONArray();
		skel16.put(2);
		skel16.put(4);
		JSONArray skel17 = new JSONArray();
		skel17.put(3);
		skel17.put(5);
		JSONArray skel18 = new JSONArray();
		skel18.put(4);
		skel18.put(6);
		JSONArray skel19 = new JSONArray();
		skel19.put(5);
		skel19.put(7);

		skel.put(skel1);
		skel.put(skel2);
		skel.put(skel3);
		skel.put(skel4);
		skel.put(skel5);
		skel.put(skel6);
		skel.put(skel7);
		skel.put(skel8);
		skel.put(skel9);
		skel.put(skel10);
		skel.put(skel11);
		skel.put(skel12);
		skel.put(skel13);
		skel.put(skel14);
		skel.put(skel15);
		skel.put(skel16);
		skel.put(skel17);
		skel.put(skel18);
		skel.put(skel19);
		category.put("skeleton", skel);

		categories.put(category);

		full.put("info", info);
		full.put("images", images);
		full.put("annotations", annotations);
		full.put("licenses", licenses);
		full.put("categories", categories);

		//write the created json stuff into a file called data.json
		try(FileWriter file1 = new FileWriter("data.json")){
			file1.write(full.toString());

			file1.flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static int[] keypointList (List<ArrayList<String>> arr) {
		//17*3 arraylist
		int official[] = new int[51];
		
		for(int i=0;i<arr.size();i++){
			ArrayList<String> keyp = arr.get(i);

			String name = keyp.get(0);
			String occluded = keyp.get(1);
			String x = keyp.get(2);
			String y = keyp.get(3);
			
			//System.out.println(name);
			
			int v;
			if(occluded.equals("yes")){
				System.out.println(occluded);
				v = 1;
			} else if(occluded.equals("no")){
				v = 2;
			} else{
				v = 0;
			}

			int xint = Integer.parseInt(x);
			int yint = Integer.parseInt(y);

			//check how to store values on specific indices in a list!
			//set(index,value)
			if(name.equals("head")){
				official[0]= xint;
				official[1]=yint;
				official[2]=v;
			} else if(name.equals("left eye")){
				official[3]= xint;
				official[4]=yint;
				official[5]=v;
			} else if(name.equals("right eye")){
				official[6]= xint;
				official[7]=yint;
				official[8]=v;
			} else if(name.equals("left ear")){
				official[9]= xint;
				official[10]=yint;
				official[11]=v;
			} else if(name.equals("right ear")){
				official[12]= xint;
				official[13]=yint;
				official[14]=v;
			} else if(name.equals("left shoulder")){//!!!
				official[15]= xint;
				official[16]=yint;
				official[17]=v;
			} else if(name.equals("right shoulder")){//!!!
				official[18]= xint;
				official[19]=yint;
				official[20]=v;
			} else if(name.equals("left elbow")){
				official[21]= xint;
				official[22]=yint;
				official[23]=v;
			} else if(name.equals("right elbow")){
				official[24]= xint;
				official[25]=yint;
				official[26]=v;
			} else if(name.equals("left wrist")){
				official[27]= xint;
				official[28]=yint;
				official[29]=v;
			} else if(name.equals("right wrist")){
				official[30]= xint;
				official[31]=yint;
				official[32]=v;
			} else if(name.equals("left hip")){
				official[33]= xint;
				official[34]=yint;
				official[35]=v;
			} else if(name.equals("right hip")){
				official[36]= xint;
				official[37]=yint;
				official[38]=v;
			} else if(name.equals("left knee")){
				official[39]= xint;
				official[40]=yint;
				official[41]=v;
			} else if(name.equals("right knee")){
				official[42]= xint;
				official[43]=yint;
				official[44]=v;
			} else if(name.equals("left ankle")){
				official[45]= xint;
				official[46]=yint;
				official[47]=v;
			} else if(name.equals("right ankle")){
				official[48]= xint;
				official[49]=yint;
				official[50]=v;
			}
		} 
		return official;
	}

	public static int numKeypoints(List<ArrayList<String>> arr) {
		if(arr.isEmpty()){
			return 0;
		} else {
			return arr.size()-1; // only one person object for us for now
		}
	}
}