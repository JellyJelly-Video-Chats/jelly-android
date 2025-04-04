package com.jellyjelly.feed.domain.model

import java.util.Date

data class Post(
    val id: String,
    val userId: String,
    val userName: String,
    val userPhotoUrl: String?,
    val content: String,
    val mediaUrl: String?,
    val mediaType: MediaType?,
    val likes: Int = 0,
    val comments: Int = 0,
    val createdAt: Date = Date(),
    val isLiked: Boolean = false
)

enum class MediaType {
    IMAGE,
    VIDEO,
    AUDIO
} 