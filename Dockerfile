# 1단계: Gradle을 사용하여 빌드하기 위한 이미지
FROM gradle:7.2-jdk11 as builder

# 소스 코드를 이미지로 복사
COPY --chown=gradle:gradle . /home/gradle/src

# 작업 디렉토리 설정
WORKDIR /home/gradle/src

# 애플리케이션 빌드 (테스트 무시)
RUN gradle clean build -x test --no-daemon --watch-fs=false

# 2단계: 런타임 Java 환경 설정
FROM openjdk:11

# 빌드된 애플리케이션 복사
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar

# 애플리케이션 실행
CMD ["java", "-jar", "/app/app.jar"]