package ru.netology.nmedia.dto

import java.util.Calendar
import java.util.Date


data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: Date? = Calendar.getInstance().time,
    val likes: Int,
    val shares: Int,
    val views: Int,
    val likedByMe: Boolean,
    val video: String? = null

)
