package com.example.roomproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roomproject.databinding.FragmentCreateTaskBinding

class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context
        binding.buttonAddTask.setOnClickListener {
            if (binding.etBody.text.isNotBlank() && binding.etTitle.text.isNotBlank()) {
                val priority = binding.etPriority.text.toString()
                val newTask = TaskModel(
                    id = 0,
                    title = binding.etTitle.text.toString(),
                    body = binding.etBody.text.toString(),
                    priority = if (priority.isBlank()) 0 else priority.toInt()
                )
                BaseApplication.getDb(context).taskDao().insert(newTask)
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(context, "Invalid Data Format", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}