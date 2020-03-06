##  LitePager，一个轻量级的ViewPager，仿新版网易云歌单广场
### 博客详情： <https://blog.csdn.net/u011387817/article/details/89142467>

### 使用方式:
#### 添加依赖：
```
implementation 'com.wuyr:litepager:1.3.0'
```

### APIs:
|Method|Description|
|------|-----------|
|addViews(int... layouts)|批量添加子View|
|addViews(View... views)|批量添加子View|
|setSelection(View target)|选中指定子View|
|setSelection(int index)|根据索引选中子View|
|setOrientation(int orientation)|设置滑动方向(默认: ORIENTATION_HORIZONTAL):<br>**ORIENTATION_HORIZONTAL**(水平)<br>**ORIENTATION_VERTICAL**(垂直)|
|setFlingDuration(long duration)|设置动画的时长|
|setTopScale(float scale)|设置**顶层**缩放比例|
|setTopAlpha(float alpha)|设置**顶层**不透明度|
|setMiddleScale(float scale)|设置**中层**缩放比例|
|setMiddleAlpha(float alpha)|设置**中层**不透明度|
|setBottomScale(float scale)|设置**底层**缩放比例|
|setBottomAlpha(float alpha)|设置**底层**不透明度|
|setOnScrollListener(OnScrollListener listener)|设置滚动状态监听:<br>**STATE_IDLE**(静止状态)<br>**STATE_DRAGGING_LEFT**(向左拖动)<br>**STATE_DRAGGING_RIGHT**(向右拖动)<br>**STATE_DRAGGING_TOP**(向上拖动)<br>**STATE_DRAGGING_BOTTOM**(向下拖动)<br>**STATE_SETTLING_LEFT**(向左调整)<br>**STATE_SETTLING_RIGHT**(向右调整)<br>**STATE_SETTLING_TOP**(向上调整)<br>**STATE_SETTLING_BOTTOM**(向下调整)<br>|
|setOnItemSelectedListener(SelectedListener listener) |设置子View被选中的监听|
|getSelectedChild() |获取当前选中的子View|
|setAutoScrollEnable(boolean enable) |设置是否开启自动轮播 (默认: false)|
|setAutoScrollInterval(long interval) |设置自动轮播的间隔 (默认: 5000 ms)|
|setAutoScrollOrientation(int orientation) |设置自动轮播的方向(默认: SCROLL_ORIENTATION_LEFT):<br>**SCROLL_ORIENTATION_LEFT**(向左滚动)<br>**SCROLL_ORIENTATION_RIGHT**(向右滚动)<br>**SCROLL_ORIENTATION_UP**(向上滚动)<br>**SCROLL_ORIENTATION_DOWN**(向下滚动)<br>|
|setAdapter(Adapter adapter)|使用Adapter来添加子View(见下)|

### Attributes:
|Name|Format|Description|
|----|-----|-----------|
|orientation|enum (默认: horizontal)<br>**horizontal**(水平)<br>**vertical**(垂直)|滑动方向|
|flingDuration|integer|动画时长|
|topScale|float (默认: 1)|**顶层**缩放比例|
|topAlpha|float (默认: 1)|**顶层**不透明度|
|middleScale|float (默认: 0.8)|**中层**缩放比例|
|middleAlpha|float (默认: 0.4)|**中层**不透明度|
|bottomScale|float (默认: 0.6)|**底层**缩放比例|
|bottomAlpha|float (默认: 0.2)|**底层**不透明度|
|autoScroll|boolean (默认: false)|是否开启自动轮播|
|autoScrollInterval|float (默认: 5000)|自动轮播的间隔|
|autoScrollOrientation|enum (默认: left)<br>**left**(左)<br>**right**(右)<br>**up**(上)<br>**down**(下)|自动轮播的方向|

### 添加子View方式：
#### 1. XML

```xml
    <com.wuyr.litepager.LitePager
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <View
            android:layout_width="150dp"
            android:layout_height="200dp"
            android:background="#F00" />

        <View
            android:layout_width="150dp"
            android:layout_height="200dp"
            android:background="#0F0"/>

        <View
            android:layout_width="150dp"
            android:layout_height="200dp"
            android:background="#00F"/>
    </com.wuyr.litepager.LitePager>
```

#### 2. 批量添加

```java
    LitePager litePager = ...;
    View child1 = ...;
    View child2 = ...;
    View child3 = ...;

    litePager.addViews(child1, child2, child3);
```

#### 3. 通过布局添加
```java
    litePager.addViews(
        R.layout.view_child1 
        R.layout.view_child2,
        R.layout.view_child3
    );
```

#### 4. 设置适配器
```java
    litePager.setAdapter(new Adapter<ViewGroup>() {

        private List<String> mData = new ArrayList<>(Arrays.asList("Item 1", "Item2", "Item3"));

        @Override
        protected ViewGroup onCreateView(@NonNull ViewGroup parent) {
            return (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent);
        }

        @Override
        protected void onBindView(@NonNull ViewGroup viewGroup, int position) {
            TextView textView = viewGroup.findViewById(R.id.text);
            textView.setText(mData.get(position));
        }

        @Override
        protected int getItemCount() {
            return mData.size();
        }
    });
```

<br>

### Demo下载: [app-debug.apk](https://github.com/wuyr/LitePager/raw/master/app-debug.apk)
### 库源码地址： <https://github.com/Ifxcyr/LitePager>

### 效果 (图1为网易云原效果)：
![preview](https://github.com/wuyr/LitePager/raw/master/previews/preview1.gif) ![preview](https://github.com/wuyr/LitePager/raw/master/previews/preview2.gif)
![preview](https://github.com/wuyr/LitePager/raw/master/previews/preview3.gif) ![preview](https://github.com/wuyr/LitePager/raw/master/previews/preview4.gif)
