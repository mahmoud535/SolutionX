package com.example.solutionx.features.login.data.local

import am.leon.solutionx.android.extentions.toJson
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.presentation.util.Constants
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

internal class UserPreferences (
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    val accessToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            preferences[Constants.ACCESS_TOKEN]
        }


    suspend fun getUser(): UserEntity {
        //read the data
        val userGson = context.dataStore.data.map {
            it[Constants.USER_KEY]
        }.first()
        val gson = Gson()
        return gson.fromJson(userGson, UserEntity::class.java)
    }

    suspend fun saveUserData(login: LoginEntity) {
        context.dataStore.edit { preferences ->
            preferences[Constants.ACCESS_TOKEN] = login.token
            preferences[Constants.USER_KEY] = gson.toJson(login.user)
        }
    }

}