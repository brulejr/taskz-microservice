package io.jrb.labs.taskz.model

import io.jrb.common.model.Entity
import io.jrb.common.model.EntityBuilder
import io.jrb.labs.taskz.resource.TaskRun
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document
data class TaskRunEntity(
        @Id override val id: String?,
        @Indexed(unique = true) override val guid: UUID?,
        val taskGuid: UUID,
        val status: TaskStatus,
        val startedOn: Instant?,
        val completedOn: Instant?,
        override val createdOn: Instant?,
        override val createdBy: String?,
        override val modifiedOn: Instant?,
        override val modifiedBy: String?,
        val notes: String?
) : Entity {

    data class Builder(
            private var taskGuid: UUID? = null,
            private var status: TaskStatus? = null,
            private var startedOn: Instant? = null,
            private var completedOn: Instant? = null,
            private var notes: String? = null
    ) : EntityBuilder<TaskRunEntity, TaskRun>() {
        constructor(taskRun: TaskRun) : this() {
            this.guid = taskRun.guid
            this.taskGuid = taskRun.taskGuid
            this.status = taskRun.status
            this.startedOn = taskRun.startedOn
            this.completedOn = taskRun.completedOn
            this.createdOn = taskRun.createdOn
            this.createdBy = taskRun.createdBy
            this.modifiedOn = taskRun.modifiedOn
            this.modifiedBy = taskRun.modifiedBy
        }

        fun taskGuid(taskGuid: UUID?) = apply { this.taskGuid = taskGuid }
        fun status(status: TaskStatus?) = apply { this.status = status }
        fun startedOn(startedOn: Instant?) = apply { this.startedOn = startedOn }
        fun completedOn(completedOn: Instant?) = apply { this.completedOn = completedOn }
        fun notes(notes: String?) = apply { this.notes = notes }

        override fun build() = TaskRunEntity(
                id = this.id,
                guid = this.guid,
                taskGuid = this.taskGuid!!,
                status = this.status!!,
                startedOn = this.startedOn!!,
                completedOn = this.completedOn!!,
                notes = this.notes!!,
                createdOn = this.createdOn,
                createdBy = this.createdBy,
                modifiedOn = this.modifiedOn,
                modifiedBy = this.modifiedBy
        )
    }
}
