package com.helpdeskapi.server;

import com.helpdeskapi.rest.ApiApplication;
import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.Optional;
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

public class Helpdeskserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Helpdeskserver.class);
    public static void main(String... args) throws Exception {
    int port = Optional.ofNullable(System.getProperty("port")).map(Integer::parseInt).orElse(8443);
   //String mode = ofNullable(System.getProperty("mode")).orElse("dev");
   ClassLoader classLoader =Helpdeskserver.class.getClassLoader();
    URI uri = classLoader.getResource("../../../../../../../../system-dev.properties").toURI();
                Path raizModulo = Paths.get(uri).getParent();
        Path rutaArchivo = raizModulo.resolve("subdirectorio/nuevoArchivo.properties");
                Files.createFile(rutaArchivo);
                String s = rutaArchivo.toAbsolutePath().toString();
File archivo = new File(s);
    Config    config = ConfigFactory.parseFile(archivo);
   String keystore = config.getString("server.keystore.file");
           HttpConfiguration httpsConfiguration = new HttpConfiguration();
           httpsConfiguration.setSecureScheme(HTTPS.asString());
           httpsConfiguration.setSecurePort(port);
           SecureRequestCustomizer customizer = new SecureRequestCustomizer();
           customizer.setSniHostCheck(false);
           customizer.setSniRequired(false);
           httpsConfiguration.addCustomizer(customizer);
           HttpConnectionFactory httpsConnectionFactory= new HttpConnectionFactory(httpsConfiguration);
           SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
           sslContextFactory.setKeyStorePath(keystore);
           sslContextFactory.setKeyStoreType("PKCS12"); 
           sslContextFactory.setKeyStorePassword("Vizuru");
           sslContextFactory.setKeyManagerPassword("Vizuru");
          sslContextFactory.setTrustAll(true);
          
          SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory,HTTP_1_1.asString());
          Server server = new Server();
          ServerConnector  httpsConnector = new ServerConnector(server, sslConnectionFactory,httpsConnectionFactory);
          httpsConnector.setName("secure");
          httpsConnector.setPort(httpsConfiguration.getSecurePort());
          server.addConnector(httpsConnector);
          ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
          servletContextHandler.setContextPath("/");
          servletContextHandler.setBaseResource(Resource.newResource("src/main/resources/www"));
          servletContextHandler.addServlet(DefaultServlet.class, "/");
          server.setHandler(servletContextHandler);
          ServletHolder apiServletHolder = servletContextHandler.addServlet(ServletContainer.class,"/api/*");
          apiServletHolder.setInitParameter("jakarta.ws.rs.Application",ApiApplication.class.getName());
          LOGGER.info("Iniciando servidor en el puerto {}", port);
          server.start();
          server.join();
    }
}
