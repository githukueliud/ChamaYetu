package com.example.chamayetu.data.network

data class NewProjectsResponseItem(
    val description: String,
    val endDate: String,
    val id: Int,
    val name: String,
    val projectConsumption: Int,
    val startDate: String
)