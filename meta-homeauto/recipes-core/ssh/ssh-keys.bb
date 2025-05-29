SUMMARY = "Add SSH public key for root"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://authorized_keys;md5=bb455feb780167dc52157adf7da3bedd"

SRC_URI += "file://authorized_keys"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/dropbear/root/.ssh
    install -m 0644 ${WORKDIR}/authorized_keys ${D}/etc/dropbear/root/.ssh/authorized_keys
}

FILES:${PN} += "/etc/dropbear/root/.ssh/authorized_keys"
