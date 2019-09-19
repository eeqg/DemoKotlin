package com.douyao.demokotlin.kotlin

/**
 * Created by wp on 2019/9/6.
 */
open class Person(name: String = "wxy") {
    var pName: String

    init {
        println("-----init block-----name: $name")
        pName = name;
    }

    companion object {
        var a = 10
        fun cc() {
            println("-----companion object-----")
        }
    }

    constructor(name: String, age: Int) : this(name) {
        println("-----secondary constructor-----name:$name, age:$age")
    }

    constructor(name: String, age: Int, sex: String) : this(name, age) {
        println("-----secondary constructor-----name:$name, age:$age, sex: $sex")
    }

    open fun greeting() {
        println("hello")
    }

    fun favorFruit(fruit: String) {

        println("---> $pName like $fruit very much!")
    }
}

class XPerson(name: String) : Person(name) {
    override fun greeting() {
        println("${pName} say hi !!")
    }
}

class PerPerson {
    fun XPerson.extendMethod() {
        println("= = = = = = = = = = = = = = = =")
        greeting()
        println("extend method.....")
        println("= = = = = = = = = = =")
    }

    fun pp() {
        XPerson("extend, Person").extendMethod()
    }
}

fun main() {
    var person1 = Person()

    val person2 = Person("Tom", 23)

    var person3 = Person("Lily")

    person2.favorFruit("pear")

    //重新
    XPerson("xMan").greeting()

    //伴生对象
    println(Person.a)
    Person.cc()

    //扩展
    val ex = PerPerson()
    ex.pp()

    //signalTon.
    SignalTonDemo.get().foo()
    //幕后字段
    println(SignalTonDemo.get().testField)
    SignalTonDemo.get().testField = "0"
    println(SignalTonDemo.get().testField)
}
