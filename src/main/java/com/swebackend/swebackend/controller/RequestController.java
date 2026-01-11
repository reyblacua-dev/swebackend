package com.swebackend.swebackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swebackend.swebackend.model.Request;
import com.swebackend.swebackend.model.RequestState;
import com.swebackend.swebackend.service.RequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService service;

    @PostMapping
    public ResponseEntity<Request> create(@RequestBody Request request) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<Request> changeState(@PathVariable Long id, @RequestBody String newState) {
        Request updatedRequest = service.changeState(id, RequestState.valueOf(newState));
        return ResponseEntity.ok(updatedRequest);
    }

}
