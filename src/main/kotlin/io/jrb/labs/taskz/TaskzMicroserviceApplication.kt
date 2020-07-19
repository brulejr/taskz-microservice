package io.jrb.labs.taskz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskzMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<TaskzMicroserviceApplication>(*args)
}
