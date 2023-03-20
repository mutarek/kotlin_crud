package com.example.kotlin_crud.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_crud.R
import com.example.kotlin_crud.data.model.Todo

class TodoAdapter(private val todoList: ArrayList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dbTitle: TextView = itemView.findViewById(R.id.todo_title)
        val dbDesc: TextView = itemView.findViewById(R.id.todo_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_todo_view, parent, false)
        return TodoViewHolder(item)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.dbTitle.text = todoList[position].title
        holder.dbDesc.text = todoList[position].description
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}