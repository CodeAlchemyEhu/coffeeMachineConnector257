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
