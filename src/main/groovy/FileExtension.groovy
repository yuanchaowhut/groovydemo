/**
 * Created by yuanchao on 2018/12/29.
 */
class FileExtension {
    //org.codehaus.groovy.runtime.ExtensionModule
    static File start(File self, Closure closure) {
        closure()
        return self
    }
}
