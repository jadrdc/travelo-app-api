package com.agusteam.travelo.models

data class TaskModel(
    val name: String,
    val description: String,
    val priority: Priority
)
fun TaskModel.taskAsRow() = """
    <tr>
        <td>$name</td><td>$description</td><td>$priority</td>
    </tr>
    """.trimIndent()

fun List<TaskModel>.tasksAsTable() = this.joinToString(
    prefix = "<table rules=\"all\">",
    postfix = "</table>",
    separator = "\n",
    transform = TaskModel::taskAsRow
)