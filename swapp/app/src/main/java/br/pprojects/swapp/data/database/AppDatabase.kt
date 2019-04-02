package br.pprojects.swapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.pprojects.swapp.models.Character

@Database(entities = [Character::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CharacterDao() : CharacterDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

//        fun destroyDataBase(){
//            INSTANCE = null
//        }
    }
}