# meta-homeauto/recipes-core/systemd/homeassistant/homeassistant.bb

SUMMARY = "Runs Home Assistant container on system boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://homeassistant.service;md5=154e3903ae8e6d22d604f48d0f179f83"

SRC_URI += "file://homeassistant.service"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "homeassistant.service"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}"
