package com.example.solutionx.common.data.repository.local.localds

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
import com.example.solutionx.R
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.common.domain.repository.loca.localds.IStorageKeyEnum
import com.example.solutionx.features.login.presentation.util.Constants
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class LocalDsProvider(private val context: Context) : ILocalDSProvider {


    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
      name =  Constants.DS_File
    )

    override suspend fun <Model> save(key: IStorageKeyEnum, data: Model) {
        context.dataStore.edit {
            when (data) {
                is String -> it[stringPreferencesKey(key.keyValue)] = data
                is Int -> it[intPreferencesKey(key.keyValue)] = data
                is Boolean -> it[booleanPreferencesKey(key.keyValue)] = data
                is Float -> it[floatPreferencesKey(key.keyValue)] = data
                is Long -> it[longPreferencesKey(key.keyValue)] = data
                else -> throw LeonException.Local.IOOperation(R.string.unsupported_type)
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    override suspend fun <Model> get(key: IStorageKeyEnum, defaultValue: Model): Model {
        return when (defaultValue) {
            is String -> (context.dataStore.data.map { it[stringPreferencesKey(key.keyValue)] }
                .firstOrNull() ?: defaultValue) as Model

            is Int -> (context.dataStore.data.map { it[intPreferencesKey(key.keyValue)] }
                .firstOrNull() ?: defaultValue) as Model

            is Boolean -> (context.dataStore.data.map { it[booleanPreferencesKey(key.keyValue)] }
                .firstOrNull() ?: defaultValue) as Model

            is Float -> (context.dataStore.data.map { it[floatPreferencesKey(key.keyValue)] }
                .firstOrNull() ?: defaultValue) as Model

            is Long -> (context.dataStore.data.map { it[longPreferencesKey(key.keyValue)] }
                .firstOrNull() ?: defaultValue) as Model

            else -> throw LeonException.Local.IOOperation(R.string.unsupported_type)
        }
    }

}