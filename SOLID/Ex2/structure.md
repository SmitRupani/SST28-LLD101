```
SOLID/Ex2/src/
├── Main.java
├── CafeteriaSystem.java
├── InvoiceService.java
├── PricingService.java
├── PolicyResolver.java
├── Menu.java
├── MenuItem.java
├── OrderLine.java
├── Invoice.java
├── InvoiceLine.java
├── CustomerPolicy.java
│
├── TaxPolicy.java                       [interface]
│   ├── double calculateTax(double amount)
│   └── double getPercent()
│       ├── StudentTaxPolicy.java        [implements TaxPolicy]
│       ├── StaffTaxPolicy.java          [implements TaxPolicy]
│       └── DefaultTaxPolicy.java        [implements TaxPolicy]
│
├── DiscountPolicy.java                  [interface]
│   └── double calculate(double subtotal, int distinctLines)
│       ├── StudentDiscountPolicy.java   [implements DiscountPolicy]
│       ├── StaffDiscountPolicy.java     [implements DiscountPolicy]
│       └── DefaultDiscountPolicy.java   [implements DiscountPolicy]
│
├── InvoiceFormatter.java                [interface]
│   └── String format(Invoice invoice)
│       └── PlainTextInvoiceFormatter.java [implements InvoiceFormatter]
│
└── InvoiceStore.java                    [interface]
    ├── void save(String id, String content)
    └── int countLines(String id)
        └── FileStore.java               [implements InvoiceStore]
```