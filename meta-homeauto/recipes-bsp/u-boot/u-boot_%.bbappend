FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://add-extra-env.patch"
SRC_URI:remove:rpi = " file://0001-rpi-always-set-fdt_addr-with-firmware-provided-FDT-address.patch"
