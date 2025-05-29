SUMMARY = "A full image for running Home Assistant in a Docker container"
LICENSE = "MIT"

inherit core-image

# Enable useful runtime tools and SSH access
IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = " \
    homeassistant \
    ssh-keys \  
    docker-moby \
    u-boot-fw-utils \
    u-boot-env \
    tzdata \
    bash \
    htop \
    curl \
    nano \
    less \
    iproute2 \
    iputils \
    net-tools \
    util-linux \
    systemd-analyze \
    rsyslog \
    logrotate \
    ca-certificates \
    wpa-supplicant \
"

# Add extra system support features
DISTRO_FEATURES:append = " virtualization systemd"

# Allocate extra space for Docker and configs
IMAGE_ROOTFS_EXTRA_SPACE = "102400"

# Pre-create persistent config dir for Home Assistant
ROOTFS_POSTPROCESS_COMMAND:append = " create_homeassistant_config_dir; "
create_homeassistant_config_dir() {
    mkdir -p ${IMAGE_ROOTFS}/mnt/homeassistant_config
    chown root:root ${IMAGE_ROOTFS}/mnt/homeassistant_config
}

# Use U-Boot for RPi
RPI_USE_U_BOOT = "1"
