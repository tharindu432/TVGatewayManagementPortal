# TV Gateway Management Portal

This project implements a REST API solution to manage gateways and attached peripheral devices for Tech Venturas' network management system.

## Getting Started

These instructions will help you set up and run the TV Gateway Management Portal on your local machine.

### Prerequisites

- Java 8 or higher
- Maven
- Git

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/tv-gateway-management.git
```

2.Navigate to the project directory:

```bash
cd tv-gateway-management
```

#### The application will be accessible at http://localhost:8080.


## Gateways

GET `/api/gateways `: Get all gateways.
GET `/api/gateways/{serialNumber}`: Get details of a specific gateway.
POST `/api/gateways`: Add a new gateway.
PUT `/api/gateways/{serialNumber}`: Update details of a specific gateway.
PATCH `/api/gateways/{serialNumber}`: Partially update details of a specific gateway.
DELETE `/api/gateways/{serialNumber}`: Delete a gateway.


## Peripheral Devices

GET `/api/gateways/{serialNumber}/devices` : Get all peripheral devices for a specific gateway.
GET `/api/gateways/{serialNumber}/devices/{deviceUid}`: Get details of a specific peripheral device for a gateway.
POST `/api/gateways/{serialNumber}/devices`: Add a new peripheral device to a gateway.
PUT `/api/gateways/{serialNumber}/devices/{deviceUid}`: Update details of a specific peripheral device for a gateway.
PATCH `/api/gateways/{serialNumber}/devices/{deviceUid}`: Partially update details of a specific peripheral device for a gateway.
DELETE `/api/gateways/{serialNumber}/devices/{deviceUid}`: Delete a peripheral device from a gateway.

## Happy Coding !
