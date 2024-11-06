package com.helpdeskapi.server;

import com.helpdeskapi.rest.ApiApplication;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.net.URL;
import static java.util.Optional.ofNullable;
//import static java.util.Optional.ofNullable;
import static org.eclipse.jetty.http.HttpScheme.HTTPS;
import static org.eclipse.jetty.http.HttpVersion.HTTP_1_1;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.helpdeskapi.server.config.ConfigKey.*;
import static com.helpdeskapi.server.config.SystemKey.*;
import java.io.IOException;

@SuppressWarnings("PMD.TooManyStaticImports") 
public class Helpdeskserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Helpdeskserver.class);
    private static final String ROOT_CONTEXT="/";
    private static final String API_PATTERN="/api/*";
    private static final String APPLICATION_KEY="jakarta.ws.rs.Application";
    private static Server createJettyServer(int port, Config config) throws IOException{
    HttpConfiguration httpsConfiguration = new HttpConfiguration();
           httpsConfiguration.setSecureScheme(HTTPS.asString());
           httpsConfiguration.setSecurePort(port);
           SecureRequestCustomizer customizer = new SecureRequestCustomizer();
           customizer.setSniHostCheck(false);
           customizer.setSniRequired(false);
           httpsConfiguration.addCustomizer(customizer);
           HttpConnectionFactory httpsConnectionFactory= new HttpConnectionFactory(httpsConfiguration);
           SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
           sslContextFactory.setKeyStorePath(config.getString(SERVER_KEYSTORE_FILE.getKey()));
           sslContextFactory.setKeyStoreType(config.getString(SERVER_KEYSTORE_TYPE.getKey())); 
           sslContextFactory.setKeyStorePassword(config.getString(SERVER_KEYSTORE_PASSWORD.getKey()));
           sslContextFactory.setKeyManagerPassword(config.getString(SERVER_KEYSTORE_PASSWORD.getKey()));
          sslContextFactory.setTrustAll(true);
          
          SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory,HTTP_1_1.asString());
          Server server = new Server();
          ServerConnector  httpsConnector = new ServerConnector(server, sslConnectionFactory,httpsConnectionFactory);
          httpsConnector.setPort(httpsConfiguration.getSecurePort());
          server.addConnector(httpsConnector);
          ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
          servletContextHandler.setContextPath(ROOT_CONTEXT);
          servletContextHandler.setBaseResource(Resource.newResource(config.getString(SERVER_WEB_CONTENT.getKey())));
          servletContextHandler.addServlet(DefaultServlet.class, ROOT_CONTEXT);
          server.setHandler(servletContextHandler);
          ServletHolder apiServletHolder = servletContextHandler.addServlet(ServletContainer.class, API_PATTERN);
          apiServletHolder.setInitParameter(APPLICATION_KEY,ApiApplication.class.getName());
          return server;
    }
    public static void main(String... args) throws Exception {
    int port = Integer.parseInt(ofNullable(System.getProperty(PORT.getKey())).orElse(PORT.getdefaultValue()));
  String mode = ofNullable(System.getProperty(MODE.getKey())).orElse(MODE.getdefaultValue());
  String url=String.format("https://raw.githubusercontent.com/VizuruDesarrollo/HelpDeskApi/refs/heads/main/HelpDeskAPI/system-dev.properties",mode);
  
      Config    config = ConfigFactory.parseURL(new URL(url));
           Server server =createJettyServer(port,config);
          LOGGER.info("Iniciando servidor en el puerto {}", port);
          server.start();
          server.join();
    }
}
