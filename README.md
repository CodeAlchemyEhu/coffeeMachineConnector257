# ☕ Coffee Machine Middleware

## 🎯 Goal
Design and implement a **middleware** between the `OrderDesk` (front-end) and the `CoffeeMachine` interface.  
The middleware must translate orders into commands for the coffee machine.

---

## 🧠 Task Description

Implement a middleware layer (the `CoffeeMachineController`) that connects the `OrderDesk` and the `CoffeeMachine`.

1. The controller must **interpret each drink** name and send the correct preparation command to the coffee machine.

2. The configuration for each coffee type (e.g. espresso, latte, cappuccino) should not be hard-coded directly in the controller.

3. Use **creational design patterns** to build a flexible and extensible design.

4. Each coffee type should define:

   - Amount of **water (ml)**

   - Amount of **coffee (g)**

    - Amount of **milk (ml)**

5. Send command to the machine in the format:
```
"<water>ml <coffee>g <milk>ml"
```
6. Each region-specific factory must return versions of coffee with **region-dependent ingredient values** (e.g. Italian espresso = *stronger*, French cappuccino = *milkier*, etc.).

7. 🌍 Regional Recipe Table:

| Region             | Espresso (W/C/M) | Cappuccino (W/C/M)  | Latte (W/C/M)       | Notes                |
| ------------------ | ---------------- | ------------------- | ------------------- | -------------------- |
| **USA** 🇺🇸       | 90ml / 14g / 0ml | 250ml / 12g / 180ml | 300ml / 12g / 250ml | Larger, lighter brew |
| **Japan** 🇯🇵     | 45ml / 17g / 0ml | 180ml / 15g / 80ml  | 220ml / 14g / 180ml | Subtle and refined   |

---

## 📝 New task for structural design patterns

### ✔ 1. Add Toppings Support
Extend the coffee order logic so a user can request toppings, for example:
```
latte cream caramel
```
The list of toppings:
- **Caramel**
- **Cream**
- **Liquor**

Toppings can be combined

### ✔ 2. Maintain Backward Compatibility

Old connector: must still work exactly as before and support topping functionality

New connector: must fully support toppings

Your implementation must ensure the system can work with either connector without breaking existing functionality.



### ✔ 3. ☕ NewCoffeeMachineConnector – Overview

`NewCoffeeMachineConnector` is a connector class that simulates communication with a coffee machine device.
It implements the `CoffeeMachineV75` interface and provides a controlled workflow for interacting with the machine.

Typical lifecycle:
```
1. getToken()
2. openSession(token)
3. makeCoffee(token, session, "200ml 15g 80ml caramel")
4. closeSession(token, session)
```
The connector supports the following operations:

**Requesting a token** – retrieves a unique authentication token for connector.

**Opening a session** – establishes a session using the provided token.

**Preparing coffee** – performs a simulated coffee preparation within an active session.

**Closing the session** – gracefully ends the active session.

Additionally, the connector implements strict validation rules to ensure proper usage:

Only one session can be open at any time

This behavior mimics real-world external device integrations where authentication, session control, and state validation are required.

---

## 📝 New task for behavioral design patterns p.1


### 🧠 Task – Order Price Calculation
Extend the coffee ordering system to **calculate the final order price** dynamically, depending on:

- coffee type
- region
- applied discount rules

Use **stratagy** pattern


#### ✔ Description

Each coffee order must be able to calculate its **base price** and then apply **one discount strategy**.


#### ✔ Regional Coffee Price Table

#### ☕ Base Coffee Prices (USD)

| Region             | Espresso | Cappuccino | Latte |
|-------------------|----------|------------|-------|
|  **USA**      | $2.00    | $3.50      | $4.00 |
| **Japan**  | $1.80    | $3.20      | $3.80 |


#### 🍯 Topping Prices (EUR)

| Topping  | Price |
|---------|-------|
| Caramel | $0.90 |
| Cream   | $0.50 |
| Liquor  | $0.70 |

- Toppings can be combined
- Each topping adds its price to the base coffee price

#### ✔ Discount Strategies

Only **one discount** may be applied per order.

| Discount Type       | Rule |
|---------------------|------|
| **None**            | No discount |
| **Student** 🎓      | 20% off total price |
| **Loyalty Card** 💳 | 10% off total price |



#### ✔ Example Usage

```
student latte cream caramel

none espresso
```

### 🧠 Task – Order Processing Pipeline

#### 🎯 Goal
Refactor the order processing logic into a **step-by-step processing pipeline** where each step is responsible for **exactly one concern**.

Use **Chain of Responsibility** pattern

#### ✔ Description

Processing a coffee order involves multiple sequential actions, such as (examples):

- parsing the input string
- identifying coffee type
- applying toppings
- applying discount rules

### 🧠 Task – Coffee Machine Connector States

### 🎯 Goal
Enhance the `CoffeeMachineConnector` to behave differently depending on its **internal state**, simulating a real-world unstable external device.

The connector must automatically switch between states based on **successes and failures** during operation.

Use **state** pattern


#### ✔ Description

The coffee machine connector must operate in **three distinct states**:

1. **OPEN**
2. **CLOSED**
3. **SEMI-CLOSED**

Each state defines how the connector reacts to incoming coffee preparation requests.

#### ✔ State Definitions & Rules

##### 🟢 OPEN State
- Normal operating mode
- All requests are executed normally
- If **2 exceptions occur processing**:
   - the connector switches to **CLOSED** state

##### 🔴 CLOSED State
- Protective mode
- The connector **ignores the next 5 incoming calls**
- Ignored calls:
   - must not reach the real coffee machine
- After 5 ignored calls:
   - the connector switches to **SEMI-CLOSED** state

##### 🟡 SEMI-CLOSED State
- Testing mode
- The connector allows **exactly one request** to pass through
- If the request:
   - **succeeds** → switch to **OPEN**
   - **fails** → switch back to **CLOSED**

---
## 📝 New task for behavioral design patterns p.2

### 🧠 New Task – Order Events & Listeners (Observer Pattern)
#### 🎯 Goal

Extend the ordering system, so it can react to order completion events without changing the existing order processing logic.

**Use the Observer (Listener) design pattern.**

#### 🧩 Core Idea

When an order is successfully prepared, or fails during processing the system must notify registered listeners.

#### ✔ Required Listener Implementations

##### 📚 1. OrderHistoryListener

Responsibility:
Store the history of all processed orders. History must include both successful and failed orders.

##### 🔔 2. NotificationListener

Responsibility:
Simulate user notifications. Output to console is enough.

## 🧠 Task – Order History Processing (Visitor Pattern)

### 🎯 Goal
Extend the order history subsystem so that **multiple independent operations** can be performed on stored order history **without modifying the order or history classes**.

Use the **Visitor design pattern**.

### 🧩 Core Idea

The system already stores a list of processed orders in `OrderHistory`.

New requirements appear:
- calculate statistics
- generate reports

Instead of:
- adding methods to `Order`
- or adding `if` / `switch` logic in `OrderHistory`

use the **Visitor pattern** to add new behaviors **without changing existing classes**.

### ✔ Required Visitor Implementations
#### 📊 1. StatisticsVisitor

Responsibility:

count total orders

count successful vs failed orders

Discounts usage

Calculate total revenue

Average order price

Anything that seems interesting to you


#### 📝 2. ReportVisitor

Responsibility:

generate a human-readable report from order history

Example output:

```
Orders processed: 42
Successful: 38
Failed: 4

Topping usage: 
-- // -- : 12
-- // -- : 4
```

📌 Notes:

Output to console or string is sufficient
