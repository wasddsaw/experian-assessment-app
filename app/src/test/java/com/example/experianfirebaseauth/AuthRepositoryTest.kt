package com.example.experianfirebaseauth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.experianfirebaseauth.repository.AuthRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class AuthRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var authResult: AuthResult

    @Mock
    private lateinit var authResultTask: Task<AuthResult>

    @Mock
    private lateinit var observer: Observer<String>

    private lateinit var authRepository: AuthRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        authRepository = AuthRepository(firebaseAuth)
        authRepository.loginStatus.observeForever(observer)
    }


    @Test
    fun `login success updates loginStatus with success message`() {
        // Arrange
        val email = "test@example.com"
        val password = "password"

        doAnswer { invocation ->
            val onCompleteListener = invocation.getArgument<OnCompleteListener<AuthResult>>(0)
            `when`(authResultTask.isSuccessful).thenReturn(true)
            onCompleteListener.onComplete(authResultTask)
            null
        }.`when`(authResultTask).addOnCompleteListener(any())

        `when`(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(authResultTask)


        //whenever(authResultTask.isSuccessful).thenReturn(true)
        //whenever(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(authResultTask)

//        `when`(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(
//            Tasks.forResult(authResult)
//        )

        // Act
        authRepository.login(email, password)
//        authRepository.loginStatus.observeForever(observer)

        // Assert
        verify(observer).onChanged("Login Successful")
    }

    @Test
    fun `login failure updates loginStatus with error message`() {
        // Arrange
        val email = "test@example.com"
        val password = "password"
        val exceptionMessage = "Login Failed"

        doAnswer { invocation ->
            val onCompleteListener = invocation.getArgument<OnCompleteListener<AuthResult>>(0)
            `when`(authResultTask.isSuccessful).thenReturn(false)
            `when`(authResultTask.exception).thenReturn(Exception(exceptionMessage))
            onCompleteListener.onComplete(authResultTask)
            null
        }.`when`(authResultTask).addOnCompleteListener(any())

        `when`(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(authResultTask)


//        whenever(authResultTask.isSuccessful).thenReturn(false)
//        whenever(authResultTask.exception).thenReturn(Exception("Login Failed"))
//        whenever(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(authResultTask)

//        val exception = FirebaseAuthInvalidCredentialsException("ERROR", "Invalid credentials")
//        `when`(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(
//            Tasks.forException(exception)
//        )

        // Act
        authRepository.login(email, password)
        //authRepository.loginStatus.observeForever(observer)

        // Assert
        verify(observer).onChanged(exceptionMessage)
    }
}
