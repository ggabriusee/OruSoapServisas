/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.servisas.OruSoapServisas;

/**
 *
 * @author gabrius
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class SoapWebServiceConfig extends WsConfigurerAdapter{
    
    
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/locations/*");
    }
    
    @Bean
    public XsdSchema WeatherSchema() {
        return new SimpleXsdSchema(new ClassPathResource("weather.xsd"));
    }

    @Bean(name = "location")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema weatherSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(weatherSchema);
        definition.setLocationUri("/locations");
        definition.setPortTypeName("LocationPort");
        definition.setTargetNamespace("com/locations");
        return definition;
    }
}
