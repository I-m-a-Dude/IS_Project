package com.example.project_is.ui.lockscreen.lockscreen_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project_is.databinding.FragmentHomeBinding
import com.example.project_is.databinding.LockscreenMainBinding
import com.example.project_is.R


class MainFragment : Fragment(){

    private var _binding: LockscreenMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout for this fragment
        return inflater.inflate(R.layout.lockscreen_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup any UI interactions or event handling
        binding.signin.setOnClickListener {
            // Handle the sign-in button click
            // Replace with actual IDs from your layout
            val enteredEmail = binding.editEmail.text.toString()
            val enteredPassword = binding.editPassword.text.toString()


            // Perform sign-in logic here, e.g., validate credentials
            // For simplicity, let's just print the entered email and password for now
            println("Entered Email: $enteredEmail")
            println("Entered Password: $enteredPassword")
        }


        binding.register.setOnClickListener {
            // Handle the register button click
            // Perform registration logic here
        }
    }
}