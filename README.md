# SemicircleProgress

## 1 效果图

![image](https://github.com/yizeliang/semicircleprgress/raw/master/img/1.png)


## 2 使用方法

```xml
   <cn.yzl.semicircleprgiress.library.SemicircleProgress
        app:progress="60"
        app:bg_start_color="#DB6958"
        app:bg_end_color="#0078D7"
        app:progress_start_color="#D8ED1A"
        app:progress_end_color="#383838"
        android:layout_width="match_parent"
        app:progress_width="10dp"
        android:layout_height="200dp"
        />
```

## 3 属性说明

|属性            | 说明                 |
| -------------------- | ----------------------------- |
| progress             | 进度 0-100                    |
| bg_start_color       | 未选中的背景色 渐变开始的颜色 |
| bg_end_color         | 未选中的背景色 渐变结束的颜色 |
| progress_start_color | 选中的背景色 渐变开始的颜色   |
| progress_end_color   | 选中的背景色 渐变结束的颜色   |

## 4 依赖添加


```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
dependencies {
	        compile 'com.github.yizeliang:semicircleprgress:1.0'
	}

```