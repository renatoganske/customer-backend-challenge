#!/bin/bash

# Esperar pelo PostgreSQL estar pronto
while ! nc -z postgres 5432; do
  sleep 1
done

# Comandos SQL para criar o banco de dados e configurar as tabelas
psql -h postgres -U postgres -c "CREATE DATABASE customerbackendchallengedb;"
psql -h postgres -U postgres -d customerbackendchallengedb -f /script/schema.sql