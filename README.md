# myhelptools
飞哥哥的日常帮助库
## 引用方法
1. Gradle引用   
   1. Add the JitPack repository to your build file
    ```
        allprojects {
	        repositories {
	    	    ...
	    	    maven { url 'https://jitpack.io' }
	        }
        }
    ```
   2. Add the dependency 
    ```
        dependencies {
	        implementation 'com.github.495262223:myhelptools:Tag'
	    }
	```
2. Maven引用
   1. Add the JitPack repository to your build file
   ```
	    <repositories>
		 <repository>
		     <id>jitpack.io</id>
		     <url>https://jitpack.io</url>
		 </repository>
	    </repositories>
   ```
   2. Add the dependency
   ```
	    <dependency>
	         <groupId>com.github.495262223</groupId>
	         <artifactId>myhelptools</artifactId>
	         <version>Tag</version>
	    </dependency>
   ```

## 库结构
>**myhelptools**
>>**utils** 
>>>**image**
>>>>**ImageLoaderUtils**
>>>>>`loadSDKImage(Context context, File file, ImageView imageView)` **加载本地图片**<br>
>>>>>`loadImage(Context context, String imageUri, ImageView imageView)` **加载网络图片**<br>
>>>>>`loadRound(Context context, String imageUri, ImageView imageView)` **加载圆形图片**<br>
>>>>>`saveViewToSystem(Context context, View view)` **将View保存到本地**<br>
>>>>>`saveBitmapToSystem(Context context, Bitmap bitmap)` **保存图片到相册**<br>
