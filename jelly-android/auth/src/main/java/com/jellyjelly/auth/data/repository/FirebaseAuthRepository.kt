package com.jellyjelly.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jellyjelly.auth.domain.model.User
import com.jellyjelly.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override val currentUser: Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toUser())
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun signIn(email: String, password: String): Result<User> = try {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        Result.success(result.user?.toUser() ?: throw IllegalStateException("User is null"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun signUp(email: String, password: String): Result<User> = try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        Result.success(result.user?.toUser() ?: throw IllegalStateException("User is null"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun resetPassword(email: String): Result<Unit> = try {
        auth.sendPasswordResetEmail(email).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateProfile(displayName: String?, photoUrl: String?): Result<User> = try {
        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .setPhotoUri(photoUrl?.let { android.net.Uri.parse(it) })
            .build()
        
        auth.currentUser?.updateProfile(profileUpdates)?.await()
        Result.success(auth.currentUser?.toUser() ?: throw IllegalStateException("User is null"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    private fun FirebaseUser.toUser() = User(
        id = uid,
        email = email ?: "",
        displayName = displayName,
        photoUrl = photoUrl?.toString()
    )
} 