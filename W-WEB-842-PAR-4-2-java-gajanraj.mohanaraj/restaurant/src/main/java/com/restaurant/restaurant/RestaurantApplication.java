package com.restaurant.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication  {


	public static void main(String[] args) {

		SpringApplication.run(RestaurantApplication.class, args);
	}



	// public void createNom(){
	// 	// System.out.println("je usis dna le create");
	// 	String sql = "INSERT INTO nom(nom)VALUES(?)";
	// 	jdbcTemplate.update(sql,"cyril");
	// 	System.out.println("le nom a bien été ajouter");
	// }


	// @GetMapping("/hello")
    // public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    //   return String.format("Hello %s!", name);
    // }
}
