# 🏬 Système de Gestion des Stocks Multi-Entrepôts

Ce projet est une application web permettant de gérer efficacement les stocks répartis entre plusieurs entrepôts. Elle permet le suivi des produits, des mouvements de stock, des alertes de seuil, ainsi que la gestion des utilisateurs.

## 📦 Fonctionnalités principales

- Gestion des **entrepôts** (adresse, capacité, etc.)
- Suivi des **produits** (prix, catégorie, seuil minimal, fournisseur, etc.)
- Suivi des **stocks** par entrepôt
- Gestion des **mouvements de stock** (entrées et sorties)
- Gestion des **utilisateurs** avec rôles (Admin, Chef d’entrepôt, etc.)
- Alertes automatiques en cas de seuil bas de stock
- Tableau de bord avec statistiques

---

## 🧱 Architecture

- **Backend** : Spring Boot + Spring Security + JPA + MySQL
- **Frontend** : Angular
- **Orchestration** : Docker & Docker Compose

---

## 🛠️ Technologies utilisées

### Backend (Spring Boot)
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- MySQL
- Maven
- Hibernate

### Frontend (Angular)
- Angular 15+
- Bootstrap / Angular Material
- RxJS
- Angular CLI

### DevOps / Conteneurisation
- Docker
- Docker Compose

---

## ⚙️ Installation et exécution

### Prérequis

- [Docker](https://www.docker.com/) et [Docker Compose](https://docs.docker.com/compose/)
- (En développement local : Java 17, Node.js 18+, Angular CLI si nécessaire)

---

### 📦 Lancer le projet avec Docker

1. **Cloner le projet**

```bash
git clone https://github.com/azizosdx/GestionStock.git -b master
cd gestion-stock
