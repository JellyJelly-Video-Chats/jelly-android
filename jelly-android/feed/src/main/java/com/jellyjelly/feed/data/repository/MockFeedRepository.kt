package com.jellyjelly.feed.data.repository

import com.jellyjelly.feed.domain.model.MediaType
import com.jellyjelly.feed.domain.model.Post
import com.jellyjelly.feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

class MockFeedRepository @Inject constructor() : FeedRepository {
    private val posts = MutableStateFlow<List<Post>>(emptyList())
    private val postsFlow = posts.asStateFlow()

    init {
        // Add some sample posts
        posts.value = listOf(
            Post(
                id = "1",
                userId = "user1",
                userName = "John Doe",
                userPhotoUrl = null,
                content = "Welcome to JellyJelly! This is a sample post.",
                mediaUrl = null,
                mediaType = null,
                likes = 42,
                comments = 12,
                createdAt = Date()
            ),
            Post(
                id = "2",
                userId = "user2",
                userName = "Jane Smith",
                userPhotoUrl = null,
                content = "Another sample post with an image",
                mediaUrl = "https://example.com/image.jpg",
                mediaType = MediaType.IMAGE,
                likes = 15,
                comments = 5,
                createdAt = Date(System.currentTimeMillis() - 3600000) // 1 hour ago
            )
        )
    }

    override fun getPosts(): Flow<List<Post>> = postsFlow

    override suspend fun createPost(
        content: String,
        mediaUrl: String?,
        mediaType: MediaType?
    ): Result<Post> = try {
        val post = Post(
            id = System.currentTimeMillis().toString(),
            userId = "currentUserId",
            userName = "Current User",
            userPhotoUrl = null,
            content = content,
            mediaUrl = mediaUrl,
            mediaType = mediaType,
            likes = 0,
            comments = 0,
            createdAt = Date()
        )
        posts.value = posts.value + post
        Result.success(post)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun likePost(postId: String): Result<Unit> = try {
        posts.value = posts.value.map { post ->
            if (post.id == postId) post.copy(likes = post.likes + 1) else post
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun unlikePost(postId: String): Result<Unit> = try {
        posts.value = posts.value.map { post ->
            if (post.id == postId) post.copy(likes = (post.likes - 1).coerceAtLeast(0)) else post
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deletePost(postId: String): Result<Unit> = try {
        posts.value = posts.value.filter { it.id != postId }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
} 