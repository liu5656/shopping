# 指定语法解析器，1总是指向最新的版本，
# syntax=docker/dockerfile:1

# 指定基础镜像
#FROM openjdk:16-alpine3.13
FROM openjdk:15

# 指定工作路径，docker使用这个路径作为后续命令的默认路径，这样只需要写出相对工作路径，不用写全路径
WORKDIR /app

# 复制maven wrapper 和pom.xml文件到镜像，第一个参数是要复制的文件、目录，第二个参数是要复制到的地方
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 一旦我们pom.xml文件复制进镜像，可以使用RUN ./mvnw dependency:go-offline将依赖安装进镜像
RUN ./mvnw dependency:go-offline

# 复制源代码到镜像
COPY src ./src


# 当镜像已经运行在容器中是，指示docker要执行的指令
CMD ["./mvnw", "spring-boot:run"]
#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql"]