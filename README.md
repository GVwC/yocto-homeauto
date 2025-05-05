# 🏠 Yocto Home Automation for Raspberry Pi 5

This project builds a custom Linux distribution for **Raspberry Pi 5** using the **Yocto Project**. It’s optimized for home automation using **Docker**, with support for **Home Assistant** and other related services.

---

## 🌟 Features

- ✅ Custom Yocto build for Raspberry Pi 5
- 🐳 Docker and Docker Compose support (via `meta-virtualization`)
- 🧠 Home Assistant running in a Docker container
- 📦 Expandable services: MQTT, ESPHome, etc.
- ⚙️ Easily reproducible with Git + submodules
- 📁 Setup script for easy bootstrapping and dependency handling

---

## 📁 Included Layers

- `poky` — Yocto core (build system)
- `meta-raspberrypi` — Raspberry Pi BSP
- `meta-openembedded` — Extra packages/utilities
- `meta-virtualization` — Docker support
- `meta-homeauto` — Your custom layer (Home Assistant, etc.)

---

## 🚀 Quick Start

### 1. Clone this repository with submodules

```bash
git clone --recurse-submodules git@github.com:GVwC/yocto-homeauto.git
cd yocto-homeauto
```

### 2. Run the setup script

```bash
./setup.sh
```

### 3. Build your custom image

```bash
source poky/oe-init-build-env build
bitbake core-image-homeauto
```
