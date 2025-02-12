package com.cscorner.cmart

data class DATA(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)