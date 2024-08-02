package com.example.chamayetu.presentation.projects

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chamayetu.data.network.NewProjectsResponseItem




val dummyProjects = listOf(
    NewProjectsResponseItem(
        description = "Project 1 Description",
        endDate = "2023-12-31",
        id = 1,
        name = "Project 1",
        projectConsumption = 100,
        startDate = "2023-01-01"
    ),
    NewProjectsResponseItem(
        description = "Project 2 Description",
        endDate = "2024-06-30",
        id = 2,
        name = "Project 2",
        projectConsumption = 200,
        startDate = "2023-07-01"
    )
)



@Composable
fun ProjectsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = hiltViewModel()
) {
    val userRoles by viewModel.userRoles.collectAsState()
    val projects by viewModel.projects.collectAsState(initial = emptyList())
    //val userRole by viewModel.userRole.collectAsState(initial = "")
    val context = LocalContext.current
    var showCreateDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var selectedProject by remember { mutableStateOf<NewProjectsResponseItem?>(null) }




    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Projects", style = MaterialTheme.typography.bodyMedium)

        if (projects.isEmpty()) {
            Text("No projects available.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(projects) { project ->
                    ProjectItem(project, onUpdateClick = {
                        selectedProject = project
                        showUpdateDialog = true
                    })
                }
            }
        }

        // Debugging
        Log.d("ProjectsScreen", "User roles: ${userRoles.joinToString()}")
        Log.d("ProjectsScreen", "Has ADMIN role: ${viewModel.hasRole("ADMIN")}")

        if (viewModel.hasRole("ADMIN")) {
            Button(onClick = { showCreateDialog = true }) {
                Text("Add Project")
            }
        }

//        if (userRole == "ADMIN" || userRole == "USER") {
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    showCreateDialog = true
//                }
//            ) {
//                Text("Create a new project")
//            }
//        }
    }

    if (viewModel.hasRole("ADMIN")) {
        if (showCreateDialog) {
            CreateProjectDialog(onDismiss = { showCreateDialog = false }) { project ->
                viewModel.createProject(project)
                showCreateDialog = false
            }
        }

        if (showUpdateDialog && selectedProject != null) {
            UpdateProjectDialog(
                project = selectedProject!!,
                onDismiss = { showUpdateDialog = false }) { project ->
                viewModel.updateProject(project)
                showUpdateDialog = false
            }
        }
    }

//    if (showCreateDialog) {
//        CreateProjectDialog(onDismiss = { showCreateDialog = false }) { project ->
//            viewModel.createProject(project)
//            showCreateDialog = false
//        }
//    }
//
//    if (showUpdateDialog && selectedProject != null) {
//        UpdateProjectDialog(project = selectedProject!!, onDismiss = { showUpdateDialog = false }) { project ->
//            viewModel.updateProject(project)
//            showUpdateDialog = false
//        }
//    }
}

@Composable
fun ProjectItem(project: NewProjectsResponseItem, onUpdateClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onUpdateClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.name, style = MaterialTheme.typography.displayMedium)
            Text(text = "Description: ${project.description}", style = MaterialTheme.typography.displayMedium)
            Text(text = "Start Date: ${project.startDate}", style = MaterialTheme.typography.displayMedium)
            Text(text = "End Date: ${project.endDate}", style = MaterialTheme.typography.displayMedium)
            Text(text = "Consumption: ${project.projectConsumption}", style = MaterialTheme.typography.displayMedium)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProjectDialog(project: NewProjectsResponseItem, onDismiss: () -> Unit, onUpdateProject: (NewProjectsResponseItem) -> Unit) {
    var projectName by remember { mutableStateOf(project.name) }
    var projectDescription by remember { mutableStateOf(project.description) }
    var projectStartDate by remember { mutableStateOf(project.startDate) }
    var projectEndDate by remember { mutableStateOf(project.endDate) }
    var projectConsumption by remember { mutableStateOf(project.projectConsumption.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Update Project") },
        text = {
            Column {
                TextField(
                    value = projectName,
                    onValueChange = { projectName = it },
                    label = { Text("Project Name") }
                )
                TextField(
                    value = projectDescription,
                    onValueChange = { projectDescription = it },
                    label = { Text("Project Description") }
                )
                TextField(
                    value = projectStartDate,
                    onValueChange = { projectStartDate = it },
                    label = { Text("Start Date") }
                )
                TextField(
                    value = projectEndDate,
                    onValueChange = { projectEndDate = it },
                    label = { Text("End Date") }
                )
                TextField(
                    value = projectConsumption,
                    onValueChange = { projectConsumption = it },
                    label = { Text("Project Consumption") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedProject = project.copy(
                    name = projectName,
                    description = projectDescription,
                    startDate = projectStartDate,
                    endDate = projectEndDate,
                    projectConsumption = projectConsumption.toIntOrNull() ?: project.projectConsumption
                )
                onUpdateProject(updatedProject)
                onDismiss()
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectDialog(onDismiss: () -> Unit, onCreateProject: (NewProjectsResponseItem) -> Unit) {
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var projectStartDate by remember { mutableStateOf("") }
    var projectEndDate by remember { mutableStateOf("") }
    var projectConsumption by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Create Project") },
        text = {
            Column {
                TextField(
                    value = projectName,
                    onValueChange = { projectName = it },
                    label = { Text("Project Name") }
                )
                TextField(
                    value = projectDescription,
                    onValueChange = { projectDescription = it },
                    label = { Text("Project Description") }
                )
                TextField(
                    value = projectStartDate,
                    onValueChange = { projectStartDate = it },
                    label = { Text("Start Date") }
                )
                TextField(
                    value = projectEndDate,
                    onValueChange = { projectEndDate = it },
                    label = { Text("End Date") }
                )
                TextField(
                    value = projectConsumption,
                    onValueChange = { projectConsumption = it },
                    label = { Text("Project Consumption") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val project = NewProjectsResponseItem(
                    description = projectDescription,
                    endDate = projectEndDate,
                    id = 0, // ID will be generated by the backend
                    name = projectName,
                    projectConsumption = projectConsumption.toIntOrNull() ?: 0,
                    startDate = projectStartDate
                )
                onCreateProject(project)
                onDismiss()
            }) {
                Text("Create")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}




//@Composable
//fun ProjectItem(project: NewProjectsResponseItem, onUpdateClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onUpdateClick() },
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = project.name, style = MaterialTheme.typography.displayMedium)
//            Text(text = "Description: ${project.description}", style = MaterialTheme.typography.displayMedium)
//            Text(text = "Start Date: ${project.startDate}", style = MaterialTheme.typography.displayMedium)
//            Text(text = "End Date: ${project.endDate}", style = MaterialTheme.typography.displayMedium)
//            Text(text = "Consumption: ${project.projectConsumption}", style = MaterialTheme.typography.displayMedium)
//        }
//    }
//}
