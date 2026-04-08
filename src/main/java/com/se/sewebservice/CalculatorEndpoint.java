package com.se.sewebservice;

import com.example.calculator.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
@Endpoint
public class CalculatorEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/calculator";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddRequest")
    @ResponsePayload
    public AddResponse add(@RequestPayload AddRequest request) {
        AddResponse response = new AddResponse();
        response.setResult(request.getA() + request.getB());
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SubtractRequest")
    @ResponsePayload
    public SubtractResponse subtract(@RequestPayload SubtractRequest request) {
        SubtractResponse response = new SubtractResponse();
        response.setResult(request.getA() - request.getB());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MultiplyRequest")
    @ResponsePayload
    public MultiplyResponse multiply(@RequestPayload MultiplyRequest request) {
        MultiplyResponse response = new MultiplyResponse();
        response.setResult(request.getA() * request.getB());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ModulusRequest")
    @ResponsePayload
    public ModulusResponse modulus(@RequestPayload ModulusRequest request) {
        ModulusResponse response = new ModulusResponse();
        response.setResult(request.getA() % request.getB());
        return response;
    }


}
