package com.beautycenter.android.models

import com.google.gson.annotations.SerializedName

class Service (
    val id: Int?,
    @SerializedName("service_category_id")
    val serviceCategoryId:Int?,
    val name: String?,
    val duration: String?,
    val image: String?,
    val created_at: String?,
    val updated_at: String?
)