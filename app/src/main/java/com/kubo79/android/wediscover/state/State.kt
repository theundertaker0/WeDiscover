package com.kubo79.android.wediscover.state

data class State(
    val id:Int,
    val name:String,
    val short_name:String,
    val image:String,
    val description:String,
    val biosecurity:String,
    val weather:String,
    val police_number:String,
    val firemen_number:String,
    val medical_number:String,
    val government_number:String,
    val lat:String,
    val lng:String,
    val ext1: String,
    val ext2: String,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String
)