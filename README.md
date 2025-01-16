# Login with SQLite - Android Project

این پروژه یک برنامه ساده ورود و ثبت‌نام کاربران در اندروید است که اطلاعات کاربران را در دیتابیس SQLite ذخیره می‌کند. کاربران می‌توانند با وارد کردن نام کاربری و رمز عبور خود ثبت‌نام کرده و سپس وارد سیستم شوند.

## ویژگی‌ها:
- **ثبت‌نام کاربر**: کاربران می‌توانند نام کاربری و رمز عبور خود را وارد کرده و در دیتابیس ذخیره کنند.
- **ورود کاربر**: کاربران می‌توانند با استفاده از نام کاربری و رمز عبور خود وارد سیستم شوند.
- **SQLite**: تمامی اطلاعات کاربران در یک پایگاه داده محلی SQLite ذخیره می‌شود.

## پیش‌نیازها:
- **Android Studio**: برای اجرای این پروژه نیاز به Android Studio دارید.
- **اندروید نسخه 5.0 به بالا**: پروژه برای نسخه‌های ۵.۰ Lollipop و بالاتر طراحی شده است.

## ساختار پروژه:
این پروژه از یک دیتابیس SQLite برای ذخیره‌سازی اطلاعات کاربران استفاده می‌کند. در اینجا، کلاس `DatabaseHelper` برای مدیریت عملیات دیتابیس و ذخیره‌سازی داده‌ها استفاده می‌شود.

### دیتابیس:

در این پروژه، جدول **Users** برای ذخیره اطلاعات کاربران به شکل زیر طراحی شده است:

```sql
CREATE TABLE Users(
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    USERNAME TEXT,
    PASSWORD TEXT
);
```

### نحوه استفاده:

#### 1. **ایجاد کلاس DatabaseHelper**

کلاس `DatabaseHelper` برای مدیریت عملیات دیتابیس تعریف شده است. این کلاس مسئول ایجاد، آپدیت و حذف جداول در دیتابیس است.

```java
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Login.db";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    // متد برای درج داده‌های کاربر
    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        long result = db.insert("Users", null, contentValues);
        return result != -1;
    }

    // متد برای چک کردن ورود کاربر
    public String checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_2};
        String selection = "USERNAME=? AND PASSWORD=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);
        String result = null;
        if (cursor != null && cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(COL_2));
            cursor.close();
        }
        return result;
    }
}
```

#### 2. **درج داده در دیتابیس**:
برای درج اطلاعات کاربر (نام کاربری و رمز عبور) از متد `insertData` استفاده می‌شود.

```java
DatabaseHelper dbHelper = new DatabaseHelper(context);
boolean isInserted = dbHelper.insertData("username123", "password456");
if (isInserted) {
    Toast.makeText(context, "User registered successfully!", Toast.LENGTH_SHORT).show();
} else {
    Toast.makeText(context, "Registration failed.", Toast.LENGTH_SHORT).show();
}
```

#### 3. **چک کردن ورود کاربر**:
برای ورود کاربران و اعتبارسنجی نام کاربری و رمز عبور از متد `checkLogin` استفاده می‌شود.

```java
DatabaseHelper dbHelper = new DatabaseHelper(context);
String result = dbHelper.checkLogin("username123", "password456");
if (result != null) {
    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show();
} else {
    Toast.makeText(context, "Invalid username or password.", Toast.LENGTH_SHORT).show();
}
```

## نحوه اجرا:

1. ابتدا پروژه را در **Android Studio** باز کنید.
2. در صورت لزوم، نسخه اندروید مناسب را انتخاب کنید.
3. برنامه را اجرا کرده و ویژگی‌های ورود و ثبت‌نام را تست کنید.

## نکات مهم:
- در این پروژه از دیتابیس SQLite برای ذخیره اطلاعات استفاده شده است.
- برای افزایش امنیت، در دنیای واقعی باید رمز عبور کاربر را با روش‌های ایمن‌تری مانند هش کردن ذخیره کرد.
- این پروژه برای اهداف آموزشی طراحی شده است.

## توسعه‌ها:
- **هش کردن رمز عبور**: اضافه کردن قابلیت هش کردن رمز عبور کاربران برای امنیت بیشتر.
- **صفحه فراموشی رمز عبور**: افزودن صفحه‌ای برای بازیابی رمز عبور در صورت فراموشی.
- **ورود با استفاده از ایمیل**: افزودن قابلیت ورود با استفاده از ایمیل به جای نام کاربری.


---

### توضیحات اضافی:

- این فایل README به طور کامل جزئیات مربوط به پروژه را پوشش می‌دهد.
- نحوه استفاده، توضیحات فنی و کدهای نمونه برای عملیات‌های اصلی (ثبت‌نام و ورود) آورده شده است.
- همچنین در این فایل پیشنهاداتی برای توسعه و امنیت بیشتر (مثل هش کردن رمز عبور) وجود دارد.

شما می‌توانید تصاویر، لینک‌ها و ایمیل‌های خود را با توجه به نیازتان به این فایل اضافه کنید.
