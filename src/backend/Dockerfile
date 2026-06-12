# 第一階段：使用 Maven 映像檔進行 Build
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# 複製 Maven 相關檔案並下載依賴 (加快 Build 速度)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# 複製源代碼並進行打包
COPY src ./src
RUN ./mvnw clean install -DskipTests

# 第二階段：建立正式運行環境
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 從第一階段複製編譯好的 jar 檔
COPY --from=build /app/target/*.jar app.jar

# 執行指令
ENTRYPOINT ["java", "-jar", "app.jar"]