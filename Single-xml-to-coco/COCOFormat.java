package xml2json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class COCOFormat {

	public static void main(String[] args) throws JSONException {
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
		image.put("id", 0); 
		image.put("width", 0); 
		image.put("height", 0); 
		image.put("file_name", "name");
		image.put("license", 0);
		image.put("flickr_url", "url");
		image.put("coco_url", "url");
		image.put("date_captured","18-7-2018");
		images.put(image);

		//third part of the full object: licenses array
		JSONArray licenses = new JSONArray();
		JSONObject license = new JSONObject();
		license.put("id", 0); //???
		license.put("name", "say my name"); 
		license.put("url", "my name is url"); 
		licenses.put(license);

		//fourth part of the full object: annotations, did I forget size?
		JSONArray annotations = new JSONArray();
		JSONObject annotation = new JSONObject();
		annotation.put("id", 0); 
		annotation.put("image_id", 0); 
		annotation.put("category_id", 0);
		annotation.put("segmentation", "empty"); // array - not done here, because I don't need it
		annotation.put("area", 0);
		annotation.put("iscrowd", 0); // boolean!
		annotation.put("keypoints",0);//array!!!
		annotation.put("num_keypoints", 0);
		annotations.put(annotation);

		//bbox
		JSONArray bbox = new JSONArray();
		bbox.put(0); //x
		bbox.put(0); //y
		bbox.put(0); //width
		bbox.put(0); //height
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
		category.put("id", 1); 
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
		System.out.println(full);

	}

}
