# Dropdown Menu View

Use popup window to choose data as easy as ListView

## update 1.0.4

解决在低版本上面点击事件不响应的问题。

## update 1.0.3

1. 添加手动弹出和关闭的方法
2. 添加管理工具类来实现同时只允许一个窗口为弹出状态

## update 1.0.2

1. 选择其中一项后改变标题文字（需要适配器实现 DropdownAdapter 接口）
2. 在 PopupWindow 打开时点击标题收起
3. Java 中设置默认标题文字

## Usage

layout code:

```xml
<com.twiceyuan.dropdown_menu.DropdownMenu
        android:id="@+id/dm_dropdown"
        android:layout_weight="1"
        android:layout_width="0dp"
        android:layout_height="48dp"
        app:iconColor="dark"
        app:titleText="Select Hero"
        app:titleTextSize="16"/>
```

java code:

```java
        // Bind layout id
        DropdownMenu menu1 = (DropdownMenu) findViewById(R.id.dm_dropdown);

        // Configure adapter
        menu1.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Iron Man", "Ant Man", "American Captain", "Hulk"}
        ));

        // Configure click listener
        menu1.setOnItemClickListener(new OnDropdownItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), strings[position], Toast.LENGTH_SHORT).show();
            }
        });

        // At the same time only allows a window pops up
        MenuManager.single(menu1, menu2);
```

```groovy
repositories {
    maven { url 'https://raw.githubusercontent.com/twiceyuan/DropdownMenu/master/repository' }
    flatDir {
        dirs 'aars'
    }
}

dependencies {
    //...
    compile 'com.twiceyuan.dropdown-menu:dropdown-menu:1.0.3'
    //...
}

```

## Screenshot

![1](screenshot/screenshot1.png)
![2](screenshot/screenshot2.png)
![3](screenshot/screenshot3.png)