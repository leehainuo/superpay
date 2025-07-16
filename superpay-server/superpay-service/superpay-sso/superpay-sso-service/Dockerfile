# 基础镜像
FROM jdk17.0.10-aarch64:latest
# 复制jar包到容器
COPY target/superpay-sso-service-0.0.1-SNAPSHOT.jar /app.jar
# 设置容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "/app.jar"]