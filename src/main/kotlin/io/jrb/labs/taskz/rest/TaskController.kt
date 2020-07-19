package io.jrb.labs.taskz.rest

import com.github.fge.jsonpatch.JsonPatch
import io.jrb.labs.taskz.resource.Task
import io.jrb.labs.taskz.service.TaskService
import mu.KotlinLogging
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(val taskService: TaskService) {

    private val log = KotlinLogging.logger {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Mono<EntityModel<Task>> {
        return taskService.create(task).map {
            EntityModel.of(it)
                    .add(selfLink(it.guid!!))
                    .add(collectionLink())
        }
    }

    @DeleteMapping("/{taskGuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable taskGuid: UUID): Mono<Void> {
        return taskService.delete(taskGuid)
    }

    @GetMapping("/{taskGuid}")
    fun getTaskById(@PathVariable taskGuid: UUID): Mono<EntityModel<Task>> {
        return taskService.findByGuid(taskGuid).map {
            EntityModel.of(it)
                    .add(selfLink(taskGuid))
                    .add(collectionLink())
        }
    }

    @GetMapping
    fun listTasks(): Flux<EntityModel<Task>> {
        return taskService.listAll().map {
            EntityModel.of(it)
                    .add(selfLink(it.guid!!))
        }
    }

    @PatchMapping(path = ["/{taskGuid}"], consumes = ["application/json-patch+json"])
    fun patchTask(@PathVariable taskGuid: UUID, @RequestBody taskPatch: JsonPatch): Mono<EntityModel<Task>> {
        return taskService.update(taskGuid, taskPatch).map {
            EntityModel.of(it)
                    .add(selfLink(it.guid!!))
                    .add(collectionLink())
        }
    }

    private fun collectionLink(): Link {
        return linkTo(methodOn(javaClass).listTasks()).withRel("collection")
    }

    private fun selfLink(taskGuid: UUID): Link {
        return linkTo(methodOn(javaClass).getTaskById(taskGuid)).withSelfRel()
    }

}
