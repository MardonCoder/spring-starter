plugins {
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "9.8.2"
    id("java")
    // Плагин для кодогенерации jOOQ
    id("org.jooq.jooq-codegen-gradle") version "3.21.9"
}

group = "org.mardon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // стартер жука
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    implementation("org.postgresql:postgresql")
    implementation("org.springframework.data:spring-data-envers")

    // Драйвер PostgreSQL для кодогенератора (в официальном плагине конфигурация называется jooqCodegen)
    jooqCodegen("org.postgresql:postgresql:42.7.4")

    // auto docker launch on project launch
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Добавить для работы Docker Compose в тестах:
    testImplementation("org.springframework.boot:spring-boot-docker-compose")

//    val springVersion = "7.0.9"
//    val assertjVersion = "3.27.7"
//    val jakartaAnnotationVersion = "3.0.0"

//    testImplementation(platform("org.junit:junit-bom:6.0.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    testImplementation("org.assertj:assertj-core:${assertjVersion}")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // not used because of dependency management
//    implementation("org.springframework:spring-core:$springVersion")
//    implementation("org.springframework:spring-context:$springVersion")
//    implementation("jakarta.annotation:jakarta.annotation-api:$jakartaAnnotationVersion")
}

jooq {
    configuration {
        logging = org.jooq.meta.jaxb.Logging.WARN
        jdbc {
            driver = "org.postgresql.Driver"
            url = "jdbc:postgresql://localhost:5432/mydb"
            user = "myuser"
            password = "mypassword"
        }
        generator {
            name = "org.jooq.codegen.DefaultGenerator"
            database {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                inputSchema = "public"
                includes = ".*"
                excludes = ""
            }
            target {
                packageName = "org.mardon.generated.jooq"
                directory = "build/generated-src/jooq/main"
            }
        }
    }
}

sourceSets {
    main {
        java {
            srcDir("build/generated-src/jooq/main")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}