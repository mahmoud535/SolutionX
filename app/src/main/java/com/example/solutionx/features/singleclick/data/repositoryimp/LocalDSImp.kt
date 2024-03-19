//package com.example.solutionx.features.singleclick.data.repositoryimp
//
//import android.content.Context
//import com.example.solutionx.features.singleclick.domain.model.Countries
//import com.example.solutionx.features.singleclick.domain.model.Currencies
//import com.example.solutionx.features.singleclick.domain.model.Filter
//import com.example.solutionx.features.singleclick.domain.repository.Repository
//import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
//import com.google.gson.Gson
//import org.json.JSONObject
//import javax.inject.Inject
//
//class LocalDSImp @Inject constructor()  :Repository{
//    override fun getAllCountries(context: Context): List<Countries> =
//        parseJsonFile(context, "countries.json")
//
//
//    override fun getAllCurrencies(context: Context): List<Currencies> =
//        parseJsonFile(context, "currencies.json")
//
//
//    override fun getAllFilters(context: Context): List<Filter> =
//        parseJsonFile(context, "filters.json")
//
//
//    private inline fun <reified T> parseJsonFile(
//        context: Context, fileName: String
//    ): List<T> {
//        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
//        val gson = Gson()
//        val jsonObject = JSONObject(jsonString)
//        val dataArray = jsonObject.getJSONArray("data")
//        val itemType = object : TypeToken<T>() {}.type
//        val itemList: MutableList<T> = mutableListOf()
//
//        for (i in 0 until dataArray.length()) {
//            val itemJson = dataArray.getJSONObject(i).toString()
//            val item: T = gson.fromJson(itemJson, itemType)
//            itemList.add(item)
//        }
//
//        return itemList
//    }
//}