package com.example.solutionx.features.login.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.domain.model.User
import dagger.hilt.android.qualifiers.ApplicationContext

private const val  DATABASE_NAME = "user_database"

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

     abstract fun  userDao(): UserDao

     companion object{

          @Volatile
          private var instance: UserDatabase? = null

          fun getInstance ( @ApplicationContext context: Context):UserDatabase{
               return instance?: synchronized(Any()){
                    instance ?: buildDatabase(context).also{ instance = it}
               }
          }

          private  fun buildDatabase(@ApplicationContext context: Context): UserDatabase{
               return Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,
                    DATABASE_NAME).build()
          }
     }

}