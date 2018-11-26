/**********************************************************************
*
* Project Alkolexa
* @author Anian Weber, weber.anian@hm.edu
* @version 26.11.2018
* 
* This Class Handels the API connection for the Alexa Skill "Alkolexa"
*
***********************************************************************/
package main.java.alkolexa.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.json.JsonObject;

public class CocktailAPI {

	public CocktailAPI() {

		// Simple Basic Tests
		// JsonObject cocktail = searchForCocktail("magerita");
		// System.out.println(getCocktailName(cocktail));
		// System.out.println(getCocktailInstructions(cocktail));
		// System.out.println(getCocktailIngredients(cocktail));
		// System.out.println(getCocktailMeasures(cocktail));

	}

	// Returns the First found Cocktail
	public JsonObject searchForCocktail(String searchCocktail) {
		String url = "";
		try {
			url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="
					+ URLEncoder.encode(searchCocktail, "UTF-8");
			System.out.println("Request: " + url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonObject jsonObj = JsonObjectFromUrlUtil.getJsonObjectFromUrl(url);
		JsonObject firstFoundCocktail = (JsonObject) jsonObj.get("drinks").asJsonArray().get(0);
		System.out.println("Raw response: " + jsonObj);
		return firstFoundCocktail;
	}

	public String getCocktailName(JsonObject cocktailJson) {
		String name = cocktailJson.getString("strDrink");
		return name;
	}

	// Returns the Instructions of a given Cocktail
	public String getCocktailInstructions(JsonObject cocktailJson) {
		String instructions = cocktailJson.getString("strInstructions");
		return instructions;
	}

	public ArrayList<String> getCocktailIngredients(JsonObject cocktailJson) {
		ArrayList<String> ingredients = new ArrayList<>();
		int currentIngredientCount = 1;
		while (!cocktailJson.getString("strIngredient" + currentIngredientCount).isEmpty()) {
			ingredients.add(cocktailJson.getString("strIngredient" + currentIngredientCount));// System.out.println(ingredients.get(currentIngredientCount-1));
			currentIngredientCount++;
		}
		return ingredients;
	}

	public ArrayList<String> getCocktailMeasures(JsonObject cocktailJson) {
		ArrayList<String> measures = new ArrayList<>();
		int currentMeasuresCount = 1;
		while (!cocktailJson.getString("strMeasure" + currentMeasuresCount).isEmpty()) {

			measures.add(cocktailJson.getString("strMeasure" + currentMeasuresCount));// System.out.println(ingredients.get(currentIngredientCount-1));
			// System.out.println(measures.get(currentMeasuresCount-1));
			currentMeasuresCount++;
		}
		return measures;
	}

	public static void main(String[] args) {
		new CocktailAPI();

	}

}