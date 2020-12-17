package com.liao.loginunittesting.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.liao.loginunittesting.R
import com.liao.loginunittesting.view_model.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var callbackManager:CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        init()
    }

    private fun init() {
        button.setOnClickListener {
            val name:String = edit_text_name.text.toString()
            val password:String = edit_text_password.text.toString()
            loginViewModel.loginButtonClicked(name, password)
        }

        loginViewModel.getLiveData().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        callbackManager = CallbackManager.Factory.create();
//        val EMAIL = "email"
//        val loginButton = findViewById<View>(R.id.login_button) as LoginButton
//        loginButton.setReadPermissions(listOf(EMAIL))
//        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
//            override fun onSuccess(loginResult: LoginResult?) {
//                // App code
//                Toast.makeText(this@LoginActivity,"success",Toast.LENGTH_SHORT).show()
//                Log.d("LiaoTag","success")
//            }
//
//            override fun onCancel() {
//                // App code
//                Toast.makeText(this@LoginActivity,"cancel",Toast.LENGTH_SHORT).show()
//                Log.d("LiaoTag","cancel")
//            }
//
//            override fun onError(exception: FacebookException) {
//                // App code
//                Toast.makeText(this@LoginActivity,"error",Toast.LENGTH_SHORT).show()
//                Log.d("LiaoTag","error")
//            }
//        })
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    // App code
                    Toast.makeText(this@LoginActivity,loginResult!!.accessToken.userId.toString(),Toast.LENGTH_SHORT).show()
                Log.d("LiaoTag",loginResult!!.accessToken.userId.toString())
                }

                override fun onCancel() {
                    // App code
                    Toast.makeText(this@LoginActivity,"cancel",Toast.LENGTH_SHORT).show()
                Log.d("LiaoTag","cancel")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    Toast.makeText(this@LoginActivity,"error",Toast.LENGTH_SHORT).show()
                Log.d("LiaoTag","error")
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }
}