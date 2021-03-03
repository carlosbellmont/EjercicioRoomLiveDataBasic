package com.cbellmont.ejercicioandroid15

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Personaje::class, Mascota::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun personajesDao(): PersonajeDao
    abstract fun mascotaDao(): MascotaDao

    companion object {

        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return INSTANCE as Db
                }
                val roomBuilder =
                    Room.databaseBuilder(context.applicationContext, Db::class.java, "database-db")
                roomBuilder.addCallback(getCallback())
                INSTANCE = roomBuilder.build()
                return INSTANCE as Db
            }
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val personajes: List<Personaje> = listOf(
                            Personaje("Aragorn", "Humano", R.mipmap.aragorn, true),
                            Personaje("Gandalf", "Mago", R.mipmap.gandalf, true),
                            Personaje("Boromir", "Humano", R.mipmap.boromir, true),
                            Personaje("Legolas", "Elfo", R.mipmap.legolas, true),
                            Personaje("Orco Feo", "Orco", R.mipmap.orco, false),
                            Personaje("Smagu", "Dragon", R.mipmap.smagu, false)
                        )
                        INSTANCE?.personajesDao()?.insertAll(personajes)

                        val mascota = Mascota("M1", "Perro", 4)
                        INSTANCE?.mascotaDao()?.insert(mascota)
                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
}