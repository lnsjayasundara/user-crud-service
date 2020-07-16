package com.crud.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.crud.springboot.client.PaymentClient;

/**
 * @author somnath.musib
 */
@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.crud.springboot.model.behpardakht");
        return jaxb2Marshaller;
    }

    @Bean
    public PaymentClient paymentClient(Jaxb2Marshaller jaxb2Marshaller) {
    	PaymentClient paymentClient = new PaymentClient();
    	paymentClient.setDefaultUri("https://bpm.shaparak.ir/pgwchannel/services/pgw");
    	paymentClient.setMarshaller(jaxb2Marshaller);
    	paymentClient.setUnmarshaller(jaxb2Marshaller);
        return paymentClient;
    }
}
