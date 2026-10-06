#!/usr/bin/env bash
# Copyright 2026 Developer Army
# Licensed under the Apache License, Version 2.0

set -e
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Download Purpur jar if not present
if [ ! -f "$SCRIPT_DIR/purpur.jar" ]; then
    echo "Downloading Purpur 1.21.1..."
    curl -s -L -o "$SCRIPT_DIR/purpur.jar" "https://api.purpurmc.org/v2/purpur/1.21.1/2329/download"
fi

# Prioritize local JDK 21
if [ -x "$SCRIPT_DIR/runtime/jdk21/usr/lib/jvm/java-21-openjdk-amd64/bin/java" ]; then
    JAVA_BIN="$SCRIPT_DIR/runtime/jdk21/usr/lib/jvm/java-21-openjdk-amd64/bin/java"
elif command -v java &> /dev/null; then
    JAVA_BIN="java"
else
    echo "Error: Java 21+ not found."
    exit 1
fi

exec "$JAVA_BIN" -Xms10G -Xmx10G \
  -XX:+UseG1GC \
  -XX:+ParallelRefProcEnabled \
  -XX:MaxGCPauseMillis=200 \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+DisableExplicitGC \
  -XX:+AlwaysPreTouch \
  -XX:G1NewSizePercent=30 \
  -XX:G1MaxNewSizePercent=40 \
  -XX:G1ReservePercent=20 \
  -XX:G1HeapWastePercent=5 \
  -XX:G1MixedGCCountTarget=4 \
  -XX:InitiatingHeapOccupancyPercent=15 \
  -XX:G1MixedGCLiveThresholdPercent=90 \
  -XX:G1RSetUpdatingPauseTimePercent=5 \
  -XX:SurvivorRatio=32 \
  -XX:+PerfDisableSharedMem \
  -XX:MaxTenuringThreshold=1 \
  -Dusing.aikars.flags=https://mcflags.emc.gs \
  -Daikars.new.flags=true \
  --add-modules=jdk.incubator.vector \
  -jar purpur.jar --nogui "$@"
