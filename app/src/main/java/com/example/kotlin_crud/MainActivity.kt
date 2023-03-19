package com.example.kotlin_crud

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlin_crud.data.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import java.sql.Date
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var desc: EditText
    lateinit var save: Button

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById(R.id.titleCon)
        desc = findViewById(R.id.descCon)
        save = findViewById(R.id.save)

        save.setOnClickListener(View.OnClickListener {
            saveToDb()
        })

    }

    private fun clear() {
        title.text.clear()
        desc.text.clear()
    }

    private fun saveToDb() {
        val dTitle = title.text.toString()
        val dDesc = desc.text.toString()
        if (dTitle.isEmpty() || dDesc.isEmpty()) {
            Toast.makeText(applicationContext, "Empty", Toast.LENGTH_SHORT).show()
        } else {
            var todoMap = hashMapOf(
                "title" to dTitle, "description" to dDesc
            )
            db.collection("Todos").add(todoMap).addOnSuccessListener {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                clear()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }

        }

    }
}