```
SOLID/Ex4/src/
├── Main.java
├── HostelFeeCalculator.java
├── BookingRequest.java
├── Money.java
├── LegacyRoomTypes.java
├── AddOn.java                           [enum]
├── FakeBookingRepo.java
├── ReceiptPrinter.java
├── RoomTypeMapper.java
├── AddOnMapper.java
│
├── RoomType.java                        [interface]
│   ├── int type()
│   ├── Money basePrice()
│   └── Money deposit()
│       ├── SingleRoom.java              [implements RoomType]
│       ├── DoubleRoom.java              [implements RoomType]
│       ├── TripleRoom.java              [implements RoomType]
│       └── DeluxeRoom.java              [implements RoomType]
│
└── AddOnPricing.java                    [interface]
    ├── AddOn type()
    └── Money monthlyPrice()
        ├── MessAddOn.java               [implements AddOnPricing]
        ├── LaundryAddOn.java            [implements AddOnPricing]
        └── GymAddOn.java               [implements AddOnPricing]
```