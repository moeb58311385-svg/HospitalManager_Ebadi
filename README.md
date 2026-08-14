# سیستم مدیریت بیمارستان (Hospital Management System)

## نحوه نصب و راه‌اندازی (Setup)

### 1. ایجاد پروژه در Eclipse

ابتدا پروژه را در محیط **Eclipse** ایجاد می‌کنیم:

**File → New → Java Project**

### 2. ساختار پروژه

فایل‌ها و کلاس‌های پروژه را بر اساس معماری **MVC** یا ساختار لایه‌ای زیر ایجاد می‌کنیم:

* **Data Layer**
* **Business Layer**
* **Presentation Layer**

سپس کلاس‌ها را با توجه به وظایف هر لایه و مطابق با نیازمندی‌های پروژه پیاده‌سازی می‌کنیم.

### 3. ساخت پایگاه داده در MySQL

ابتدا **WAMP** را نصب کرده و **MySQL Server** را اجرا می‌کنیم.

سپس یک پایگاه داده با نام `hospital_db` ایجاد می‌کنیم.

```sql
CREATE DATABASE hospital_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE hospital_db;

SOURCE database/hospital.sql;
```

فایل `database/hospital.sql` را روی پایگاه داده اجرا می‌کنیم تا جدول‌ها و داده‌های اولیه، شامل **بخش‌ها و پزشکان**، ایجاد شوند.

همچنین می‌توان فایل `hospital.sql` را از طریق **phpMyAdmin** و گزینه **Import** اجرا کرد.

### تنظیمات اتصال به پایگاه داده

تنظیمات اتصال به پایگاه داده در فایل زیر قرار دارد:

```text
src/data/DatabaseManager.java
```

تنظیمات اتصال:

```java
URL      = "jdbc:mysql://localhost:3306/hospital_db";
USERNAME = "root";
PASSWORD = "";
```

در این پروژه برای پایگاه داده نام کاربری و رمز عبور اختصاصی تعریف نشده است و از تنظیمات پیش‌فرض MySQL در WAMP استفاده شده است.

### 4. دریافت MySQL Connector/J

برای برقراری ارتباط بین برنامه Java و پایگاه داده MySQL، ابتدا **MySQL Connector/J** را از وب‌سایت رسمی MySQL دانلود می‌کنیم.

### 5. اضافه کردن JDBC Driver به پروژه

پس از دانلود **MySQL Connector/J**، فایل JAR مربوط به JDBC Driver را به **Build Path** پروژه اضافه می‌کنیم:

**Project → Properties → Java Build Path → Libraries → Add External JARs**

سپس فایل JAR مربوط به MySQL Connector/J را انتخاب می‌کنیم.

### 6. اجرای برنامه

برای اجرای برنامه، فایل زیر را در Eclipse باز می‌کنیم:

```text
src/presentation/Main.java
```

سپس:

**Right Click → Run As → Java Application**

---

## مشخصات محیط اجرا (Environment)

| مورد                   | نسخه / مقدار                 |
| ---------------------- | ---------------------------- |
| زبان برنامه‌نویسی      | Java                         |
| نسخه JDK               | Java 17                      |
| IDE                    | Eclipse 2021                 |
| پایگاه داده            | MySQL                        |
| JDBC Driver            | MySQL Connector/J            |
| رابط کاربری            | Java Swing (JFrame / JPanel) |
| نام کاربری پایگاه داده | `root`                       |
| رمز عبور پایگاه داده   | خالی                         |
| نام پایگاه داده        | `hospital_db`                |

