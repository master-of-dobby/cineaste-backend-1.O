//package com.naveen.userreg.controllers;
//
//import org.springframework.web.bind.annotation.*;
//import com.google.cloud.dialogflow.v2.*;
//import com.google.cloud.dialogflow.v2.QueryResult;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:3000", "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
//public class TicketBookingController {
//
//    @PostMapping("/book-ticket")
//    public Map<String, String> bookTicket(@RequestBody Map<String, String> request) {
//        String query = request.get("query");
//        String response = processNlpQuery(query);
//        Map<String, String> responseBody = new HashMap<>();
//        responseBody.put("message", response);
//        return responseBody;
//    }
//
//    private String processNlpQuery(String query) {
//        // Replace with actual NLP processing (e.g., Google Dialogflow)
//        // Example using Google Dialogflow:
//        try (SessionsClient sessionsClient = SessionsClient.create()) {
//            SessionName session = SessionName.of("cineaste-430108", UUID.randomUUID().toString());
//            TextInput.Builder textInput = TextInput.newBuilder().setText(query).setLanguageCode("en-US");
//
//            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
//            DetectIntentRequest detectIntentRequest = DetectIntentRequest.newBuilder()
//                                                              .setSession(session.toString())
//                                                              .setQueryInput(queryInput)
//                                                              .build();
//
//            DetectIntentResponse detectIntentResponse = sessionsClient.detectIntent(detectIntentRequest);
//            QueryResult queryResult = detectIntentResponse.getQueryResult();
//
//            return queryResult.getFulfillmentText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error processing query";
//        }
//    }
//}