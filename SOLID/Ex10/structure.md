```
SOLID/Ex10/src/
├── Main.java
├── TransportBookingService.java
├── TripRequest.java
├── GeoPoint.java
├── BookingReceipt.java
├── ConsoleUi.java
│
├── IDistanceCalculator.java             [interface]
│   └── double km(GeoPoint a, GeoPoint b)
│       └── DistanceCalculator.java      [implements IDistanceCalculator]
│
├── IDriverAllocator.java                [interface]
│   └── String allocate(String studentId)
│       └── DriverAllocator.java         [implements IDriverAllocator]
│
├── IPaymentGateway.java                 [interface]
│   └── String charge(String studentId, double amount)
│       └── PaymentGateway.java          [implements IPaymentGateway]
│
└── IFareCalculator.java                 [interface]
    └── double calculate(double km)
        └── FareCalculator.java          [implements IFareCalculator]
```