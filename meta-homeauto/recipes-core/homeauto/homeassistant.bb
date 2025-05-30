# meta-homeauto/recipes-core/systemd/homeassistant/homeassistant.bb

SUMMARY = "Docker Compose setup for Home Assistant"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://compose.yml;md5=3eee4c9770af5086a7892923fd3c459e \
    file://start-homeassistant.sh;md5=550f69cea3e8ee584c5a2622263f2479 \
    file://homeassistant.service;md5=6e142fcd2a38d35914d0787397303ba8 \
"

SRC_URI += " \
    file://compose.yml \
    file://start-homeassistant.sh \
    file://homeassistant.service \
"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "homeassistant.service"

do_install() {
    install -d ${D}/containers/homeassistant
    install -m 0644 ${WORKDIR}/compose.yml ${D}/containers/homeassistant/

    install -d ${D}/containers/homeassistant
    install -m 0755 ${WORKDIR}/start-homeassistant.sh ${D}/containers/start-homeassistant.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/homeassistant.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += " \
    /containers/homeassistant \
    /containers/start-homeassistant.sh \
    ${systemd_system_unitdir}/homeassistant.service \
"

RDEPENDS:${PN} += "docker-compose"

