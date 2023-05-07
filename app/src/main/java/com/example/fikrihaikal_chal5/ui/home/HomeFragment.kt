package com.example.fikrihaikal_chal5.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fikrihaikal_chal5.R
import com.example.fikrihaikal_chal5.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mainAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.session()
        initList()
        viewModel.getAllMoviesNowPlaying()
        viewModel.loadingState.observe(viewLifecycleOwner){
            binding.pb.isVisible = it
            binding.rvMovie.isVisible =!it
        }
        viewModel.movie.observe(viewLifecycleOwner) {
            mainAdapter.differ.submitList(it.results)
        }
        viewModel.user.observe(viewLifecycleOwner){
            binding.setName.text = "Hi, ${it?.email}"
        }
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_profileFragment2)
        }

    }

    private fun initList() {
        mainAdapter = HomeAdapter()
        binding.rvMovie.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }



}