package com.reactive.demo.users;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;



@RestController
@Slf4j
public class UsersController {
	
    @Autowired
    private UsersRepository repository;
    
    @PostMapping("/login")
    public Mono<ResponseEntity<Users>> login(@Valid @RequestBody Users user) {
    	
    	System.out.println(user.toString());
    	
//    	return Mono.just("test");
    	return Mono.just(repository.findById(user.getUserId())
    					.filter( u -> u.getPassword().equals(user.getPassword()))
		        		.map(result -> ResponseEntity.ok(result)).get())
		                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    
    @PostMapping("/create")
//    public Mono<ServerResponse> createUser(@RequestBody Users user){
    public Mono<Users> createUser(@RequestBody Users user){
    	
//		return Mono.just(repository.save(user));
    	repository.save(user);
    	Users saveUser = repository.findOne(Example.of(user)).get();
    	System.out.println(saveUser);
    	
    	return Mono.just(saveUser);
    }
    
    
//    public Mono<ServerResponse> list(ServerRequest request) {
//        List<BoardPost> posts = repository.findAll();
//        Flux<BoardPost> boardPostFlux = Flux.fromIterable(posts);
//        return ServerResponse.ok().body(boardPostFlux, BoardPost.class);
//    }
    
}




