package org.misumirize.hackernews.app

import android.os.IBinder
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.reflect.Method
import java.util.*

class DisableAnimationsRule : TestRule {

    val setAnimationScalesMethod: Method;
    val getAnimationScalesMethod: Method;
    val windowManagerObject: Any;

    init {
        val windowManagerStubClazz = Class.forName("android.view.IWindowManager\$Stub");
        val asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder::class.java);

        val serviceManagerClazz = Class.forName("android.os.ServiceManager");
        val getService = serviceManagerClazz.getDeclaredMethod("getService", String::class.java);

        val windowManagerClazz = Class.forName("android.view.IWindowManager");

        setAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("setAnimationScales", FloatArray::class.java);
        getAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("getAnimationScales");

        val windowManagerBinder = getService.invoke(null, "window") as IBinder;
        windowManagerObject = asInterface.invoke(null, windowManagerBinder);
    }

    override fun apply(base: Statement?, description: Description?): Statement? {
        return object: Statement() {
            override fun evaluate() {
                setAnimationScaleFactors(0.0f)
                try {
                    base?.evaluate()
                } finally {
                    setAnimationScaleFactors(1.0f)
                }
            }
        }
    }

    private fun setAnimationScaleFactors(scaleFactor: Float) {
        val scaleFactors = getAnimationScalesMethod.invoke(windowManagerObject) as FloatArray;
        Arrays.fill(scaleFactors, scaleFactor);
        setAnimationScalesMethod.invoke(windowManagerObject, scaleFactors);
    }
}
