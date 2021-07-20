package com.beautycenter.android.presentation.models

import com.google.gson.annotations.SerializedName

data class Service (
    val id: Int?,
    @SerializedName("service_category_id")
    val serviceCategoryId:Int?,
    val name: String?,
    val duration: String?,
    val image: String?,
    val created_at: String?,
    val updated_at: String?
)