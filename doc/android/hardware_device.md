# Hardware Device on Linux

* https://developer.android.com/tools/device.html

On linux you need to create a file `/etc/udev/rules.d/51-android.rules` as shown below. 

Set `device-id` to 0bb4 when you use a HTC. For a list of devices have a look at the link shown at the top. When you're not sure what kind of device you have. Use `lsusb` and look for your device.

```
SUBSYSTEM=="usb", ATTR{id.Vendor}=="device-id", MODE="0666", OWNER="user", GROUP="group"
```

Enable developermode in your device.
