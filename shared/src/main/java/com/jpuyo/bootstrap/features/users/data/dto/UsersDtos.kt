package com.jpuyo.bootstrap.features.users.data.dto

import com.jpuyo.bootstrap.features.users.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    @SerialName("page") val page : Int,
    @SerialName("per_page") val per_page : Int,
    @SerialName("total") val total : Int,
    @SerialName("total_pages") val total_pages : Int,
    @SerialName("data") val data : List<UserDto>
){
    fun toUsersList(): List<User> = data.map { it.toUser() }
}

@Serializable
data class UserDetailResponseDto(
    @SerialName("data") val data : UserDto
){
    fun toUser(): User = data.toUser()
}

@Serializable
data class UserDto (
    @SerialName("id") val id : Int,
    @SerialName("email") val email : String,
    @SerialName("first_name") val first_name : String,
    @SerialName("last_name") val last_name : String,
    @SerialName("avatar") val avatar : String
){
    fun toUser(): User = User(
            id = id.toString(),
            email = email,
            fullName = "$first_name $last_name",
            avatar = avatar
    )
}