package io.jrb.labs.taskz.model

import io.jrb.common.model.Entity
import io.jrb.common.model.EntityBuilder
import io.jrb.labs.taskz.resource.Task
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document
data class TaskEntity(
        @Id override val id: String?,
        @Indexed(unique = true) override val guid: UUID?,
        val type: TaskType,
        val name: String,
        val description: String,
        val tags: List<String>,
        override val createdOn: Instant?,
        override val createdBy: String?,
        override val modifiedOn: Instant?,
        override val modifiedBy: String?
) : Entity {

    data class Builder(
            private var type: TaskType? = null,
            private var name: String? = null,
            private var description: String? = null,
            private var tags: List<String>? = null
    ) : EntityBuilder<TaskEntity, Task>() {
        constructor(task: Task): this() {
            this.guid = task.guid
            this.type = task.type
            this.name = task.name
            this.description = task.description
            this.tags = task.tags
            this.createdOn = task.createdOn
            this.createdBy = task.createdBy
            this.modifiedOn = task.modifiedOn
            this.modifiedBy = task.modifiedBy
        }

        constructor(taskEntity: TaskEntity): this() {
            this.id = taskEntity.id
            this.guid = taskEntity.guid
            this.type = taskEntity.type
            this.name = taskEntity.name
            this.description = taskEntity.description
            this.tags = taskEntity.tags
            this.createdOn = taskEntity.createdOn
            this.createdBy = taskEntity.createdBy
            this.modifiedOn = taskEntity.modifiedOn
            this.modifiedBy = taskEntity.modifiedBy
        }

        fun type(type: TaskType?) = apply { this.type = type }
        fun name(name: String?) = apply { this.name = name }
        fun description(description: String?) = apply { this.description = description }
        fun tags(tags: List<String>?) = apply { this.tags = tags }

        override fun build() = TaskEntity(
                id = this.id,
                guid = this.guid,
                type = this.type!!,
                name = this.name!!,
                description = this.description!!,
                tags = this.tags!!,
                createdOn = this.createdOn,
                createdBy = this.createdBy,
                modifiedOn = this.modifiedOn,
                modifiedBy = this.modifiedBy
        )
    }

}
