```
SOLID/Ex7/src/
├── Main.java
├── ClassroomController.java
├── DeviceRegistry.java
├── Manual.java
│
└── SmartClassroomDevice.java            [interface]
    ├── void powerOn()
    └── void powerOff()
        │
        ├── InputConnectable.java        [interface extends SmartClassroomDevice]
        │   └── void connectInput(String port)
        │       ├── Projector.java       [implements InputConnectable]
        │       └── SmartBoard.java      [implements InputConnectable]
        │
        ├── Dimmable.java                [interface extends SmartClassroomDevice]
        │   └── void setBrightness(int pct)
        │       └── LightsPanel.java     [implements Dimmable]
        │
        ├── TemperatureControllable.java [interface extends SmartClassroomDevice]
        │   └── void setTemperatureC(int c)
        │       └── AirConditioner.java  [implements TemperatureControllable]
        │
        └── AttendanceScannable.java     [interface extends SmartClassroomDevice]
            └── int scanAttendance()
                └── AttendanceScanner.java [implements AttendanceScannable]
```