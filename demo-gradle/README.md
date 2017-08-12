Gradle Note
===========

### Gradle 概述
Gradle是一个自动化的构建框架. 使用Project和Task表示项目及构建任务.
实际构建中是由Plugin向Project提供Property和Task, 完成特定构建任务, gradle自带一系列常用plugin.

### Gradle 部署
GRADLE_HOME 及 bin入PATH
gradlew(gradle wrapper) 是为使用者准备的, 会在第一次执行下载gradle.

### Gradle 脚本
gradle.build 脚本基于groovy语言和Gradle DSL编写.

#### Task编写