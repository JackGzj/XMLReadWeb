package kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import model.City;

public class XMLTest
{
	private static List<City> cities = new ArrayList<City>();
	private static final String PATH = "../test.xml";

	private static Document document = new Document();

	public static void createXml(File file)
	{

		// 1.创建元素 及 设置为根元素
		Element employees = new Element("cities");
		document.setContent(employees);

		// 2.创建注释 及 设置到根元素上
		Comment commet = new Comment("thisis my comment");
		employees.addContent(commet);

		List<City> cities = City.dao.find("SELECT * FROM city WHERE ID < 600");
		for (City city : cities)
		{
			// 3.创建元素
			Element element = new Element("city");

			// 3.3 设置元素名及文本
			Element ele = new Element("ID");
			Integer id = city.getInt("ID");
			ele.setText(id + "");
			// 设置到上层元素上
			element.addContent(ele);

			// 设置元素
			ele = new Element("Name");
			ele.setText(city.getStr("Name"));
			element.addContent(ele);
			ele = new Element("CountryCode");
			ele.setText(city.getStr("CountryCode"));
			element.addContent(ele);
			ele = new Element("District");
			ele.setText("District");
			element.addContent(ele);
			Integer population = city.getInt("Population");
			ele = new Element("Population");
			ele.setText(population + "");
			element.addContent(ele);

			// 设置为根元素的子元素
			employees.addContent(element);

		}

		// 设置xml文档输出的格式
		Format format = Format.getPrettyFormat();
		XMLOutputter out = new XMLOutputter(format);
		// 将得到的xml文档输出到文件流中
		try
		{
			out.output(document, new FileOutputStream(file));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 利用JDom进行xml文档的读取操作
	 */
	public static void parserXml(File file)
	{
		// 建立解析器
		SAXBuilder builder = new SAXBuilder();
		try
		{
			// 将解析器与文档关联
			document = builder.build(file);
		}
		catch (JDOMException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		// 读取根元素
		Element root = document.getRootElement();

		// 读取元素集合
		List<?> cityList = root.getChildren("city");

		for (int i = 0; i < cityList.size(); i++)
		{
			Element ele = (Element) cityList.get(i);
			City city = new City();
			try
			{
				city.set("ID", ele.getChild("ID").getText());
				city.set("Population", ele.getChild("Population").getText());
				city.set("CountryCode", ele.getChild("CountryCode").getText());
				city.set("District", ele.getChild("District").getText());
				city.set("Name", ele.getChild("Name").getText());
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
			}

			cities.add(city);
		}

	}

	public static StringBuffer readFileByChars()
	{
		StringBuffer xmlRaw = new StringBuffer();
		Reader reader = null;
		try
		{
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(new File(PATH)));
			int tempchar;
			while ((tempchar = reader.read()) != -1)
			{
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r')
				{
					xmlRaw.append((char) tempchar);
					// System.out.print((char) tempchar);
				}
			}
			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return xmlRaw;
	}

	public static List<City> makeFile()
	{
		// XMLTest jdom = new XMLTest();
		File file = new File(PATH);
		createXml(file);
		parserXml(file);
		return cities;
	}

	/**
	 * 测试
	 */
	/*
	 * public static void main(String[] args) {
	 * 
	 * XMLTest jdom = new XMLTest(); File file = new File(PATH);
	 * jdom.createXml(file); jdom.parserXml(file); }
	 */
}
