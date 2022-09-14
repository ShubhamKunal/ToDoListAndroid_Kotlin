package com.example.roomproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomproject.databinding.FragmentListBinding
import kotlin.collections.ArrayList


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskTable: TaskDao
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context

        taskTable = BaseApplication.getDb(context).taskDao()
        adapter = ListAdapter(this::onItemDelete)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        val taskList = taskTable.getAll()
        adapter.setTaskList(taskList as ArrayList<TaskModel>)

        binding.addTaskButton.setOnClickListener {
            findNavController().navigate(R.id.action_list_fragment_to_createTaskFragment)
        }

        if (BaseApplication.showFirstRunToast(context)) {
            BaseApplication.setFirstRunToast(context, false)
            Toast.makeText(context, "First Launch!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onItemDelete(taskModel: TaskModel) {
        taskTable.delete(taskModel)
        adapter.setTaskList(taskTable.getAll() as ArrayList<TaskModel>)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}