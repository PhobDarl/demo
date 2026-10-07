package com.example.demo;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class GamesConfig {

    // servlet exposes the wsdl to the internet
    // also directs soap requests and routes them to the correct endpoint
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // this one creates the WSDL that is used as the 'contract' for clients based on the team.xsd
    @Bean(name = "football")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema footballSchema){

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("FootballPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://www.phobdarl.com/xml/football");

        wsdl11Definition.setSchema(footballSchema);

        return wsdl11Definition;
    }

    // object that allows spring to use the xsd
    @Bean
    public XsdSchema footballSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("team.xsd")
        );
    }


}
