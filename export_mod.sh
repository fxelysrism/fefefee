#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR=$(cd "$(dirname "$0")" && pwd)
BUILD_DIR="$ROOT_DIR/build"
DIST_DIR="$ROOT_DIR/dist"

rm -rf "$BUILD_DIR" "$DIST_DIR"
mkdir -p "$BUILD_DIR/classes" "$DIST_DIR"

find "$ROOT_DIR/src/main/java" \
  -path "$ROOT_DIR/src/main/java/com/insanecraft/fabric/*" -prune -o \
  -name "*.java" -print > "$BUILD_DIR/sources.txt"

javac -d "$BUILD_DIR/classes" @"$BUILD_DIR/sources.txt"
jar cfe "$DIST_DIR/insanecraft-demo.jar" com.insanecraft.InsaneCraftMod -C "$BUILD_DIR/classes" .

echo "Created $DIST_DIR/insanecraft-demo.jar"
