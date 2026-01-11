package com.swebackend.swebackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swebackend.swebackend.model.Request;
import com.swebackend.swebackend.service.RequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
