package com.kubo79.android.wediscover.location

data class Location(
    val id:Int,
    val name:String,
    val short_description:String,
    val image:String,
    val state_id:String,
    val description:String,
    val biodiversity:String,
    val environmental:String,
    val culture:String,
    val archeology:String,
    val history:String,
    val economy:String,
    val sustainable_development:String,
    val demography:String,
    val gastronomy:String,
    val biblio:String,
    val lat:String,
    val lng:String,
    val ext1: String,
    val ext2: String,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String
)