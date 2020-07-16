package com.crud.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.springboot.client.PaymentClient;
import com.crud.springboot.model.behpardakht.BpPayRequestResponse;

@SpringBootApplication
public class UserCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCrudServiceApplication.class, args);
	}
	
	@Bean
    CommandLineRunner lookup(PaymentClient paymentClient) {
        return args -> {
            String name = "Sajal";//Default Name
            if(args.length>0){
                name = args[0];
            }
            
            System.out.println("CommandLineRunner");
            BpPayRequestResponse response = paymentClient.payRequest();
        };
	}
}
