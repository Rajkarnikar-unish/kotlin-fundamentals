package com.example.myfirstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


    }
}

/**
 * Passing data from activities using INTENT
 *
 *     val name = intent.getStringExtra("EXTRA_NAME")
 *     val age = intent.getIntExtra("EXTRA_AGE", 0)
 *     val country = intent.getStringExtra("EXTRA_COUNTRY")
 *
 *     val person = intent.getSerializableExtra("EXTRA_PERSON") as Person
 *
 *     tvPerson.text = "I am ${person.name} and am ${person.age} years old from ${person.country}."
 */

/**
 * RETURN FROM INTENT
 * btnReturn.setOnClickListener {
 * finish()
 * }
 */