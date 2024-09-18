package com.example.mobileapplication.network

import com.example.mobileapplication.model.RMCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface RickAndMortyApi {

    @GET("character/{id}")
    fun getCharacter(@Path("id") characterId: Int): Call<RMCharacter>
}
