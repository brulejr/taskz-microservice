package io.jrb.labs.taskz.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.jrb.common.service.CrudService
import io.jrb.labs.taskz.model.TaskEntity
import io.jrb.labs.taskz.repository.TaskEntityRepository
import io.jrb.labs.taskz.resource.Task
import org.springframework.stereotype.Service

@Service
class TaskService(
        val taskEntityRepository: TaskEntityRepository,
        val objectMapper: ObjectMapper
) : CrudService<TaskEntity, Task>(
        taskEntityRepository,
        "Task",
        TaskEntity::class.java,
        TaskEntity.Builder::class.java,
        Task::class.java,
        Task.Builder::class.java,
        objectMapper
) {
}
