package com.swebackend.swebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swebackend.swebackend.model.Request;
import com.swebackend.swebackend.model.RequestState;
import com.swebackend.swebackend.repository.RequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    public Request create(Request request) { return repository.save(request); }

    public List<Request> getAll() { return repository.findAll(); }

    public Request getOne(Long id) {return repository.findById(id).orElseThrow();}

    public Request changeState(Long id, RequestState newState) {
        Request s = repository.findById(id).orElseThrow();
        List<RequestState> pendingPossibleChanges= List.of(RequestState.APROBADA, RequestState.RECHAZADA);
        List<RequestState> approvedPossibleChanges= List.of(RequestState.CANCELADA);
        List<RequestState> unmatableStates= List.of(RequestState.CANCELADA, RequestState.RECHAZADA);

        if(s.getState() == RequestState.PENDIENTE && !pendingPossibleChanges.contains(newState) ) {  
            throw new IllegalStateException("No puede cambiar el estado PENDIENTE a APROBADA o RECHAZADA");
        }

        if(s.getState() == RequestState.APROBADA && !approvedPossibleChanges.contains(newState)) {
            throw new IllegalStateException("No puede cambiar el estado de una solicitud que se encuentra APROBADA a otro estado que no sea CANCELADA");
        }

        if(unmatableStates.contains(s.getState())) {
            throw new IllegalStateException("No puede cambiar el estado de peticiones que estén RECHAZADA or CANCELADA");
        }

        s.setState(newState);
        return repository.save(s);
    }

}
