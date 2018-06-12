####android FrameSequenceDrawable 编译 (支持webp和gif动画，可控制)

webp在android 4.0开始支持，但是不支持动态的webp动画.  

android的使用 FrameSequenceDrawable播放webp动画，但是这个类没有在android sdk中集成。   
只在系统源码中有  
地址 https://android.googlesource.com/platform/external/webp   


项目需要依赖 gif库和webp解码库，这两个库可以在源码中找到依赖.  

由于源码现在多使用android.bp编译，这里改成常用的Android.mk编译. 


