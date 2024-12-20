package com.agusteam.travelo.models

object TaskRepository {
    private val tasks = mutableListOf(
        TaskModel("cleaning", "Clean the house", Priority.Low),
        TaskModel("gardening", "Mow the lawn", Priority.Medium),
        TaskModel("shopping", "Buy the groceries", Priority.High),
        TaskModel("painting", "Paint the fence", Priority.Medium)
    )

    fun allTasks(): List<TaskModel> = tasks

    fun tasksByPriority(priority: Priority) = tasks.filter {
        it.priority == priority
    }

    fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addTask(task: TaskModel) {
        if(taskByName(task.name) != null) {
            throw IllegalStateException("Cannot duplicate task names!")
        }
        tasks.add(task)
    }
}