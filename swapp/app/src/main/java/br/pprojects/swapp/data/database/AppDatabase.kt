package br.pprojects.swapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.pprojects.swapp.models.*

@Database(entities = [Character::class, Species::class, Planet::class], version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
    abstract fun planetDao() : PlanetDao
    abstract fun speciesDao() : SpeciesDao

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