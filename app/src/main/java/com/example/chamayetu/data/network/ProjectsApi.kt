package com.example.chamayetu.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProjectsApi {

    @GET("/user/projects")
    suspend fun getAllProjects(): Response<NewProjectsResponse>


    @GET("/user/projects/{id}")
    suspend fun getProjectById(@Path("id") id: Int): Response<List<NewProjectsResponseItem>>

    @PUT("/admin/project/{id}")
    suspend fun updateProject(@Path("id") id: Int, newProjectsResponse: NewProjectsResponseItem): Response<NewProjectsResponseItem>

    @POST("/admin/project")
    suspend fun createProject(newProjectsResponse: NewProjectsResponseItem): Response<NewProjectsResponseItem>

}