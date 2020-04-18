package net.kathir.koinmvvm.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.kathir.koinmvvm.model.entity.GithubUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun findAll(): LiveData<List<GithubUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<GithubUser>)
}