# ClewareSocket
![Cleware](https://img.shields.io/badge/Manufacturer-Cleware%20GmbH-orange.svg) ![Product](https://img.shields.io/badge/Article%20No.%20-41--1-yellow.svg)

Built around [Comsysto's Repository](https://github.com/comsysto/build-light), ClewareSocket allows users to wirelessly control the Cleware USB-Ampel.

## Setting up
### 1. Server
- The server application is ready to run once the USB Ampel is attached. If the application cannot open port 5815, either change the port in SocketServer.java to another port, or close any other application(s) using this port.
- Build and run the module inside ./Server on any platform. The USB libraries are optimized for all Windows, MacOS, and \*NIX platforms.

### 2. Client
- Change the hostname and port inside SocketClient.java
- Build and run the module inside ./Client on any platform. JavaFX ensures a consistent interface across all platforms.


## Disclaimer
This application is built for fun and serves no specific usage. If you wish to expand on this code, feel free to do so. 
Links back to this repository are not required though appreciated.

Any pull requests will be reviewed seriously, if you want to contribute, please do!
