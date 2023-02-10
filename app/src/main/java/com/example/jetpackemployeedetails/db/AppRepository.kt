package com.example.jetpackemployeedetails.db

import com.example.jetpackemployeedetails.data.ApiInterface

class AppRepository(private val apiInterface: ApiInterface) {

    suspend fun fetchUserDetails() = apiInterface.fetchUserDetails()
}