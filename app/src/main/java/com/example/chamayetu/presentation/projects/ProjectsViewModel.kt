package com.example.chamayetu.presentation.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.network.NewProjectsResponseItem
import com.example.chamayetu.data.network.RetrofitInstance
import com.example.chamayetu.presentation.login.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _projects = MutableStateFlow<List<NewProjectsResponseItem>>(emptyList())
    val projects: StateFlow<List<NewProjectsResponseItem>> = _projects

    private val _userRole = MutableStateFlow("")
    val userRole: StateFlow<String> = _userRole

    private val _userRoles = MutableStateFlow<List<String>>(emptyList())
    val userRoles: StateFlow<List<String>> = _userRoles

    private val projectsApi = RetrofitInstance.projectsApi

    init {
        fetchProjects()
        fetchUserRole()
        viewModelScope.launch {
            userRepository.user.collect { userDto ->
                _userRoles.value = userDto.role.split(",")
            }
        }
    }

    fun hasRole(role: String): Boolean {
        return _userRoles.value.contains(role)
    }

    private fun fetchProjects() {
        viewModelScope.launch {
            try {
                val response = projectsApi.getAllProjects()
                if (response.isSuccessful) {
                    val projects = response.body()

                    Log.d("ProjectsViewModel", "Fetched projects: $projects")
                    _projects.value = response.body() ?: emptyList()

                } else {
                    Log.e("ProjectsViewModel", "API Error: ${response.code()} ${response.errorBody()?.string()}")                }
            } catch (e: Exception) {
                Log.e("ProjectsViewModel", "Network Error: ${e.message}")
            }
        }
    }

    private fun fetchUserRole() {
        viewModelScope.launch {
            try {
                userRepository.user.collect { user ->
                    _userRole.value = user.role
                }
            } catch (e: Exception) {
                Log.e("ProjectsViewModel", "Network Error: ${e.message}")
            }
        }
    }

    fun createProject(project: NewProjectsResponseItem) {
        viewModelScope.launch {
            try {
                val response = projectsApi.createProject(project)
                if (response.isSuccessful) {
                    fetchProjects() // Refresh the project list after creating a new project
                } else {
                    Log.e("ProjectsViewModel", "API Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ProjectsViewModel", "Network Error: ${e.message}")
            }
        }
    }

    fun updateProject(project: NewProjectsResponseItem) {
        viewModelScope.launch {
            try {
                val response = projectsApi.updateProject(project.id, project)
                if (response.isSuccessful) {
                    fetchProjects() // Refresh the project list after updating a project
                } else {
                    Log.e("ProjectsViewModel", "API Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ProjectsViewModel", "Network Error: ${e.message}")
            }
        }
    }
}


