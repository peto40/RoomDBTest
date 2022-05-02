package com.hfad.android.roomdbtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.hfad.android.roomdbtest.databinding.ActivityMainBinding

const val KEY_COUNT = "key_count"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        val prefs = getSharedPreferences("new_pref_file", Context.MODE_PRIVATE)
       // val defaultPrefs = getPreferences(Context.MODE_PRIVATE)
        var counter = prefs.getInt(KEY_COUNT, 0)

        binding.title.text = counter.toString()

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