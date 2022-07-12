package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 999,
    val likedByMe: Boolean = false,
    val shares: Int = 1099
)
