package com.andria.dota.Model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection="myHeroes")
public class Hero {

@Id
private String _id;
private String name;
private String localizedName;
private String primaryAttr;
private String attackType;
private List <String> roles = null;

//private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//Here we dont need getters and setters since we are using the lombok.data library to 
//automatically create them

public Hero(String _id, String name,String localizedName,String primaryAttr, String attackType, List <String> roles ) {

 this._id = _id;
 this.name = name;
 this.localizedName = localizedName;
 this.primaryAttr = primaryAttr;
 this.localizedName = attackType;
 this.roles = roles;
}
}
