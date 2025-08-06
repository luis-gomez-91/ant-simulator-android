package com.luis.ant.screens.home

import com.luis.ant.data.network.LicenceType
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("licences/")
    suspend fun getLicences(): List<LicenceType>
}