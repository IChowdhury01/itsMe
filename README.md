![Logo](/demos/Snapshots/Final/logo2.png)

**A new, revolutionary smart lock.**

![release-v1.0](https://img.shields.io/badge/release-v1.0-blue)
![build-passing](https://img.shields.io/badge/build-passing-brightgreen)
![dependencies-up-to-date](https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen)
![license-MIT](https://img.shields.io/badge/license-MIT-green)



# Project Description

itsMe is a smart lock IoT device designed for improved portability and versatility, and low cost-of-installation. itsMe locks can be 3D-printed and assembled at under $60, freely attached onto deadbolt locks with no required home renovation, and controlled remotely via Bluetooth with the itsMe Android app. itsMe's design is fully open-source, and can be modified to fit locks of any size for individual use. See our **[final presentation](https://www.youtube.com/watch?v=xuYFx85O6f8)** and **[complete documentation](/docs/full-documentation)** for further information.

[![Design Poster](/docs/full-documentation/Final-Poster.png]

# Demo
**[Operation demo](https://www.youtube.com/watch?v=Y3_GFy8Gmhg)**

**[Rotation demo](https://www.youtube.com/watch?v=DRbHsR_Iyws)**



## 3D-Printable Models

[![CAD Model](/demos/Snapshots/Final/CAD.gif)(/demos/CAD)

Click the animation above to find the models.

## Materials
![](https://i.imgur.com/hORWZto.jpg)

The above materials are all purchasable on Amazon. These are the recommended brands minimizing cost-of-installation; other brands may work, but device stability will not be guaranteed. 

# Getting Started

## Prerequisites
- Android phone
- [Android Studio 4.0+](https://developer.android.com/studio) installed.

## Installation
1. Purchase the materials listed above.
2. Clone this repository
3. [Optional] Modify the dimensions of the downloaded CAD models to suit your needs.
4. 3D print your materials with ABS plastic.
5. Assemble your 3D-printed parts, following the animation above. Place the Servo Motor in the groove and wire it to your Raspberry Pi. Place the self-adhesive tape strips around the edge of your device (where it'll make contact with the door).
6. Open this project in Android studio. Then connect your Android device to your computer with a USB cable, and run the app. Your phone will now have the itsMe app installed.

## Usage
1. Install your smart lock onto your door by simply placing the grip over a pre-existing deadbolt lock. Make sure itthe device is firmly attached to the door with the adhesive strips.
2. Open the itsMe Android app.
3. Turn on Bluetooth on your phone and connect it to your smart lock.
4. Your lock's state is now being monitored through the app. Press the "Lock" or "Unlock" app buttons to control the lock's state. Enjoy!

[![UI](/demos/Snapshots/Final/UI2.jpg)]


# License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

# Acknowledgements

- **Development team**: Min JO, Hyungseok Yoon, Josh Go, Ivan Chowdhury
- **Advisors:** Sam Keene, Carl Sable, Shivam Mevawala
