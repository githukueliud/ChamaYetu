package com.example.chamayetu.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectsApi {

    @GET("/users/projects")
    suspend fun getAllProjects(): Response<NewProjectsResponse>


    @GET("/users/projects/{id}")
    suspend fun getProjectById(@Path("id") id: Int): Response<NewProjectsResponseItem>

}