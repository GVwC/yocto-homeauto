#!/usr/bin/env bash

set -e

echo "📦 Yocto Home Automation Environment Setup"

# --- Required host packages
REQUIRED_PACKAGES=(
  gawk wget git diffstat unzip texinfo gcc g++ build-essential chrpath socat
  cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping
  python3-git python3-jinja2 libsdl1.2-dev pylint xterm zstd
  locales curl bzip2 make rpcsvc-proto
)

echo "🔍 Checking required packages..."
MISSING=()
for pkg in "${REQUIRED_PACKAGES[@]}"; do
  if ! dpkg -s "$pkg" &> /dev/null; then
    MISSING+=("$pkg")
  fi
done

if [ ${#MISSING[@]} -ne 0 ]; then
  echo "🚧 Installing missing packages: ${MISSING[*]}"
  sudo apt-get update
  sudo apt-get install -y "${MISSING[@]}"
else
  echo "✅ All required packages are already installed."
fi

# --- Ensure en_US.UTF-8 locale
if ! locale -a | grep -q "en_US.utf8"; then
  echo "🌐 Adding locale en_US.UTF-8"
  sudo locale-gen en_US.UTF-8
  sudo update-locale
else
  echo "🌐 Locale en_US.UTF-8 already available"
fi

# --- WSL check
if grep -qi microsoft /proc/version; then
  echo "💡 Detected WSL environment."
  echo "   ⚠️ WSL2 is preferred with systemd and Docker support"
fi

# --- Init submodules
echo "🔁 Initializing git submodules..."
git submodule update --init --recursive

# --- Init build env
if [ ! -d "build/conf" ]; then
  echo "📁 Creating new build environment..."
  source poky/oe-init-build-env build
else
  echo "🛠️ Build environment already exists."
  source poky/oe-init-build-env build
fi

# --- Add all required meta-layers
echo "➕ Adding Yocto layers if missing..."
bitbake-layers show-layers | grep -q "meta-oe" || bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers show-layers | grep -q "meta-python" || bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers show-layers | grep -q "meta-filesystems" || bitbake-layers add-layer ../meta-openembedded/meta-filesystems
bitbake-layers show-layers | grep -q "meta-virtualization" || bitbake-layers add-layer ../meta-virtualization
bitbake-layers show-layers | grep -q "meta-raspberrypi" || bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers show-layers | grep -q "meta-lts-mixins" || bitbake-layers add-layer ../meta-lts-mixins
bitbake-layers show-layers | grep -q "meta-homeauto" || bitbake-layers add-layer ../meta-homeauto

echo ""
echo "✅ Yocto setup complete!"
echo "📄 Edit build/conf/local.conf to configure:"
echo "    - MACHINE (e.g., raspberrypi5, qemux86-64)"
echo "    - IMAGE_INSTALL_append for Docker, Home Assistant, etc."
echo ""
echo "🧱 Then build with:"
echo "    bitbake core-image-homeauto"
