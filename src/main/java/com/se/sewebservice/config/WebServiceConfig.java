// package com.se.sewebservice.config;

// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.ws.config.annotation.EnableWs;
// import org.springframework.ws.config.annotation.WsConfigurer;
// import org.springframework.ws.transport.http.MessageDispatcherServlet;
// import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
// import org.springframework.xml.xsd.SimpleXsdSchema;
// import org.springframework.xml.xsd.XsdSchema;

// @EnableWs
// @Configuration
// public class WebServiceConfig  implements WsConfigurer {

    // @Bean
    // public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
    //         ApplicationContext context) {
    //     MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    //     servlet.setApplicationContext(context);
    //     servlet.setTransformWsdlLocations(true);

    //     return new ServletRegistrationBean<>(servlet, "/ws/*");
    // }

//     @Bean(name = "calculator")
//     public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
//         DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
//         wsdl.setPortTypeName("CalculatorPort");
//         wsdl.setLocationUri("/ws/calculator");
//         wsdl.setTargetNamespace("http://example.com/calculator");
//         wsdl.setSchema(schema);
//         return wsdl;
//     }

//         @Bean(name = "shapetool")
//     public DefaultWsdl11Definition shapeToolWsdlDefinition(XsdSchema shapeSchema) {
//         DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
//         wsdl.setPortTypeName("ShapeToolPort");
//         wsdl.setLocationUri("/ws/shapetool");
//         wsdl.setTargetNamespace("http://wuniversity.edu/se/shapetool");
//         wsdl.setSchema(shapeSchema);
//         return wsdl;
//     }

//     @Bean
//     public XsdSchema shapeSchema() {
//         return new SimpleXsdSchema(new ClassPathResource("schemas/shapetool.xsd"));
//     }


//     @Bean
//     public XsdSchema calculatorSchema() {
//         return new SimpleXsdSchema(new ClassPathResource("schemas/calculator.xsd"));
//     }
// }

package com.se.sewebservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import java.util.List;
@Configuration
@EnableWs
public class WebServiceConfig implements WsConfigurer {

    // 1. Update the Calculator WSDL Bean



  // 1. Create the Interceptor as a managed Bean
@Bean
public PayloadValidatingInterceptor validatingInterceptor() throws Exception {
    PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
    
    // Set the schemas
    interceptor.setSchemas(new Resource[] {
        new ClassPathResource("schemas/calculator.xsd"),
        new ClassPathResource("schemas/shapetool.xsd")
    });
    
    // Set validation settings
    interceptor.setValidateRequest(true);
    interceptor.setValidateResponse(true);
    
    // Set your custom error message
    interceptor.setFaultStringOrReason("ShapeTool Error: Provided data does not match the required format.");
    
    // IMPORTANT: This line creates the internal validator engine
    interceptor.afterPropertiesSet(); 
    
    return interceptor;
}

// 2. Add that Bean to the list of interceptors
@Override
public void addInterceptors(List<EndpointInterceptor> interceptors) {
    try {
        interceptors.add(validatingInterceptor());
    } catch (Exception e) {
        // This will show an error in your terminal if your XSD files are missing
        System.err.println("CRITICAL ERROR: Could not initialize SOAP Validator: " + e.getMessage());
    }
}


    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "calculator")
    public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("calculatorSchema") XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("CalculatorPort");
        wsdl.setLocationUri("/ws/calculator");
        wsdl.setTargetNamespace("http://example.com/calculator");
        wsdl.setSchema(schema);
        return wsdl;
    }

    // 2. Update the ShapeTool WSDL Bean
    @Bean(name = "shapetool")
    public DefaultWsdl11Definition shapeToolWsdlDefinition(@Qualifier("shapeSchema") XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("ShapeToolPort");
        wsdl.setLocationUri("/ws/shapetool");
        wsdl.setTargetNamespace("http://webservice.wdu/shapetool");
        wsdl.setSchema(schema);
        return wsdl;
    }

    // --- Schema Beans (Keep these as they are) ---

    @Bean
    public XsdSchema calculatorSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/calculator.xsd"));
    }

    @Bean
    public XsdSchema shapeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/shapetool.xsd"));
    }
    
    // ... rest of your config (ServletRegistrationBean, etc.)
}