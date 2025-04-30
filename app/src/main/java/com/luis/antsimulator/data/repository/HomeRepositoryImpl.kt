package com.luis.ant.data.repository

import com.luis.ant.data.network.LicenceType
import com.luis.ant.domain.HomeRepository
import com.luis.ant.screens.home.HomeService
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeService
) : HomeRepository {
    override suspend fun getLicences(): List<LicenceType> = api.getLicences()
}