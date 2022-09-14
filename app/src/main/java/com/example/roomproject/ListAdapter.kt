package com.example.roomproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.databinding.TaskItemBinding


class ListAdapter(
    private val onDeleteClickListener: (TaskModel) -> Unit
) : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {

    private val tasks = ArrayList<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding, onDeleteClickListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun setTaskList(list: ArrayList<TaskModel>) {
        tasks.clear()
        tasks.addAll(list.sortedBy { -it.priority })
        notifyDataSetChanged()
    }

    class TaskViewHolder(
        private val binding: TaskItemBinding,
        private val onDeleteClickListener: (TaskModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TaskModel) {
            binding.apply {
                deleteButton.setOnClickListener {
                    onDeleteClickListener(model)
                }
                textViewTitle.text = "P-${model.priority} | ${model.title}"
                textViewBody.text = model.body
            }
        }

    }

}