package com.example.KlashaBE.controller;

import com.example.KlashaBE.apiRequest.StateRequest;
import com.example.KlashaBE.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class StateController {
    private final StateService stateService;

    @GetMapping("/fetch-all-states")
    public void getAllStatesByCountry(@RequestBody StateRequest country){
        stateService.getAllStatesByCountry(country);
    }
}
