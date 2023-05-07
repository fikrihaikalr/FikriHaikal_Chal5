package com.example.fikrihaikal_chal5.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fikrihaikal_chal5.R
import com.example.fikrihaikal_chal5.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.session()
        viewModel.user.observe(viewLifecycleOwner){
            binding.apply {
                etEmail.setText(it?.email.toString())
            }
        }
        binding.apply {
            btnUpdate.setOnClickListener {
                val email = etEmail.text.toString().trim()
                viewModel.updateEmail(email)
                viewModel.update.observe(viewLifecycleOwner){
                    Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.profileFragment2)
                }
            }
            btnLogout.setOnClickListener {
                Firebase.auth.signOut()
                findNavController().navigate(R.id.action_profileFragment2_to_loginFragment)
            }
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment2_to_homeFragment2)
            }
        }
    }

}