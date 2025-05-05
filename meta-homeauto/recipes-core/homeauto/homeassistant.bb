SUMMARY = "Install and run Home Assistant as a Docker container"
DESCRIPTION = "This recipe sets up Home Assistant to run in a Docker container"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=8e5c7c0d343d9021ecba1b1bdbbbf90f"

DEPENDS = "docker-moby"

inherit systemd

SRC_URI = ""

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${sysconfdir}/systemd/system/
}

FILES:${PN} += "${sysconfdir}/systemd/system/homeassistant.service"
SYSTEMD_SERVICE:${PN} = "homeassistant.service"

