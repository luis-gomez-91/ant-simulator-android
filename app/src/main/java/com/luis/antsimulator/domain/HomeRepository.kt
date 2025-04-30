package com.luis.ant.domain

import com.luis.ant.data.network.LicenceType


interface HomeRepository {
    suspend fun getLicences(): List<LicenceType>
}