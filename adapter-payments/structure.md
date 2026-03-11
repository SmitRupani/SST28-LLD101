```
adapter-payments/src/com/example/payments/
├── App.java
├── OrderService.java
├── FastPayClient.java
├── SafeCashClient.java
├── SafeCashPayment.java
│
└── PaymentGateway.java                  [interface]
    └── String charge(String customerId, int amountCents)
        ├── FastPayAdapter.java          [implements PaymentGateway]
        └── SafeCashAdapter.java         [implements PaymentGateway]
```