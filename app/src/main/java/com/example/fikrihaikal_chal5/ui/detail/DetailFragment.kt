package com.example.fikrihaikal_chal5.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.fikrihaikal_chal5.R
import com.example.fikrihaikal_chal5.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMovie(arguments?.getInt("MOVIE_ID")!!)
        bind()
    }

    private fun bind() {
        viewModel.loadingState.observe(viewLifecycleOwner){
            binding.pb.isVisible = it
            binding.name.isVisible = !it
            binding.photo.isVisible = !it
            binding.backdrop.isVisible = !it
            binding.desc.isVisible = !it
        }

        viewModel.movie.observe(viewLifecycleOwner){
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            Glide.with(requireContext()).load(imageUrl + it.backdropPath).into(binding.backdrop)
            Glide.with(requireContext()).load(imageUrl + it.posterPath).into(binding.photo)
            binding.name.text = it.title.toString()
            binding.desc.text = it.overview.toString()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment2_to_homeFragment2)
        }
    }


}