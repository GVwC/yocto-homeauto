SUMMARY = "A full image for running Home Assistant in a Docker container"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = " \
    docker-moby \
    homeassistant \
"

# Optional, but helps make the image more self-contained
DISTRO_FEATURES:append = " virtualization systemd"

# Add extra space for persistent configs and containers
IMAGE_ROOTFS_EXTRA_SPACE = "102400"

# Precreate directory for config
ROOTFS_POSTPROCESS_COMMAND:append = " create_homeassistant_config_dir; "
create_homeassistant_config_dir() {
    mkdir -p ${IMAGE_ROOTFS}/mnt/homeassistant_config
}
