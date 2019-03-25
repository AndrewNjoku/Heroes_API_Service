
package com.andria.dota.repositories;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.andria.dota.Model.Hero;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.ApiOperation;



public class HeroRepoImpl implements CustomHeroRepo {
	
	
	//This is the instance object used to perform operations , it is essentially how we 
	//are interfacing with our MongoDatabase
	
	private final MongoTemplate operations;
	
	
	//Used for REST calls
	RestTemplate restTemplate;
	
	//This is the uri for our external hero datrabase 
	final String externalHeroUri = "https://api.opendota.com/api/heroes";
    //TODO need to change this uri to reflect the openDota api to return all heroes
	
	
	//Constructor 
	  @Autowired
	  public HeroRepoImpl(MongoTemplate operations) {

	    Assert.notNull(operations, "MongoTemplate must not be null!");
	    
	    this.operations = operations;
	  }
	
	  
	  
		@Override
		public HttpStatus updateDatabase() {
			
			 restTemplate = new RestTemplate();
			 
			 // due to type erasure with Java generics.
			 //When the application is running, it has no knowledge of what type of object 
			 //is in the list. This means the data in the list cannot be deserialized into 
			 //the appropriate type.So we need to use response Entity as a middleman
			 
			 ResponseEntity<List<Hero>> response = restTemplate.exchange(
					 externalHeroUri,
					  HttpMethod.GET,
					  null,
					  new ParameterizedTypeReference<List<Hero>>(){});
			 
			 //TODO Here i could use Reactive Java to wrap response in an observable and use an anonymous
			 //inner class together with an interface declaration to store the response body
			 //Straight into my database as soon as it comes available using the mongoDBimplementation
			 //by overriding its specified interface method!
			 // I will do this if i have more time
			 //Lets get the status code and print to console or somewhere useful in
			 //case there is an error so we can establish what the problem is
			 
			 HttpStatus statusCode = response.getStatusCode();
			 
			 System.out.println("The http response for updating hero database is:" +  statusCode.toString());
			 List<Hero> heroes = response.getBody();
			 
			 operations.save(heroes);
			 
			return statusCode;
			   
		}
			
	
	
      @Override
	  public List<Hero>findMatchingHeroes(String [] roles){
		  
		List<Hero> heroes = operations
				.find(Query.query(Criteria.where("roles").all(roles)), Hero.class);
		return heroes;
				
	  }
	 
	
}
