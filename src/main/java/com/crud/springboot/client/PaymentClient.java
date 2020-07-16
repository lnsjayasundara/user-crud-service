package com.crud.springboot.client;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.crud.springboot.model.behpardakht.BpPayRequest;
import com.crud.springboot.model.behpardakht.BpPayRequestResponse;
import com.crud.springboot.model.behpardakht.ObjectFactory;

public class PaymentClient extends WebServiceGatewaySupport {

	public BpPayRequestResponse payRequest() {
		try {
			ObjectFactory objectFactory = new ObjectFactory();
			BpPayRequest bpPayRequest = objectFactory.createBpPayRequest();
			bpPayRequest.setTerminalId(1801994);
			bpPayRequest.setUserName("airar921");
			bpPayRequest.setUserPassword("80117690");
			bpPayRequest.setOrderId(10);
			bpPayRequest.setAmount(100);
			bpPayRequest.setLocalDate("20101008");
			bpPayRequest.setLocalTime("102003");
			bpPayRequest.setAdditionalData("");
			bpPayRequest.setPayerId("10");
			bpPayRequest.setCallBackUrl("http://www.mysite.com/myfolder/callbackmellat.aspx");
			JAXBElement<BpPayRequest> jaxbBpPayRequest = objectFactory.createBpPayRequest(bpPayRequest);

			JAXBElement<BpPayRequestResponse> jaxbBpPayRequestResponse = (JAXBElement<BpPayRequestResponse>)getWebServiceTemplate()
			.marshalSendAndReceive("https://bpm.shaparak.ir/pgwchannel/services/pgw", jaxbBpPayRequest);
			BpPayRequestResponse bpPayResponse = jaxbBpPayRequestResponse.getValue();
			System.out.println("response >>>>>>>"+bpPayResponse+" return >>>"+bpPayResponse.getReturn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
