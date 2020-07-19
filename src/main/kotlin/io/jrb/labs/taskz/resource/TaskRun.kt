package io.jrb.labs.taskz.resource

import com.fasterxml.jackson.annotation.JsonInclude
import io.jrb.common.resource.Resource
import io.jrb.common.resource.ResourceBuilder
import io.jrb.labs.taskz.model.TaskRunEntity
import io.jrb.labs.taskz.model.TaskStatus
import java.time.Instant
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TaskRun(
        override val guid: UUID?,
        val taskGuid: UUID,
        val status: TaskStatus,
        val startedOn: Instant?,
        val completedOn: Instant?,
        override val createdOn: Instant?,
        override val createdBy: String?,
        override val modifiedOn: Instant?,
        override val modifiedBy: String?,
        val notes: String?
) : Resource {

    data class Builder(
            private var taskGuid: UUID? = null,
            private var status: TaskStatus? = null,
            private var startedOn: Instant? = null,
            private var completedOn: Instant? = null,
            private var notes: String? = null
    ) : ResourceBuilder<TaskRun, TaskRunEntity>() {

        constructor(taskRunEntity: TaskRunEntity): this() {
            this.guid = taskRunEntity.guid
            this.taskGuid = taskRunEntity.taskGuid
            this.status = taskRunEntity.status
            this.startedOn = taskRunEntity.startedOn
            this.completedOn = taskRunEntity.completedOn
            this.createdOn = taskRunEntity.createdOn
            this.createdBy = taskRunEntity.createdBy
            this.modifiedOn = taskRunEntity.modifiedOn
            this.modifiedBy = taskRunEntity.modifiedBy
            this.notes = taskRunEntity.notes
        }

        fun taskGuid(taskGuid: UUID?) = apply { this.taskGuid = taskGuid }
        fun status(status: TaskStatus?) = apply { this.status = status }
        fun startedOn(startedOn: Instant?) = apply { this.startedOn = startedOn }
        fun completedOn(completedOn: Instant?) = apply { this.completedOn = completedOn }
        fun notes(notes: String?) = apply { this.notes = notes }

        override fun build() = TaskRun(
                guid = this.guid,
                taskGuid = this.taskGuid!!,
                status = this.status!!,
                startedOn = this.startedOn!!,
                completedOn = this.completedOn!!,
                createdOn = this.createdOn,
                createdBy = this.createdBy,
                modifiedOn = this.modifiedOn,
                modifiedBy = this.modifiedBy,
                notes = this.notes
        )
    }

}
