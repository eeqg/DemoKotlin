package com.douyao.demokotlin

/**
 * Created by wp on 2019/9/6.
 */
open class Person(name: String = "wxy") {
    var pName: String

    init {
        println("-----init block-----name: $name")
        pName = name;
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

fun main() {
    var person1 = Person()

    val person2 = Person("Tom", 23)

    var person3 = Person("Lily")

    person2.favorFruit("pear")

    XPerson("xMan").greeting()
}
