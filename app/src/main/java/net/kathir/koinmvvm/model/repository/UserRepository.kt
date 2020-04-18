package net.kathir.koinmvvm.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.kathir.koinmvvm.model.api.UserApi
import net.kathir.koinmvvm.model.dao.UserDao

class UserRepository(private val userApi: UserApi,private val userDao: UserDao) {

    val data =  userDao.findAll()

    suspend fun refresh()
    {
        withContext(Dispatchers.IO)
        {
            val users = userApi.getAllAsync().await()
            userDao.add(users)
        }
    }
}