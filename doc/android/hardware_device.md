# Hardware Device on Linux
When you have an android device you may want to use this device for testing. Do the following steps to use your hardware device same as an virtual.

1) Enable developermode on your device.

2) Open a terminal and type `lsusb`. This shows all usbdevices connected to your computer.
Example output:

```
Bus 002 Device 004: ID 0bb4:0cf5 HTC (High Tech Computer Corp.) 
Bus 002 Device 002: ID 8087:0024 Intel Corp. Integrated Rate Matching Hub
Bus 002 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
Bus 004 Device 001: ID 1d6b:0003 Linux Foundation 3.0 root hub
Bus 003 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
Bus 001 Device 003: ID 2232:1018 Silicon Motion 
Bus 001 Device 002: ID 8087:0024 Intel Corp. Integrated Rate Matching Hub
Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
```

3) Look for your device. Note the bus, device number and the first four digits of the ID.

4) Create a file `/etc/udev/rules.d/51-android.rules` as shown below. You may need to change the ID.

`/etc/udev/rules.d/51-android.rules`

```
SUBSYSTEM=="usb", ATTR{id.Vendor}=="0bb4", MODE="0666", OWNER="user", GROUP="group"
```

*Note:* You may find a list of IDs under https://developer.android.com/tools/device.html

4) Now you need to grant ownership of the device. To do this use `sudo chown <user>:<group> /dev/bus/usb/<bus>/<device>`. In my case, with username alex, group alex and an HTC device: `sudo chown alex:alex /dev/bus/usb/002/004`.

5) To show connected devices change directory to `android-sdk/platform-tools` and type `./adb device`. When you still have a `NO PERMISSION`Error, restart adb by typing `./adb kill-server && ./adb start-server`.

6) You can install apps via. `Run As`, `Android Application`. all you need to do is to select your hardware device.
