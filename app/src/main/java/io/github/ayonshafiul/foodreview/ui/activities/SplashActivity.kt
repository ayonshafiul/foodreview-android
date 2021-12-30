package io.github.ayonshafiul.foodreview.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.SplashViewModel
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs : SharedPreferences
    lateinit var token: String
    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPrefs = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        token = sharedPrefs.getString(getString(R.string.token_key), "")!!
        viewModel = ViewModelProvider(this, Instances.splashFactory).get(SplashViewModel::class.java)
        if(token == "") {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        } else {
            viewModel.checkAuthenticated(token)
            viewModel.msgResponse.observe(this) {
                Log.d("Response", "onCreate: " + it.toString())
                Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
            }
        }

    }


}

//CoroutineScope(Dispatchers.IO).launch {
//    delay(3000)
//    notifyMainThread()
//}

//suspend fun notifyMainThread() {
//    CoroutineScope(Dispatchers.Main).launch {
//        finish()
//    }
//}