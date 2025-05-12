# ## build stage backend
FROM maven:3.8-openjdk-17-slim AS backend-app-build

COPY backend/src /home/app/src

COPY backend/pom.xml /home/app

RUN mvn -X -e -f /home/app/pom.xml clean package -DskipTests 


# deploy stage backend

FROM openjdk:17-slim AS backend-app

COPY --from=backend-app-build /home/app/target/*.jar /usr/local/lib/backend-app.jar

ENTRYPOINT ["java","-jar","/usr/local/lib/backend-app.jar"]

## build stage web app
FROM node:18.14 as web-app-build
WORKDIR /app
COPY frontend/gestion-Stock-Frontend/. .
COPY frontend/gestion-Stock-Frontend/package.json .
RUN npm install  --legacy-peer-deps && npm run build --prod

## deploy stage web app
FROM nginx:alpine as web-app
COPY gateway/default.conf /etc/nginx/conf.d/default.conf
COPY --from=web-app-build /app/dist/gestionstock /app



