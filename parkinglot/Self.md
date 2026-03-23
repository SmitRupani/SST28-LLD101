Alright — let’s walk through this like you would **explain in an interview from start → finish**, with **clear flow, reasoning, and design decisions**.

---

# 🧠 1. Problem Understanding

We are designing a **Multilevel Parking Lot System** with:

### ✅ Key Requirements

* 3 slot types: **Small, Medium, Large**
* Multiple **entry gates**
* Assign **nearest available slot (based on gate)**
* Allow **upcasting**:

  * Bike → Small / Medium / Large
  * Car → Medium / Large
  * Bus → Large
* Billing based on **slot type**
* APIs:

  * `park()`
  * `exit()`
  * `status()`

---

# 🚨 2. Core Challenge

👉 The hardest requirement is:

> “Always assign the **nearest available compatible slot** based on entry gate”

---

# 💡 3. Key Design Decision

Instead of scanning all slots every time ❌ (O(n))
We use:

👉 **TreeSet (Sorted Set) per gate per slot type**

---

# 🏗️ 4. High-Level Approach

## For each Entry Gate:

Maintain **3 TreeSets**:

```text
Gate A:
   Small Slots  → TreeSet (sorted by distance)
   Medium Slots → TreeSet
   Large Slots  → TreeSet
```

---

## 🧩 Why TreeSet?

* Always gives **nearest slot → O(log n)**
* Supports **removal → O(log n)**
* No stale data (unlike heaps)

---

# 📦 5. Handling Precomputed Distances

Since distances are already given:

👉 We **don’t store distance in ParkingSlot**

Instead:

### Introduce Wrapper

```text
GateSlotWrapper = (slot, distance)
```

---

## 💥 Why this is important

Same slot has different distances from different gates:

```text
Slot S1:
   Gate A → 5m
   Gate B → 20m
```

👉 So distance is **gate-specific**, not slot-specific

---

# 🧱 6. Core Components

---

## 🚗 Vehicle

* vehicleId
* type (Bike, Car, Bus)

---

## 🅿️ ParkingSlot

* slotId
* slotType

---

## 🌳 GateSlotWrapper

* ParkingSlot
* distance (precomputed)

---

## 🚪 EntryGate

Contains:

```text
TreeSet<GateSlotWrapper> smallSlots
TreeSet<GateSlotWrapper> mediumSlots
TreeSet<GateSlotWrapper> largeSlots
```

Sorted by:

```text
(distance, slotId)
```

---

## 🎫 ParkingTicket

* ticketId
* vehicle
* slot
* entryTime

---

## 💰 PricingStrategy

* Calculates bill based on:

  * slot type
  * duration

---

## 🏢 ParkingLot (Controller)

Handles:

* parking
* exit
* slot allocation
* tracking tickets

---

# 🔄 7. Full Flow

---

# 🚗 FLOW 1: Vehicle Entry (park)

### Step 1: Input

```text
(vehicle, entryTime, gateId)
```

---

### Step 2: Fetch Gate

```text
EntryGate gate = gates.get(gateId)
```

---

### Step 3: Find Compatible Slot

#### Rules:

| Vehicle | Try Order              |
| ------- | ---------------------- |
| Bike    | Small → Medium → Large |
| Car     | Medium → Large         |
| Bus     | Large                  |

---

### Step 4: Get Nearest Slot

```text
wrapper = set.first()
```

👉 This is the **nearest available slot**

---

### Step 5: Remove from TreeSet

```text
set.remove(wrapper)
```

---

### Step 6: Create Ticket

```text
ticket = (vehicle, slot, entryTime)
```

Store in:

```text
activeTickets
```

---

### ✅ Result

Ticket returned to user

---

# 🚙 FLOW 2: Vehicle Exit (exit)

---

### Step 1: Fetch Ticket

```text
ticket = activeTickets.remove(ticketId)
```

---

### Step 2: Calculate Bill

```text
amount = pricingStrategy.calculate(...)
```

Based on:

* entryTime
* exitTime
* slotType

---

### Step 3: Free Slot

We must reinsert slot into **ALL gates**

---

### Step 4: Why ALL gates?

Because each gate has its own:

```text
(slot → distance)
```

---

### Step 5: Reinsert Using Wrapper Map

```text
wrapper = gateSlotMap[gateId][slotId]
```

---

### Step 6: Add Back to TreeSet

```text
set.add(wrapper)
```

---

### ✅ Result

Slot becomes available again

---

# 📊 FLOW 3: Status

For each gate:

```text
Small → size
Medium → size
Large → size
```

---

# ⚡ 8. Complexity Analysis

| Operation      | Complexity           |
| -------------- | -------------------- |
| park()         | O(log n)             |
| exit()         | O(gates × log n)     |
| nearest lookup | O(1) (first element) |

---