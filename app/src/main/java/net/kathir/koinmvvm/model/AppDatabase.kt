package net.kathir.koinmvvm.model

import androidx.room.Database
import androidx.room.RoomDatabase
import net.kathir.koinmvvm.model.dao.UserDao
import net.kathir.koinmvvm.model.entity.GithubUser

@Database(entities = [GithubUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    abstract val userDao: UserDao
}