package com.swebackend.swebackend.service;

import org.springframework.stereotype.Service;

import com.swebackend.swebackend.model.Request;
import com.swebackend.swebackend.repository.RequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    public Request create(Request request) { return repository.save(request); }

}
