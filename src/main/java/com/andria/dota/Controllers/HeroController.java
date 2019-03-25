package com.andria.dota.Controllers;

import java.util.List;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.andria.dota.repositories.heroRepo;

import io.swagger.annotations.ApiOperation;

import com.andria.dota.Model.Hero;

@RestController
@RequestMapping("/heroes")
public class HeroController {

  //My hero repo , this acts like a middleman/presenter delegating responsibility to the correct
 //Locations	

  private heroRepo myrepo;
  
  
  
  @GetMapping("/heroes/all")
  public List<Hero> returnAllHeroes() {
		return (List<Hero>) myrepo.findAll();
	}


  //This is a post request that will update the database with latest heroes utilising
  //an external API
  
  @PostMapping(value="/heroes/update")
  ResponseEntity<?>updateHeroDatabase(){
	  
	HttpStatus result =   myrepo.updateDatabase();
	  
	  if(result.is2xxSuccessful()) 
		    return new ResponseEntity<>(HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  
  }
  

  // Find all heroes based on Role (Tank/Nuker/Carry/Solo)
  //Role simply refers to the job thew hero was made to carry out within
  //The context of the game
  
	@GetMapping("/heroes/{role}")
	public List<Hero>heroesMatching(@PathVariable String[] roles) {

		return myrepo.findMatchingHeroes(roles);
	}

}
