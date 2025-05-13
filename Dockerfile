# ====================
# Backend Build Stage
# ====================
FROM maven:3.8-openjdk-17-slim AS backend-build
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn clean package -DskipTests

# ====================
# Backend App Stage (Target: backend-app)
# ====================
FROM openjdk:17-slim AS backend-app
COPY --from=backend-build /app/target/*.jar /app/backend.jar
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]

# ====================
# Frontend Build Stage
# ====================
FROM node:18-alpine AS frontend-build
WORKDIR /app
COPY frontend/gestion-Stock-Frontend/ .
RUN npm install --legacy-peer-deps && npm run build --prod

# ====================
# Frontend Deploy Stage (Target: web-app)
# ====================
FROM nginx:alpine AS web-app
COPY --from=frontend-build /app/dist/gestion-stock-frontend /usr/share/nginx/html
