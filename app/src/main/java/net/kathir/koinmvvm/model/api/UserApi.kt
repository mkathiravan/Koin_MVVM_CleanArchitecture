package net.kathir.koinmvvm.model.api

import kotlinx.coroutines.Deferred
import net.kathir.koinmvvm.model.entity.GithubUser
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllAsync(): Deferred<List<GithubUser>>
}