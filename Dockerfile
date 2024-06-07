# 1단계: 빌드를 위한 Maven 기반 이미지 사용
FROM maven:3.8.4-openjdk-17 as builder

# 애플리케이션 소스 코드를 이미지 내부로 복사
COPY . /app

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 빌드 (테스트 건너뛰기)
RUN mvn clean package -DskipTests

# 2단계: 실행을 위한 Java 환경 설정
FROM openjdk:17

# 빌드한 애플리케이션을 실행 환경으로 복사
COPY --from=builder /app/target/*.jar /app/app.jar

# 애플리케이션 실행
CMD ["java", "-jar", "/app/app.jar"]
