package config;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import controller.CityController;
import model.City;

public class TestConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configHandler(Handlers arg0) {}

	@Override
	public void configInterceptor(Interceptors arg0) {}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://120.77.176.18:8888/world?useSSL=false", "gzj", "GZJdmysql2017");
		cp.setDriverClass("com.mysql.jdbc.Driver");
		me.add(cp);  
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);  
		me.add(arp);  
		arp.addMapping("city", "ID", City.class);
	}

	@Override
	public void configRoute(Routes arg0) {
		arg0.add("/city", CityController.class,"/");
		arg0.add("/", CityController.class);
		//arg0.add(controllerKey, controllerClass, viewPath);
	}
}