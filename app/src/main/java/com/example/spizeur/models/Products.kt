package com.example.spizeur.models

class Products private constructor() {

    var products: ArrayList<Product> = ArrayList()
    private object Holder {
        val INSTANCE = Products()
    }

    companion object {
        val instance: Products by lazy { Holder.INSTANCE }
    }
}
