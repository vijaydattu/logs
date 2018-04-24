TARGETS = console-setup resolvconf mountkernfs.sh ufw apparmor hostname.sh plymouth-log x11-common screen-cleanup udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh mountdevsubfs.sh checkroot.sh networking open-iscsi iscsid urandom lvm2 checkfs.sh mountall.sh mountnfs.sh mountall-bootclean.sh bootmisc.sh checkroot-bootclean.sh mountnfs-bootclean.sh procps kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
networking: resolvconf mountkernfs.sh urandom procps
open-iscsi: networking iscsid
iscsid: networking
urandom: hwclock.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountnfs.sh: networking
mountall-bootclean.sh: mountall.sh
bootmisc.sh: mountall-bootclean.sh mountnfs-bootclean.sh checkroot-bootclean.sh udev
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
procps: mountkernfs.sh udev
kmod: checkroot.sh
