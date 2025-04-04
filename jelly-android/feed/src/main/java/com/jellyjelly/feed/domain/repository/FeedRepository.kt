package com.jellyjelly.feed.domain.repository

import com.jellyjelly.feed.domain.model.MediaType
import com.jellyjelly.feed.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getPosts(): Flow<List<Post>>
    suspend fun createPost(content: String, mediaUrl: String?, mediaType: MediaType?): Result<Post>
    suspend fun likePost(postId: String): Result<Unit>
    suspend fun unlikePost(postId: String): Result<Unit>
    suspend fun deletePost(postId: String): Result<Unit>
} 