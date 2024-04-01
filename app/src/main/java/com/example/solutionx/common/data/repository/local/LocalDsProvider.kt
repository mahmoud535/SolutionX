package com.example.solutionx.common.data.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.common.domain.repository.loca.ILocalDSProvider
import com.example.solutionx.features.login.data.local.UserPreferences
import com.example.solutionx.features.login.presentation.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

internal class LocalDsProvider(private val context: Context) : ILocalDSProvider {


    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        Constants.DS_File
    )

    override suspend fun <T> save(key: String, value: T) {
        val preferencesKey = stringPreferencesKey(key)
        context.preferencesDataStore.edit { preferences ->
            when (value) {
                is String -> preferences[stringPreferencesKey(key)] = value
                is Boolean -> preferences[booleanPreferencesKey(key)] = value
                is Int -> preferences[intPreferencesKey(key)] = value
                is Float -> preferences[floatPreferencesKey(key)] = value
                is Long -> preferences[longPreferencesKey(key)] = value
            }
        }
    }

    override suspend fun <T> get(key: String): T? {
        return try {
            context.preferencesDataStore.data.map { preferences ->
                preferences[stringPreferencesKey(key)] ?:
                preferences[booleanPreferencesKey(key)] ?:
                preferences[intPreferencesKey(key)] ?:
                preferences[longPreferencesKey(key)] ?:
                preferences[floatPreferencesKey(key)]
            }.firstOrNull() as T? ?: throw IllegalStateException("the key $key not found")
        } catch (e: Throwable) {
            null
        }
    }

}