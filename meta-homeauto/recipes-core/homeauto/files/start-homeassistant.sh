#!/bin/sh

CONFIG_DIR="/containers/homeassistant"
COMPOSE_FILE="$CONFIG_DIR/compose.yml"

# Ensure directories
if [ ! -d "$CONFIG_DIR/config" ]; then
  echo "Creating config directory..."
  mkdir -p "$CONFIG_DIR/config"
fi

# Check compose file
if [ ! -f "$COMPOSE_FILE" ]; then
  echo "Error: $COMPOSE_FILE not found!" >&2
  exit 1
fi

echo "Starting Home Assistant container with docker-compose..."
/usr/bin/docker compose -f "$COMPOSE_FILE" up -d
