# Day 28: 循序圖

## 循序圖（Sequence Diagram）

### 📝 什麼是循序圖？

循序圖展示**物件之間如何互動**，以及**訊息傳遞的時間順序**。它是動態行為圖中最常用的類型。

### 🎯 用途

- **展示互動流程**：物件之間如何協作完成功能
- **時間順序**：清楚表達訊息傳遞的先後順序
- **詳細設計**：從使用案例到實際實作的橋樑
- **除錯分析**：理解系統執行流程

---

## 🏗️ 循序圖的基本元素

### 1. 生命線（Lifeline）

**定義**：代表參與互動的物件或參與者

**表示法**：

```text
 物件名稱
    │
    │  ← 垂直虛線（生命線）
    │
    ▼
```

**命名格式**：

- `物件名稱 : 類別名稱`
- `:類別名稱` （匿名物件）
- `物件名稱` （只顯示名稱）

---

### 2. 啟動框（Activation Box）

**定義**：表示物件處於活躍狀態（正在執行操作）

**表示法**：生命線上的細長矩形

```text
 物件A
    │
    ├──┐  ← 啟動框（物件正在執行）
    │  │
    │  │
    ├──┘
    │
```

---

### 3. 訊息（Message）

**定義**：物件之間的通訊

**類型**：

| 類型           | 符號                   | 說明               |
| -------------- | ---------------------- | ------------------ |
| **同步訊息**   | `───>`                 | 發送者等待回應     |
| **非同步訊息** | `---->`                | 發送者不等待回應   |
| **回傳訊息**   | `<---`                 | 回傳結果           |
| **自我呼叫**   | `⟲`                    | 物件呼叫自己的方法 |
| **建立訊息**   | `---->` + `<<create>>` | 建立新物件         |
| **銷毀訊息**   | `----> ✕`              | 銷毀物件           |

---

### 4. 組合片段（Combined Fragments）

用於表達條件、迴圈等控制結構。

**類型**：

- `alt`：選擇（if-else）
- `opt`：可選（if）
- `loop`：迴圈
- `par`：平行執行
- `ref`：引用其他循序圖

**表示法**：

```text
┌─ alt ──────────────┐
│ [條件1]            │
│   執行流程1        │
├────────────────────┤
│ [else]             │
│   執行流程2        │
└────────────────────┘
```

---

## 範例 1：使用者登入系統

### 系統需求

實作一個使用者登入功能：

1. 使用者輸入帳號密碼
2. 系統驗證身分
3. 從資料庫查詢使用者資料
4. 驗證密碼
5. 回傳登入結果

### UML 循序圖

```text
使用者    LoginUI    AuthService    UserRepository    Database
  │          │           │                │              │
  │─輸入帳密─>│           │                │              │
  │          │           │                │              │
  │          │─驗證請求─>│                │              │
  │          │           │                │              │
  │          │           │─查詢使用者────>│              │
  │          │           │                │              │
  │          │           │                │─SQL查詢────>│
  │          │           │                │              │
  │          │           │                │<─User物件───│
  │          │           │                │              │
  │          │           │<─User物件──────│              │
  │          │           │                │              │
  │          │           │─────┐          │              │
  │          │           │     │驗證密碼  │              │
  │          │           │<────┘          │              │
  │          │           │                │              │
  │          │<─登入成功─│                │              │
  │          │           │                │              │
  │          │─────┐     │                │              │
  │          │     │顯示首頁              │              │
  │          │<────┘     │                │              │
  │          │           │                │              │
  │<─顯示首頁─│           │                │              │
  │          │           │                │              │
```

<!-- ### Java 程式碼實作

#### User 實體類別

```java
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
```

#### UserRepository 資料存取層

```java
public class UserRepository {
    private Database database;

    public UserRepository() {
        this.database = new Database();
    }

    public User findByUsername(String username) {
        System.out.println("UserRepository: 查詢使用者 " + username);
        return database.query("SELECT * FROM users WHERE username = ?", username);
    }
}
```

#### Database 資料庫類別

```java
public class Database {
    public User query(String sql, String username) {
        System.out.println("Database: 執行查詢 - " + sql);
        System.out.println("Database: 參數 - " + username);

        // 模擬資料庫查詢
        if ("john".equals(username)) {
            return new User(1L, "john", "password123", "john@example.com");
        }
        return null;
    }
}
```

#### AuthService 認證服務

```java
public class AuthService {
    private UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    public boolean authenticate(String username, String password) {
        System.out.println("AuthService: 開始驗證使用者 " + username);

        // 從資料庫查詢使用者
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("AuthService: 使用者不存在");
            return false;
        }

        // 驗證密碼
        return verifyPassword(user, password);
    }

    private boolean verifyPassword(User user, String password) {
        System.out.println("AuthService: 驗證密碼...");
        boolean isValid = user.getPassword().equals(password);

        if (isValid) {
            System.out.println("AuthService: 密碼正確");
        } else {
            System.out.println("AuthService: 密碼錯誤");
        }

        return isValid;
    }
}
```

#### LoginUI 使用者介面

```java
public class LoginUI {
    private AuthService authService;

    public LoginUI() {
        this.authService = new AuthService();
    }

    public void login(String username, String password) {
        System.out.println("=== 使用者輸入帳號密碼 ===");
        System.out.println("帳號: " + username);
        System.out.println("密碼: " + "***");
        System.out.println();

        boolean success = authService.authenticate(username, password);

        if (success) {
            displayHomePage();
        } else {
            displayError();
        }
    }

    private void displayHomePage() {
        System.out.println();
        System.out.println("=== 登入成功！顯示首頁 ===");
        System.out.println("歡迎回來！");
    }

    private void displayError() {
        System.out.println();
        System.out.println("=== 登入失敗 ===");
        System.out.println("帳號或密碼錯誤");
    }
}
```

#### 測試程式

```java
public class LoginExample {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();

        // 測試成功登入
        System.out.println("【測試 1：正確的帳號密碼】");
        loginUI.login("john", "password123");

        System.out.println("\n" + "=".repeat(50) + "\n");

        // 測試登入失敗
        System.out.println("【測試 2：錯誤的密碼】");
        loginUI.login("john", "wrongpassword");
    }
}
```

#### 執行結果

```text
【測試 1：正確的帳號密碼】
=== 使用者輸入帳號密碼 ===
帳號: john
密碼: ***

AuthService: 開始驗證使用者 john
UserRepository: 查詢使用者 john
Database: 執行查詢 - SELECT * FROM users WHERE username = ?
Database: 參數 - john
AuthService: 驗證密碼...
AuthService: 密碼正確

=== 登入成功！顯示首頁 ===
歡迎回來！

==================================================

【測試 2：錯誤的密碼】
=== 使用者輸入帳號密碼 ===
帳號: john
密碼: ***

AuthService: 開始驗證使用者 john
UserRepository: 查詢使用者 john
Database: 執行查詢 - SELECT * FROM users WHERE username = ?
Database: 參數 - john
AuthService: 驗證密碼...
AuthService: 密碼錯誤

=== 登入失敗 ===
帳號或密碼錯誤
```

--- -->

## 範例 2：線上購物結帳流程

### 系統需求

實作線上購物的結帳功能：

1. 使用者發起結帳
2. 系統檢查登入狀態
3. 系統檢查購物車
4. 系統計算總金額
5. 使用者選擇付款方式
6. 系統處理付款
7. 系統建立訂單
8. 系統更新庫存
9. 系統發送確認郵件

### UML 循序圖（含條件判斷）

```text
顧客  CheckoutUI  OrderService  CartService  PaymentService  InventoryService  EmailService
 │       │            │             │              │                 │               │
 │─結帳─>│            │             │              │                 │               │
 │       │            │             │              │                 │               │
 │       │─處理結帳──>│             │              │                 │               │
 │       │            │             │              │                 │               │
 │       │            │─取得購物車─>│              │                 │               │
 │       │            │             │              │                 │               │
 │       │            │<─購物車資料─│              │                 │               │
 │       │            │             │              │                 │               │
 │       │            ├─ alt ───────┤              │                 │               │
 │       │            │ [購物車空]  │              │                 │               │
 │       │<─錯誤訊息──│             │              │                 │               │
 │       │            ├─────────────┤              │                 │               │
 │       │            │ [else]      │              │                 │               │
 │       │            │─────┐       │              │                 │               │
 │       │            │     │計算總額               │                 │               │
 │       │            │<────┘       │              │                 │               │
 │       │            │             │              │                 │               │
 │       │<─顯示金額──│             │              │                 │               │
 │       │            │             │              │                 │               │
 │─選擇付款>│          │             │              │                 │               │
 │       │            │             │              │                 │               │
 │       │─付款請求──>│             │              │                 │               │
 │       │            │             │              │                 │               │
 │       │            │─處理付款─────────────────>│                 │               │
 │       │            │             │              │                 │               │
 │       │            │<─付款成功─────────────────│                 │               │
 │       │            │             │              │                 │               │
 │       │            │─建立訂單──┐ │              │                 │               │
 │       │            │           │ │              │                 │               │
 │       │            │<──────────┘ │              │                 │               │
 │       │            │             │              │                 │               │
 │       │            │─更新庫存─────────────────────────────────>│               │
 │       │            │             │              │                 │               │
 │       │            │─發送郵件─────────────────────────────────────────────────>│
 │       │            │             │              │                 │               │
 │       │<─訂單確認──│             │              │                 │               │
 │       │            │             │              │                 │               │
 │<─顯示成功│          │             │              │                 │               │
 │       │            │             │              │                 │               │
```

<!-- ### 簡化的 Java 實作

```java
// OrderService
public class OrderService {
    private CartService cartService;
    private PaymentService paymentService;
    private InventoryService inventoryService;
    private EmailService emailService;

    public OrderService() {
        this.cartService = new CartService();
        this.paymentService = new PaymentService();
        this.inventoryService = new InventoryService();
        this.emailService = new EmailService();
    }

    public Order processCheckout(String userId, String paymentMethod) {
        System.out.println("OrderService: 開始處理結帳...");

        // 取得購物車
        Cart cart = cartService.getCart(userId);

        // 檢查購物車
        if (cart.isEmpty()) {
            throw new RuntimeException("購物車是空的！");
        }

        // 計算總金額
        double totalAmount = calculateTotal(cart);
        System.out.println("OrderService: 計算總金額 = $" + totalAmount);

        // 處理付款
        boolean paymentSuccess = paymentService.processPayment(userId, totalAmount, paymentMethod);

        if (!paymentSuccess) {
            throw new RuntimeException("付款失敗！");
        }

        // 建立訂單
        Order order = createOrder(userId, cart, totalAmount);
        System.out.println("OrderService: 訂單建立成功，訂單號：" + order.getOrderId());

        // 更新庫存
        inventoryService.updateInventory(cart);

        // 發送確認郵件
        emailService.sendOrderConfirmation(userId, order);

        return order;
    }

    private double calculateTotal(Cart cart) {
        return cart.getItems().stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
    }

    private Order createOrder(String userId, Cart cart, double totalAmount) {
        String orderId = "ORD-" + System.currentTimeMillis();
        return new Order(orderId, userId, cart, totalAmount);
    }
}

// CheckoutUI
public class CheckoutUI {
    private OrderService orderService;

    public CheckoutUI() {
        this.orderService = new OrderService();
    }

    public void checkout(String userId, String paymentMethod) {
        System.out.println("=== 顧客發起結帳 ===\n");

        try {
            Order order = orderService.processCheckout(userId, paymentMethod);
            displaySuccess(order);
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    private void displaySuccess(Order order) {
        System.out.println("\n=== 結帳成功！ ===");
        System.out.println("訂單編號：" + order.getOrderId());
        System.out.println("訂單金額：$" + order.getTotalAmount());
        System.out.println("感謝您的購買！");
    }

    private void displayError(String message) {
        System.out.println("\n=== 結帳失敗 ===");
        System.out.println("錯誤：" + message);
    }
}
``` -->

---

## 🎓 最佳實踐

### 循序圖

✅ **好的做法**：

1. 由左至右排列物件，按照呼叫順序
2. 使用啟動框清楚標示物件的活躍期
3. 適當使用組合片段表達條件和迴圈
4. 訊息名稱要清晰，最好對應實際方法名

❌ **避免的錯誤**：

1. 一張圖包含太多物件（超過 7-8 個）
2. 過於複雜的巢狀結構
3. 缺少回傳訊息
4. 忽略異常情況的處理

---

## 🎯 使用案例圖 vs 循序圖

### 對比分析

| 特性         | 使用案例圖     | 循序圖        |
| ------------ | -------------- | ------------- |
| **視角**     | 外部使用者視角 | 內部系統視角  |
| **關注點**   | 做什麼（What） | 怎麼做（How） |
| **抽象層級** | 高層次、概覽   | 詳細、具體    |
| **時間順序** | 不強調         | 強調時間先後  |
| **適用階段** | 需求分析       | 設計階段      |
| **目標受眾** | 客戶、產品經理 | 開發團隊      |

### 如何搭配使用？

1. **需求分析階段**：繪製使用案例圖，識別系統功能
2. **詳細設計階段**：為每個使用案例繪製循序圖，設計實作細節
3. **開發階段**：根據循序圖實作程式碼
4. **測試階段**：根據使用案例圖和循序圖設計測試案例
