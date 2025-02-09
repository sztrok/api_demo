# Użycie oficjalnego obrazu Node.js w wersji 10.5.0
FROM node:10.5.0

WORKDIR /app

# Kopiowanie plików package.json i package-lock.json
COPY package.json package-lock.json ./

# Instalacja zależności
RUN npm install

# Kopiowanie reszty plików projektu
COPY . .

# Budowanie aplikacji frontendowej (jeśli wymaga)
RUN npm run build

# Eksponowanie portu aplikacji frontendowej
EXPOSE 3000

# Uruchomienie aplikacji frontendowej
CMD ["npm", "start"]
