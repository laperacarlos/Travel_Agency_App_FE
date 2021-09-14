package com.kodilla.travelagencyfe.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.travelagencyfe.domain.*;
import com.kodilla.travelagencyfe.gson.LocalDateSerializer;
import com.kodilla.travelagencyfe.gson.LocalDateTimeSerializer;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@NoArgsConstructor
public class BackendEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendEndpoint.class);
    private static final String baseUrl = "http://localhost:8085/travelAgencyBe/v1";
    private final RestTemplate restTemplate = new RestTemplate();

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        return gsonBuilder.create();
    }

    public void createUser(User user) {
        String jsonContent = createGson().toJson(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/users").build().encode().toUri();

        restTemplate.postForObject(url, request, User.class);
    }

    public void updateUser(User user) {
        String jsonContent = createGson().toJson(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/users").build().encode().toUri();
        restTemplate.put(url, request);
    }

    public List<User> getAllUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/users")
                .build()
                .encode()
                .toUri();

        try {
            User[] apiResponse = restTemplate.getForObject(url, User[].class);
            return new ArrayList<>(Optional.ofNullable(apiResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Complaint> getAllComplaints() {
        return Collections.emptyList();
    }

    public List<ComplaintAnswer> getAllComplaintAnswers() {
        return Collections.emptyList();
    }

    public List<Travel> getAllTravels() {
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/travels").build().encode().toUri();
        return getTravels(url);
    }

    public List<Travel> getOpenTravels() {
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/travels/open").build().encode().toUri();
        return getTravels(url);
    }

    private List<Travel> getTravels(URI url) {
        try {
            Travel[] apiResponse = restTemplate.getForObject(url, Travel[].class);
            return new ArrayList<>(Optional.ofNullable(apiResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void createTravel(Travel travel) {
        String jsonContent = createGson().toJson(travel);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/travels").build().encode().toUri();

        restTemplate.postForObject(url, request, Travel.class);
    }

    public void updateTravel(Travel travel) {
        String jsonContent = createGson().toJson(travel);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/travels").build().encode().toUri();

        restTemplate.put(url, request);
    }

    public List<Reservation> getAllReservations() {
        return Collections.emptyList();
    }
}
