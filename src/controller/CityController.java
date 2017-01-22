package controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;

import kit.XMLTest;
import model.City;

public class CityController extends Controller
{

	public void index()
	{
		render("index.jsp");
	}

	public void getData()
	{
		List<City> temp = XMLTest.makeFile();
		
		
		JSONObject data = new JSONObject();
		String dataString = JsonKit.toJson(temp);
		JSONArray dataJSONArray = new JSONArray(dataString);
		data.put("data", dataJSONArray);
		
		System.out.println(data);
		renderJson(data.toString());
	}
	
	public void getRaw()
	{
		StringBuffer xmlRaw = XMLTest.readFileByChars();
		renderText(xmlRaw.toString());
	}
	

}
