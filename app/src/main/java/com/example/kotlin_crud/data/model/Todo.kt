package com.example.kotlin_crud.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Todo(
    val id: String,
    val title: String,
    val description: String,
    @ServerTimestamp
    val date: Date
)
