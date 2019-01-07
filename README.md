# Groovy方法扩展与脚本调用

### 需求：给File的实例(或类)扩展start方法

### 第一步：编写FileExtension文件(命名可随意)

```
class FileExtension {
    //org.codehaus.groovy.runtime.ExtensionModule
    static File start(File self, Closure closure) {
        closure()
        return self
    }
}
```
### 第二步：编写manifest文件
1. 包名必须为 manifest.META-INF.services
2. 文件名必须是 org.codehaus.groovy.runtime.ExtensionModule
3. 内容如下，extensionClasses表示扩展给File的实例，staticExtensionClasses表示扩展给File类。
   + moduleName:myFile
   + moduleVersion:1.0
   + extensionClasses:FileExtension
   + //staticExtensionClasses:FileExtension

### 第三步：编译、打包
groovyc -d classes FileExtension.groovy

jar -cvf file.jar -C classes . -C manifest .

### 第四步：编写测试文件(Test.groovy)、使用
//创建file实例

```
File file = new File('/Users/yuanchao/Workspaces/IdeaProjects/groovydemo/src/main/groovy/readme.txt')
file.start{
    println "file start!"
}
```
运行：groovy -classpath file.jar Test.groovy