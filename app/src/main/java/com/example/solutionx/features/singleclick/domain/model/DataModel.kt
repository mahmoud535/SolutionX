package com.example.solutionx.features.singleclick.domain.model

import android.content.Context
import com.example.solutionx.features.singleclick.data.model.EntityCountries
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import org.json.JSONObject

object DataModel {

    fun getCountries(context: Context): List<EntityCountries> = parseJsonFile(context, "countries.json")

    fun getCurrencies(context: Context): List<EntityCurrencies> = parseJsonFile(context, "currencies.json")

    fun getFilters(context: Context): List<EntityFilter> = parseJsonFile(context, "filters.json")

    private inline fun <reified T> parseJsonFile(
        context: Context, fileName: String
    ): List<T> {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val gson = Gson()
        val jsonObject = JSONObject(jsonString)
        val dataArray = jsonObject.getJSONArray("data")
        val itemType = object : TypeToken<T>() {}.type
        val itemList: MutableList<T> = mutableListOf()

        for (i in 0 until dataArray.length()) {
            val itemJson = dataArray.getJSONObject(i).toString()
            val item: T = gson.fromJson(itemJson, itemType)
            itemList.add(item)
        }

        return itemList
    }
}