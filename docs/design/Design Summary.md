# Design Summary

## Purpose

- Conventional smart locks:
  - Require home renovation for installation.
  - Require technical expertise to install & maintain.
  - Are time-consuming and inconvenient to install and set up.
  - Are hard to remove and reinstall in a new home.
  - Have high server-maintenance fees.
- Our smart lock model offers **cheap, practical, and easy-to-install security** for tenants, apartment-users, and moving homeowners.
- **3D print your own smart lock** from standard materials with our **open-source design**.
- **Download our smartphone app** off Google Play Store, and **register your lock** in a matter of seconds.
- **Fast, easy installation with no costs required.** Simply attach your smart lock onto your door's deadbolt lock, turn it on, and you're ready to go.

## Software Architecture

- **Client-Server** architecture.
- An **Android app** will be downloadable from Google Play Store and used to control the lock.
- The app will communicate with a **server** and **database** hosted on a **Raspberry Pi Zero W**.
- **One-time registration,** where the user will register his **3D-printed** Smart Lock.
- Users can quickly connect their smartphone and lock via **Bluetooth**.
- The server will monitor the user's **lock status** in real time, and **open/close** the lock on request.
- Users may **invite their friends** to use their lock for a limited time.
- A **database** will keep track of all users, registered locks, and access permissions.

## Tech Stack

- Android App: 
  - Java
- Server: 
  - Python
  - Raspberry Pi Zero W with Raspbian OS.
  - Bluetooth & Servo-controller libraries
- Database: 
  - MySQL