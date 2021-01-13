package com.example.maven.springbootlearn;

// import org.apache.catalina.Context;
// import org.apache.catalina.connector.Connector;
// import org.apache.tomcat.util.descriptor.web.SecurityCollection;
// import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringbootlearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootlearnApplication.class, args);
	}


	// public Connector httpConnector(){
	// 	Connector con = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	// 	con.setScheme("http");
	// 	con.setSecure(false);
	// 	con.setPort(80);
	// 	con.setRedirectPort(8443);
	// 	return con;
	// }

	// @Bean
	// public ServletWebServerFactory  createFac(){
	// 	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	// 		@Override
	// 		protected void postProcessContext(Context context) {
	// 			SecurityConstraint securityConstraint = new SecurityConstraint();
	// 			securityConstraint.setUserConstraint("CONFIDENTIAL");
	// 			SecurityCollection collection = new SecurityCollection();
	// 			collection.addPattern("/*");
	// 			securityConstraint.addCollection(collection);
	// 			context.addConstraint(securityConstraint);
	// 		}
	// 	};
	// 	tomcat.addAdditionalTomcatConnectors(httpConnector());
	// 	return tomcat;
	// }
}