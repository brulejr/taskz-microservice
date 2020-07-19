rootProject.name = "taskz-microservice"

sourceControl {
    gitRepository(uri("https://github.com/brulejr/kotlin-microservice-lib.git")) {
        producesModule("io.jrb:kotlin-microservice-lib")
    }
}
