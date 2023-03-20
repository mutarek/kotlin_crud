package com.example.kotlin_crud.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_crud.R
import com.example.kotlin_crud.adapters.TodoAdapter
import com.example.kotlin_crud.data.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllTodo : AppCompatActivity() {
    lateinit var todoRecyclerView: RecyclerView
    private lateinit var todoList: ArrayList<Todo>
    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_todo)
        initize()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoList = arrayListOf()
        db.collection("Todos").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val todo: Todo? = data.toObject(Todo::class.java)
                    if (todo != null) {
                        todoList.add(todo)

                    }
                }
                todoRecyclerView.adapter = TodoAdapter(todoList)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initize() {
        todoRecyclerView = findViewById(R.id.todo_recycler)
    }

}