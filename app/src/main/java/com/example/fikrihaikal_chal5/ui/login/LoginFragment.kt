package com.example.fikrihaikal_chal5.ui.login

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fikrihaikal_chal5.R
import com.example.fikrihaikal_chal5.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel session berfungsi untuk jika akun sudah login dia ke home, dan jika belum dia tetap ke login
        viewModel.session()

        //logika untuk menentukan akun sudah login atau belum, jika akun sudah login nav ke home, jika belum tetap di forom login
        viewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            }
        }

        binding.tvDaftar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = R.string.emailRequired.toString()
                binding.etEmail.requestFocus()
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etPassword.error = R.string.invalidEmail.toString()
                binding.etPassword.requestFocus()
            }
            if (password.isEmpty()) {
                binding.etPassword.error = R.string.passwordIsRequired.toString()
                binding.etPassword.requestFocus()
            }
            if (!binding.outlinedTextField.isErrorEnabled && !binding.outlinedTextField2.isErrorEnabled) {
                viewModel.loginFirebase(email, password)
            }
        }
        binding.gantiBhs.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        viewModel.login.observe(viewLifecycleOwner) {
            if (it.equals("Login Success!", true)) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
            } else {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }


}