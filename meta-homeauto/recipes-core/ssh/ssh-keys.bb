SUMMARY = "Add SSH public key for root"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://authorized_keys;md5=d3ea1bd3e57bb95f78f830df137f27b7"

SRC_URI += "file://authorized_keys"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/root/.ssh
    install -m 0644 ${WORKDIR}/authorized_keys ${D}/root/.ssh/authorized_keys
}

FILES:${PN} += "/root/.ssh/authorized_keys"
