package com.example.socialneighborhood.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ietiapp.R
import com.example.ietiapp.data.TasksDto

class TasksAdapter (private val  context: Context?,
                    val tasks: List<TasksDto>): RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    class TasksViewHolder (view: View?):RecyclerView.ViewHolder(view!!) {
        val nombre : TextView = view!!.findViewById(R.id.nombre_task)
        val fecha  : TextView = view!!.findViewById(R.id.fecha_task)
        val asignado : TextView = view!!.findViewById(R.id.assigned_to)
        val status: TextView = view!!.findViewById(R.id.status)
        val descripcion  : TextView = view!!.findViewById(R.id.descripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.TasksViewHolder {
        val adapterLayout =  LayoutInflater.from(parent.context).inflate(R.layout.item_tasks,
            parent,false)
        return TasksViewHolder(adapterLayout);
    }

    override fun getItemCount(): Int {
        return tasks.size;
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = tasks[position]
        holder.nombre?.text = item.name
        holder.fecha?.text = item.dueDate
        holder.asignado?.text = item.assignedTo
        holder.status?.text = item.status
        holder.descripcion?.text = item.description
    }
}