package com.douyao.demokotlin

/**
 * Created by wp on 2019/9/9.
 */
class SignalTonDemo private constructor() {
    companion object {
        //懒汉模式
        private var instance: SignalTonDemo? = null
            get() {
                if (field == null) {
                    field = SignalTonDemo()
                }
                return field
            }

//        //双重校验模式
//        val instance: SignalTonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            SignalTonDemo()
//        }

        @Synchronized //加锁
        fun get(): SignalTonDemo {
            return instance!!
        }
    }

    //field 关键词只能用于属性的访问器(getters & setters)
    var testField: String? = null
        get() {
            if (field == null) {
                print("---------> get value : ")
                field = "10000000"
            }
            return field
        }
        set(value) {
            print("---------> set value : ")
            field = value
        }

    fun foo() : String {
        println("---------> form signalTon.")
        return "111222333"
    }
}