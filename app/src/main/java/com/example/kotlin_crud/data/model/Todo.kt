package com.example.kotlin_crud.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Todo(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
)
