package com.aysmdva.zipasticapi.viewmodel

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aysmdva.zipasticapi.model.CountryDTO
import com.aysmdva.zipasticapi.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class State{
    SUCCESS,ERROR
}

class MainViewModel : ViewModel() {

    val dto = MutableLiveData<CountryDTO>()
    val state = MutableLiveData<State>()

    fun getCountryDTO(zipCode: String, context: Context) {
        RetrofitService.service?.getAPI()?.getCountryDTO(zipCode)
            ?.enqueue(object : Callback<CountryDTO> {
                override fun onResponse(call: Call<CountryDTO>, response: Response<CountryDTO>) {
                    val dto = response.body() as CountryDTO
                    this@MainViewModel.dto.postValue(dto)
                    if (dto == null) this@MainViewModel.state.postValue(State.ERROR)
                    else this@MainViewModel.state.postValue(State.SUCCESS)
                }

                override fun onFailure(call: Call<CountryDTO>, t: Throwable) {
                    Toast.makeText(context, "NOT FOUND!", Toast.LENGTH_LONG).show()
                }

            })
    }
    fun observeDTO() : LiveData<CountryDTO>{
        return dto
    }

    fun observeState () :LiveData<State>{
        return state
    }
}