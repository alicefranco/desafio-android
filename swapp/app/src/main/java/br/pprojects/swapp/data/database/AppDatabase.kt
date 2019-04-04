package br.pprojects.swapp.data.database

import android.arch.persistence.room.*
import android.content.Context
import br.pprojects.swapp.models.*

@Database(entities = [Character::class, Species::class, Planet::class], version = 11)
@TypeConverters(Converters::class)
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