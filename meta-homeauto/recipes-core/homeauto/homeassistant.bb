# meta-homeauto/recipes-core/systemd/homeassistant/homeassistant.bb

SUMMARY = "Runs Home Assistant container on system boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://homeassistant.service;md5=256c8d5ccb90db9f19018065c404a6ff"

SRC_URI += "file://homeassistant.service"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "homeassistant.service"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}"
