# ============= Build stage =============
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# cache dependencies
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# build WAR
COPY src ./src
RUN mvn -B -q -DskipTests package

# ============= Run stage (Tomcat 11 / Jakarta) =============
FROM tomcat:11.0-jre17-temurin

# Xóa webapps mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy đúng WAR của bạn và chạy ở "/"
COPY --from=build /app/target/btap_lan1-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
