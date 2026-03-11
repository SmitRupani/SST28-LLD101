```
SOLID/Ex5/src/
├── Main.java
├── SampleData.java
├── ExportRequest.java
├── ExportResult.java
│
└── Exporter.java                        [abstract class]
    ├── abstract boolean canExport(ExportRequest req)
    ├── abstract ExportResult export(ExportRequest req)
    └── String rejectionReason(ExportRequest req)
        ├── PdfExporter.java             [extends Exporter]
        ├── CsvExporter.java             [extends Exporter]
        └── JsonExporter.java            [extends Exporter]
```