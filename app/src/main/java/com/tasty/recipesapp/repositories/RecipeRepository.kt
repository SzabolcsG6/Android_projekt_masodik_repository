import android.content.Context
import com.google.gson.Gson
import com.tasty.recipesapp.data.RecipeDao
import com.tasty.recipesapp.dtos.Component
import com.tasty.recipesapp.dtos.Credit
import com.tasty.recipesapp.dtos.Instruction
import com.tasty.recipesapp.dtos.Measurement
import com.tasty.recipesapp.dtos.Nutrition
import com.tasty.recipesapp.dtos.RecipeDto
import com.tasty.recipesapp.dtos.Section
import com.tasty.recipesapp.dtos.Tag
import com.tasty.recipesapp.dtos.Topic
import com.tasty.recipesapp.dtos.UserRating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import com.tasty.recipesapp.dtos.Unit
class RecipeRepository(
    private val recipeDao: RecipeDao,
    private val applicationContext: Context // Pass the Context here
) {
    private val gson = Gson()
    private var _recipes: List<RecipeDto> = emptyList()

    suspend fun loadRecipesFromJson(): List<RecipeDto> {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = applicationContext.assets.open("all_recipes.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val jsonObject = JSONObject(json)
                val resultsArray = jsonObject.getJSONArray("results")

                val recipesList = mutableListOf<RecipeDto>()

                for (i in 0 until resultsArray.length()) {
                    val recipeObject = resultsArray.getJSONObject(i)

                    val recipeDto = extractRecipeDtoFromJson(recipeObject)

                    recipesList.add(recipeDto)
                }

                _recipes = recipesList
                recipesList

            } catch (e: IOException) {
                emptyList()
            }
        }
    }

    suspend fun getAllRecipes(): List<RecipeDto> {
        return _recipes
    }
    // Extract Credits attribute
    private fun extractCredits(creditsArray: JSONArray): List<Credit> {
        val credits = mutableListOf<Credit>()
        for (i in 0 until creditsArray.length()) {
            val creditObject = creditsArray.getJSONObject(i)
            val name = creditObject.optString("name", "")
            val role = creditObject.optString("role", "")
            credits.add(Credit(name, role))
        }
        return credits
    }

    // Extract Instructions attribute
    private fun extractInstructions(instructionsArray: JSONArray): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        for (i in 0 until instructionsArray.length()) {
            val instructionObject = instructionsArray.getJSONObject(i)
            val id = instructionObject.optInt("id", 0)
            val appliance = instructionObject.optString("appliance", null)
            val endTime = instructionObject.optInt("endTime", 0)
            val temperature = instructionObject.optString("temperature", null)
            val position = instructionObject.optInt("position", 0)
            val displayText = instructionObject.optString("displayText", "")
            val startTime = instructionObject.optInt("startTime", 0)
            instructions.add(
                Instruction(id, appliance, endTime, temperature, position, displayText, startTime)
            )
        }
        return instructions
    }

// Implement the remaining extraction functions (Sections, Components, Tags, Topics, UserRatings, Nutrition) similarly
// These functions will extract attributes from the corresponding JSONArrays or JSONObjects
// Ensure to adjust field names, data types, and parsing logic according to your JSON structure.
// Extract Sections attribute
private fun extractSections(sectionsArray: JSONArray): List<Section> {
    val sections = mutableListOf<Section>()
    for (i in 0 until sectionsArray.length()) {
        val sectionObject = sectionsArray.getJSONObject(i)
        val id = sectionObject.optInt("id", 0)
        val title = sectionObject.optString("title", "")
        val description = sectionObject.optString("description", "")
        sections.add(Section(id, title, description))
    }
    return sections
}

    // Extract Components attribute
    private fun extractComponents(componentsArray: JSONArray): List<Component> {
        val components = mutableListOf<Component>()
        for (i in 0 until componentsArray.length()) {
            val componentObject = componentsArray.getJSONObject(i)
            val id = componentObject.optInt("id", 0)
            val name = componentObject.optString("name", "")
            val quantity = componentObject.optDouble("quantity", 0.0)
            val measurementObject = componentObject.optJSONObject("measurement")
            val measurement = extractMeasurement(measurementObject)
            components.add(Component(id, name, quantity, measurement))
        }
        return components
    }
    private fun extractUnit(unitObject: JSONObject?): Unit? {
        if (unitObject == null) {
            return null
        }

        val id = unitObject.optInt("id", -1)
        val name = unitObject.optString("name", "")
        val symbol = unitObject.optString("symbol", "")

        return Unit(id, name, symbol)
    }

    private fun extractMeasurement(measurementObject: JSONObject?): Measurement? {
        if (measurementObject == null) {
            return null
        }

        val id = measurementObject.optInt("id", -1)
        val unit = extractUnit(measurementObject.optJSONObject("unit"))
        val quantity = measurementObject.optDouble("quantity", 0.0)

        return Measurement(id, unit ?: return null, quantity)
    }


    // Extract Tags attribute
    private fun extractTags(tagsArray: JSONArray): List<Tag> {
        val tags = mutableListOf<Tag>()
        for (i in 0 until tagsArray.length()) {
            val tagObject = tagsArray.getJSONObject(i)
            val id = tagObject.optInt("id", 0)
            val name = tagObject.optString("name", "")
            tags.add(Tag(id, name))
        }
        return tags
    }

    // Extract Topics attribute
    private fun extractTopics(topicsArray: JSONArray): List<Topic> {
        val topics = mutableListOf<Topic>()
        for (i in 0 until topicsArray.length()) {
            val topicObject = topicsArray.getJSONObject(i)
            val id = topicObject.optInt("id", 0)
            val title = topicObject.optString("title", "")
            val description = topicObject.optString("description", "")
            topics.add(Topic(id, title, description))
        }
        return topics
    }

    // Extract UserRatings attribute
    private fun extractUserRatings(userRatingsArray: JSONArray): List<UserRating> {
        val userRatings = mutableListOf<UserRating>()
        for (i in 0 until userRatingsArray.length()) {
            val userRatingObject = userRatingsArray.getJSONObject(i)
            val userId = userRatingObject.optInt("userId", 0)
            val rating = userRatingObject.optInt("rating", 0)
            userRatings.add(UserRating(userId, rating))
        }
        return userRatings
    }

    // Extract Nutrition attribute
    private fun extractNutrition(nutritionObject: JSONObject?): Nutrition? {
        if (nutritionObject != null) {
            val calories = nutritionObject.optInt("calories", 0)
            val fat = nutritionObject.optInt("fat", 0)
            val protein = nutritionObject.optInt("protein", 0)
            val carbohydrates = nutritionObject.optInt("carbohydrates", 0)
            return Nutrition(calories, fat, protein, carbohydrates)
        }
        return null
    }

    private fun extractRecipeDtoFromJson(recipeObject: JSONObject): RecipeDto {
        return RecipeDto(
            id = recipeObject.optString("id", null.toString()),
            name = recipeObject.optString("name", null.toString()),
            imageUrl = recipeObject.optString("imageUrl", null.toString()),
            thumbnailUrl = recipeObject.optString("thumbnail_url", null.toString()),
            promotion = recipeObject.optString("promotion", null.toString()),
            originalVideoUrl = recipeObject.optString("original_video_url", null.toString()),
            servingsNounPlural = recipeObject.optString("servings_noun_plural", null.toString()),
            videoAdContent = recipeObject.optString("video_ad_content", null.toString()),
            seoTitle = recipeObject.optString("seo_title", null.toString()),
            seoPath = recipeObject.optString("seo_path", null.toString()),
            canonicalId = recipeObject.optString("canonical_id", null.toString()),
            beautyUrl = recipeObject.optString("beauty_url", null.toString()),
            draftStatus = recipeObject.optString("draft_status", null.toString()),
            aspectRatio = recipeObject.optString("aspect_ratio", null.toString()),
            difficultyLevel = recipeObject.optString("difficulty_level", null.toString()),
            cuisineType = recipeObject.optString("cuisine_type", null.toString()),
            dietaryInformation = recipeObject.optString("dietary_information", null.toString()),
            mealType = recipeObject.optString("meal_type", null.toString()),
            calories = if (recipeObject.has("calories")) recipeObject.getInt("calories") else null,
            nutritionalInfo = recipeObject.optString("nutritional_info", null.toString()),
            allergens = recipeObject.optString("allergens", null.toString()),
            credits = extractCredits(recipeObject.getJSONArray("credits")), // Extract credits attribute
            instructions = extractInstructions(recipeObject.getJSONArray("instructions")), // Extract instructions attribute
            sections = extractSections(recipeObject.getJSONArray("sections")), // Extract sections attribute
            components = extractComponents(recipeObject.getJSONArray("components")), // Extract components attribute
            tags = extractTags(recipeObject.getJSONArray("tags")), // Extract tags attribute
            topics = extractTopics(recipeObject.getJSONArray("topics")), // Extract topics attribute
            userRatings = extractUserRatings(recipeObject.getJSONArray("userRatings")), // Extract userRatings attribute
                    nutrition = extractNutrition(recipeObject.optJSONObject("nutrition")) // Extract nutrition attribute

        )
    }


    // Other repository functions for database operations, etc.
}
