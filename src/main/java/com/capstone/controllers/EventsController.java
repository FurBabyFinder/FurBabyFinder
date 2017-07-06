package com.capstone.controllers;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;

/**
 * Created by frenchfryes on 7/6/17.
 */
@Controller
public class EventsController {
    GoogleCredential credential = GoogleCredential.getApplicationDefault();

    @GetMapping("/events")
    public Calendar(com.google.api.client.http.HttpTransport transport,
                    com.google.api.client.json.JsonFactory jsonFactory,
                    com.google.api.client.http.HttpRequestInitializer httpRequestInitializer)



}
