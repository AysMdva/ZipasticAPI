package com.aysmdva.zipasticapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.aysmdva.zipasticapi.databinding.ActivityMainBinding
import com.aysmdva.zipasticapi.viewmodel.MainViewModel
import com.aysmdva.zipasticapi.viewmodel.State

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel :MainViewModel
    lateinit var progressBar :ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        progressBar = binding.progressbar

        binding.button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.getCountryDTO(binding.zipCode.text.toString(),this)
        }


        viewModel.observeState().observe(this,{state ->
            when(state){
                State.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    binding.countryName.text = viewModel.observeDTO().value?.countryName
                    binding.countryState.text = viewModel.observeDTO().value?.countryState
                    binding.countryCity.text = viewModel.observeDTO().value?.countryCity
                }
                State.ERROR -> {
//                    AlertDialog.Builder(this).setMessage("NOT FOUND ANY COUNTRY,ENTER NEW ONE").create().show()

                }

            }

        })
    }
}