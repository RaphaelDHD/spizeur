package com.example.spizeur.models

import com.google.gson.annotations.SerializedName

data class Products(@SerializedName("products") val productList: List<Product> ) {

}