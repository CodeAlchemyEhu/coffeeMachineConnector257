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