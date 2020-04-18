package net.kathir.koinmvvm.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class GithubUser(
    @PrimaryKey val id: Long,
    val login: String,
    val avatar_url: String,
    var node_id: String,
    val type : String
)