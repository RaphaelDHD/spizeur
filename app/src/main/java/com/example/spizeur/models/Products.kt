package com.example.spizeur.models

import com.google.gson.annotations.SerializedName

data class Products(@SerializedName("products") var productList: MutableList<Product> ) {
}