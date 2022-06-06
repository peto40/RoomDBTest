package com.hfad.android.roomdbtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.hfad.android.roomdbtest.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val KEY_COUNT = "key_count"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {

            val userDao = UserDB.getInstance(this@MainActivity).userDao()
            userDao.addUser(UserModel(0,"James","Rodrigue"))
            userDao.addUser(UserModel(0,"Andy","Symon"))
        }

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val user = UserDB.getInstance(this@MainActivity).userDao()
                    .getUserBySurname("Symon").get(0)

                binding.title.setText(user.surname)
            }
        }

    }

    fun getPref(){
        val prefs = getSharedPreferences("new_pref_file", Context.MODE_PRIVATE)
        // val defaultPrefs = getPreferences(Context.MODE_PRIVATE)
        var counter = prefs.getInt(KEY_COUNT, 0)

        binding.title.text = counter.toString()

        Log.d("dsadsad", "asdasdalnsdbuh")

        binding.button.setOnClickListener {
            counter++

            prefs.edit()
                .putInt(KEY_COUNT, counter)
                .putString("some_string", "sadly")
                .putBoolean("boolean", true)
                .apply()
            binding.title.text = counter.toString()
        }
    }
}