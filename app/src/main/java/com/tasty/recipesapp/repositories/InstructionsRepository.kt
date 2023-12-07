package com.tasty.recipesapp.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.activities.Mapping.toModelList
import org.json.JSONObject
import java.io.IOException

class InstructionsRepository : IGenericRepository<InstructionModel> {

    // later, context should be removed
    override fun getAll(context: Context): List<InstructionModel> {
        return readAll(context).toModelList()
    }

    // In the future this should be deleted and data should be fetched from a public API
    private fun readAll(context : Context): List<InstructionDTO> {
        val gson = Gson()
        var instructionList = listOf<InstructionDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("instructions.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            //If there is an extra label
            val jsonObject = JSONObject(jsonString)
            val instructionsArray = jsonObject.getJSONArray("instructions")

            val type = object : TypeToken<List<InstructionDTO>>() {}.type
            //if it is simple
            //val instructionList = gson.fromJson<List<InstructionDTO>>(jsonString, type)
            // if with label
            instructionList = gson.fromJson(instructionsArray.toString(), type)


            Log.i("GSON", instructionList.toString())
            //instructions.value = instructionList
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return instructionList
    }

}