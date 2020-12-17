package com.liao.loginunittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import com.liao.loginunittesting.view_model.LoginViewModel
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginViewModelUnitTest {
    lateinit var loginViewModel: LoginViewModel
    @Before
    fun init(){
        loginViewModel = LoginViewModel()
    }
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getLiveData_test() {
        loginViewModel.getLiveData().value = "test"
        var res = ""
        loginViewModel.getLiveData().observeForever {
            res = it
        }
        assertEquals("test",res)
    }

    @Test
    fun loginButtonClicked_withCorrectNamePassword_test() {
        loginViewModel.loginButtonClicked("admin","admin")
        var res = ""
        loginViewModel.getLiveData().observeForever {
            res = it
        }
        assertEquals("Success",res)
    }

    @Test
    fun loginButtonClicked_withIncorrectNamePassword_test() {
        loginViewModel.loginButtonClicked("123","123")
        var res = ""
        loginViewModel.getLiveData().observeForever {
            res = it
        }
        assertEquals("Wrong name or password",res)
    }


}