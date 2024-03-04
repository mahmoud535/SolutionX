package com.example.solutionx

import com.example.solutionx.model.Countries
import com.example.solutionx.model.Currencies
import com.example.solutionx.model.Filter

class DataModel {
   companion object{
       val currencyList = listOf(
           Currencies("2211", 1, "American Dollar", "$"),
           Currencies("1511", 2, "Euro", "LE"),
           Currencies("1655", 3, "Pound", "SAR"),
           Currencies("1651651", 4, "Riyal", "AED"),
       )
       val countryList = listOf(
           Countries("sa", "SAR", "USA", 1, "Saudi Arabia", "00966"),
           Countries("eg", "EGP", "AUR", 2, "Egypt", "5065"),
           Countries("ef", "AFN", "AGP", 3, "Afghanistan", "412"),
           Countries("al", "ALL", "VVM", 4, "Albania", "47"),
       )
       val filterList = listOf(
           Filter(1, "filter by country"),
           Filter(2, "currency"),
           Filter(3, "Custom Filter"),
           Filter(4, "Filter by all"),
       )
   }


}