package com.andria.dota.repositories;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.andria.dota.Model.Hero;



//Rulebook in the form of aninterface defining operations that can be performed on our mongodb
//Database



//lets add some context to our hero repo by providing custom operations on our mongoDB
//database extending the MongoRepository interface providing the CRUD functionality


public interface CustomHeroRepo {
	
	//Operations here are simple automatic ones 
	
	//Here we want to return a list of heroes that contain the roles provided in the method
	 @Query(value = "{ 'roles' : {$all : [?0] }}")
	    public List<Hero> findMatchingHeroes(String[] roles);
	 //We want to check if there are any new heroes added to our external API sources database
	 //and update if this is the case
	 
	    public HttpStatus updateDatabase();
	   

	




}
