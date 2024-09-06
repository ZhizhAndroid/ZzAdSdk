### ZzAdSdk集成步骤

* 如果你的项目 Gradle 配置是在 `7.0 以下`，需要在 `build.gradle` 文件中加入

```groovy
allprojects {
    repositories {
        // JitPack 远程仓库：https://jitpack.io
        maven { url 'https://jitpack.io' }
    }
}
```

* 如果你的 Gradle 配置是 `7.0 及以上`，则需要在 `settings.gradle` 文件中加入

```groovy
dependencyResolutionManagement {
    repositories {
        // JitPack 远程仓库：https://jitpack.io
        maven { url 'https://jitpack.io' }
    }
}
```

* 配置完远程仓库后，在项目 app 模块下的 `build.gradle` 文件中加入远程依赖

```groovy
dependencies {
    implementation 'com.github.ZhizhAndroid:ZzAdSdk:0.1'
}
```

* ZzAdSdk 框架混淆规则

```text
# ZzAdSdk 框架混淆规则
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.zhizh.ad.tv.http.model.** { *; }
-keep interface com.zhizh.ad.tv.listener.** { *; }
-dontwarn com.zhizh.ad.tv.**
```

### 框架初始化

* 需要传入dev_key进行sdk的初始化，在应用的Application类onCreate方法中进行初始化

```java
ZzAdSdk.getInstance().initialize(this, dev_key);
```

### 设置用户userId

* 对于有用户账号体系的产品，通过设置用户userid，可以将广告产生的效果和单个用户进行追踪

```java
ZzAdSdk.getInstance().initialize(this, dev_key);
```

### 获取原生广告数据

* 调用获取广告方法，可以获取原生广告数据，开发者可以自由的展示广告

```java
ZzAdSdk.getInstance().getListOfAds(new OnLoadAdsListener() {
    @Override
    public void onLoadAdsSuccess(List<ZzNativeAd> offerList) {
        
    }

    @Override
    public void onLoadAdsFail(Exception e) {

    }
});
### ZzNativeAd类的字段解释
```

### ZzNativeAd实体类字段解释

| 字段名称      | 数据类型   | 必传 | 描述         | 备注               |
|-----------|--------|----|------------|------------------|
| offer_id  | String | 必传 | 广告id       |                  |
| title     | String | 必传 | 广告标题       |                  |
| appid     | String | 必传 | 广告包名       |                  |
| desc      | String | 选传 | 广告描述       |                  |
| icon      | String | 必传 | 广告icon     |                  |
| banner    | String | 选传 | 广告banner大图 |                  |
| rating    | float  | 必传 | 广告评分       |                  |
| click_url | float  | 必传 | 点击链接       | 对于手机端可以直接拉起浏览器跳转 |
| qr_code   | Bitmap | 必传 | 跳转二维码链接    | 对于电视端用于展示跳转的二维码  |

### 广告展示上报

* 为了便于统计广告效果，在需要展示的RecyclerView或者ListView中监听广告的展示情况，需要调用以下方法上报展示数据

```java
ZzAdSdk.getInstance().setAdShow(appid);
```

### 广告点击上报

* 为了便于统计广告效果，在广告的RecyclerView或者ListView中点击了该广告，需要调用以下方法上报点击数据

```java
ZzAdSdk.getInstance().setAdClick(appid);
```

