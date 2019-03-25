package com.andria.dota.Exceptions;

import java.util.function.Supplier;

import org.springframework.web.bind.annotation.ResponseStatus;


class HeroListDoesntExist extends Exception{
	
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public HeroListDoesntExist(String[] roles){
	  
    super("No Heroes with the following roles together" + roles.toString()+"");
  }


}