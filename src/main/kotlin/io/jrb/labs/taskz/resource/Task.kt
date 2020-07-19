package io.jrb.labs.taskz.resource

import com.fasterxml.jackson.annotation.JsonInclude
import io.jrb.common.resource.Resource
import io.jrb.common.resource.ResourceBuilder
import io.jrb.labs.taskz.model.TaskEntity
import io.jrb.labs.taskz.model.TaskType
import java.time.Instant
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Task(
        override val guid: UUID?,
        val type: TaskType,
        val name: String,
        val description: String,
        val tags: List<String>,
        override val createdOn: Instant?,
        override val createdBy: String?,
        override val modifiedOn: Instant?,
        override val modifiedBy: String?
) : Resource {

    data class Builder(
            private var type: TaskType? = null,
            private var name: String? = null,
            private var description: String? = null,
            private var tags: List<String>? = null
    ) : ResourceBuilder<Task, TaskEntity>() {

        constructor(taskEntity: TaskEntity): this() {
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

        override fun build() = Task(
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
