package com.douyao.demokotlin.kotlin

import android.os.Build
import kotlin.random.Random

/**
 * Created by wp on 2019/9/18.
 */
fun main() {
    val a = 5
    val b = 10
    println("---------------------->>> function default params.. ")
    println("$a x 2 = ${multiply(a)}")
    println("$a x $b = ${multiply(a, b)}")

    println("--------------------> lambda ..")
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    println("sum(1, 2) = ${sum(1, 2)}")

    testArray()

    println("--------------------> collections ..")
    val list = List(3) { it * 3 }
    println(list)
    testList()
//    val mapValue = mapOf("a" to 1, "b" to 2, "c" to 5, "a" to 65)
    val mapValue = mutableMapOf("a" to 1, "b" to 2, "c" to 5, "a" to 65)
        .apply {
            put("key", 234)
            this["aaa"] = 999
        }
    println(mapValue)

    testListCopy()
    testListOperate()

    testArrays()

    testSequences()

    testSet()

    testMap()

    testOther()
}

//params b : 默认参数
fun multiply(a: Int, b: Int = 2): Int {
    return a * b
}

fun testArray() {
    println("--------------------> arrays ..")
    val arrayOf = arrayListOf(1, 2, 3)
    println(arrayOf)
}

fun testList() {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    numbers.removeAt(1)
    numbers[0] = 0
    numbers.shuffle()
    println("list : $numbers")
}

fun testListCopy() {
    val emptyList = emptyList<String>()
//    println(emptyList.first())
    emptyList.toMutableList().apply {
        add("copy: empty -> writable list.")
        println(this)
    }

    val list1 = mutableListOf(1, 2, 3)
    val list2 = list1
    list2.add(4)
    list1.toMutableList().apply {
        add(5)
        print(this)
    }
    print(list1)
    print(list2)
    println()
}

fun testListOperate() {
    /**
     * 元素操作符
     * contains(元素) : 检查集合中是否包含指定的元素，若存在则返回true，反之返回false
    elementAt(index) : 获取对应下标的元素。若下标越界，会抛出IndexOutOfBoundsException（下标越界）异常，同get(index)一样
    elementAtOrElse(index,{...}) : 获取对应下标的元素。若下标越界，返回默认值，此默认值就是你传入的下标的运算值
    elementAtOrNull(index) : 获取对应下标的元素。若下标越界，返回null
    first() : 获取第一个元素，若集合为空集合，这会抛出NoSuchElementException异常
    first{} : 获取指定元素的第一个元素。若不满足条件，则抛出NoSuchElementException异常
    firstOrNull() : 获取第一个元素，若集合为空集合，返回null
    firstOrNull{} : 获取指定元素的第一个元素。若不满足条件，返回null
    getOrElse(index,{...}) : 同elementAtOrElse一样
    getOrNull(index) : 同elementAtOrNull一样
    last() : 同first()相反
    last{} : 同first{}相反
    lastOrNull{} : 同firstOrNull()相反
    lastOrNull() : 同firstOrNull{}相反
    indexOf(元素) : 返回指定元素的下标，若不存在，则返回-1
    indexOfFirst{...} : 返回第一个满足条件元素的下标，若不存在，则返回-1
    indexOfLast{...} : 返回最后一个满足条件元素的下标，若不存在，则返回-1
    single() : 若集合的长度等于0,则抛出NoSuchElementException异常，若等于1，则返回第一个元素。反之，则抛出IllegalArgumentException异常
    single{} : 找到集合中满足条件的元素，若元素满足条件，则返回该元素。否则会根据不同的条件，抛出异常。这个方法慎用
    singleOrNull() : 若集合的长度等于1,则返回第一个元素。否则，返回null
    singleOrNull{} : 找到集合中满足条件的元素，若元素满足条件，则返回该元素。否则返回null
    forEach{...} : 遍历元素。一般用作元素的打印
    forEachIndexed{index,value} : 遍历元素，可获得集合中元素的下标。一般用作元素以及下标的打印
    componentX() ： 这个函数在前面的章节中提过多次了。用于获取元素。其中的X只能代表1..5。详情可看下面的例子

     *顺序操作符
    reversed() : 反序。即和初始化的顺序反过来。
    sorted() : 自然升序。
    sortedBy{} : 根据条件升序，即把不满足条件的放在前面，满足条件的放在后面
    sortedDescending() : 自然降序。
    sortedByDescending{} : 根据条件降序。和sortedBy{}相反

     *映射操作符：
    map{...} : 把每个元素按照特定的方法进行转换，组成一个新的集合。
    mapNotNull{...} : 同map{}函数的作用相同，只是过滤掉转换之后为null的元素
    mapIndexed{index,result} : 把每个元素按照特定的方法进行转换，只是其可以操作元素的下标(index)，组成一个新的集合。
    mapIndexedNotNull{index,result} : 同mapIndexed{}函数的作用相同，只是过滤掉转换之后为null的元素
    flatMap{...} : 根据条件合并两个集合，组成一个新的集合。
    groupBy{...} : 分组。即根据条件把集合拆分为为一个Map<K,List<T>>类型的集合。具体看实例

     *过滤操作符
     * filter{...} : 把不满足条件的元素过滤掉
    filterIndexed{...} : 和filter{}函数作用类似，只是可以操作集合中元素的下标（index）
    filterNot{...} : 和filter{}函数的作用相反
    filterNotNull() : 过滤掉集合中为null的元素。
    take(num) : 返回集合中前num个元素组成的集合
    takeWhile{...} : 循环遍历集合，从第一个元素开始遍历集合，当第一个出现不满足条件元素的时候，退出遍历。然后把满足条件所有元素组成的集合返回。
    takeLast(num) : 返回集合中后num个元素组成的集合
    takeLastWhile{...} : 循环遍历集合，从最后一个元素开始遍历集合，当第一个出现不满足条件元素的时候，退出遍历。然后把满足条件所有元素组成的集合返回。
    drop(num) : 过滤集合中前num个元素
    dropWhile{...} : 相同条件下，和执行takeWhile{...}函数后得到的结果相反
    dropLast(num) : 过滤集合中后num个元素
    dropLastWhile{...} : 相同条件下，和执行takeLastWhile{...}函数后得到的结果相反
    distinct() : 去除重复元素
    distinctBy{...} : 根据操作元素后的结果去除重复元素
    slice : 过滤掉所有不满足执行下标的元素。

     * 生产操作符
     * plus() : 合并两个集合中的元素，组成一个新的集合。也可以使用符号+
    zip : 由两个集合按照相同的下标组成一个新集合。该新集合的类型是：List<Pair>
    unzip : 和zip的作用相反。把一个类型为List<Pair>的集合拆分为两个集合。看下面的例子
    partition : 判断元素是否满足条件把集合拆分为有两个Pair组成的新集合。

     * 聚合操作符
    any() : 判断是不是一个集合，若是，则在判断集合是否为空，若为空则返回false,反之返回true,若不是集合，则返回hasNext
    any{...} : 判断集合中是否存在满足条件的元素。若存在则返回true,反之返回false
    all{...} : 判断集合中的所有元素是否都满足条件。若是则返回true,反之则返回false
    none() : 和any()函数的作用相反
    none{...} : 和all{...}函数的作用相反
    max() : 获取集合中最大的元素，若为空元素集合，则返回null
    maxBy{...} : 获取方法处理后返回结果最大值对应那个元素的初始值，如果没有则返回null
    min() : 获取集合中最小的元素，若为空元素集合，则返回null
    minBy{...} : 获取方法处理后返回结果最小值对应那个元素的初始值，如果没有则返回null
    sum() : 计算出集合元素累加的结果。
    sumBy{...} : 根据元素运算操作后的结果，然后根据这个结果计算出累加的值。
    sumByDouble{...} : 和sumBy{}相似，不过sumBy{}是操作Int类型数据，而sumByDouble{}操作的是Double类型数据
    average() : 获取平均数
    reduce{...} : 从集合中的第一项到最后一项的累计操作。
    reduceIndexed{...} : 和reduce{}作用相同，只是其可以操作元素的下标(index)
    reduceRight{...} : 从集合中的最后一项到第一项的累计操作。
    reduceRightIndexed{...} : 和reduceRight{}作用相同，只是其可以操作元素的下标(index)
    fold{...} : 和reduce{}类似，但是fold{}有一个初始值
    foldIndexed{...} : 和reduceIndexed{}类似，但是foldIndexed{}有一个初始值
    foldRight{...} : 和reduceRight{}类似，但是foldRight{}有一个初始值
    foldRightIndexed{...} : 和reduceRightIndexed{}类似，但是foldRightIndexed{}有一个初始值
     */

    listOf("one", "two", "three", "four", "five")
        .apply {
            println("sorted: ${sorted()}")
            println("sortBy length: ${sortedBy { it.length }}")
            val mList = mutableListOf("one", "two", "three")
            println("sort: ${mList.sort()}")

            println("Sorted by length ascending: ${sortedWith(compareBy { it.length })}")

            println("shuffled: ${shuffled()}")//随机顺序

            forEach { print(it) }
            println()
            forEachIndexed { index, s ->
                print("forEach: index: $index, value: $s ")
            }

            println()
            println("map: " + map { "$it-afterMap" })
            println("mapIndex: " + mapIndexed { index, s -> "$index is $s" })
            println("flatMap: " + flatMap { listOf(it, "oo-".plus(it)) })

            //groupBuy, return a Map
            println("groupBy: ".plus(groupBy {
                if (it.startsWith("t")) {
                    "T"
                } else {
                    "O"
                }
            }))

            println("slice: ${slice(IntRange(1, 3))}")//根据下标进行过滤
            println("slice: ${slice(listOf(1, 3))}")//根据下标进行过滤

            println("filter: ${filter { it.length > 3 }}") //过滤
            println("take: ${take(2)}")
            println("drop: ${drop(2)}")
            println("takeWhile: ${takeWhile { it.length > 3 }}")//从第一个元素开始遍历集合，当第一个出现不满足条件元素的时候，退出遍历。然后把满足条件所有元素组成的集合返回

            println("max: " + max())
            println("sum: " + listOf(1, 2, 3).sum())
            println("reduce: " + listOf(1, 2, 3).reduce { result, next -> result + next })
            println("sum: " + listOf(1, 2, 3).fold(10) { result, next -> result + next })

            println("+ : ${plus("six")}")
            println("- : ${minus("one")}")
            println("+ : ${this + "seven"}")
            println("- : ${this - listOf("one", "two")}")

        }

    println("-------binarySearch--------")
    // If such an element exists, the function returns its index; otherwise, it returns (-insertionPoint - 1) where insertionPoint is the index where this element should be inserted so that the list remains sorted.
    val numList = mutableListOf(3, 9, 5, 7, 2)
    println("origin: $numList")
    numList.sort()
    println("sort: $numList") // [2, 3, 5, 7, 9]
    println("binarySearch: ${numList.binarySearch(3)}")//return index -> 1
    println("binarySearch: ${numList.binarySearch(80)}")//return (-insertionPoint - 1) -> -6
    println("binarySearch: ${numList.binarySearch(6)}")//return (-insertionPoint - 1) -> -4

    println("-------zip 双路合并--------")
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)
    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))

    println("-------associateWith 关联--------")
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length })

    println("-------flatten 变平--------")
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())

    println("-------join --------")
    println("source list: $numbers")
    println("toString: " + numbers.joinToString())//toString
    val listString = StringBuffer("The list of numbers: ")
    println("joinTo: " + numbers.joinTo(listString))
    println(
        "separator: " +
                numbers.joinToString(
                    separator = " | ",
                    prefix = "start: ",
                    postfix = ": end"
                )
    )//separator
    val numbersBig = (1..100).toList()
    println("limit: " + numbersBig.joinToString(limit = 10, truncated = "<...>"))//limit
    println("transform: " + numbers.joinToString { "Element: ${it.toUpperCase()}" })//transform
}

/**
 * 数组
 */
fun testArrays() {
    println("-------array --------")
    // Array of int of size 5 with values [0, 0, 0, 0, 0]
    val arr1 = IntArray(5)

    // e.g. initialise the values in the array with a constant
    // Array of int of size 5 with values [42, 42, 42, 42, 42]
    val arr2 = IntArray(5) { 42 }

    // e.g. initialise the values in the array using a lambda
    // Array of int of size 5 with values [0, 1, 2, 3, 4] (values initialised to their index value)
    var arr3 = IntArray(5, { it * 1 })
    println(arr3.asList())
}

/**
 * 列表和序列
 */
fun testSequences() {

    val words = "The quick brown fox jumps over the lazy dog".split(" ")

    println("--------------------list-------------------------")

    val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)

    println("--------------------sequence-------------------------")

    val wordsSequence = words.asSequence()
    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // terminal operation: obtaining the result as a List
    println(lengthsSequence.toList())
}

fun testSet() {
    println("--------------------set-------------------------")
    val numbers = setOf("one", "two", "three")

    println(numbers union setOf("four", "five"))
    println(setOf("four", "five") union numbers)

    println(numbers intersect setOf("two", "one"))
    println(numbers subtract setOf("three", "four"))
    println(numbers subtract setOf("four", "three")) // same output
}

fun testMap() {
    println("--------------------map-------------------------")
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap.get("one"))
    println(numbersMap["one"])
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        println(numbersMap.getOrDefault("four", 10))
    }
    println(numbersMap["five"])               // null
    //numbersMap.getValue("six")      // exception!

    println(numbersMap.keys)
    println(numbersMap.values)

    val filteredMap = numbersMap.filter { (key, value) -> value > 2 }
    println(filteredMap)

    //plus 与 minus 操作
    println(numbersMap + Pair("four", 4))
    println(numbersMap + Pair("one", 10))
    println(numbersMap + mapOf("five" to 5, "one" to 11))
    println(numbersMap - "one")
    println(numbersMap - listOf("two", "four"))

    //Map 写操作
    val mutableMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    mutableMap.putAll(setOf("four" to 4, "five" to 5))
    mutableMap["six"] = 6
    println(mutableMap)

}

fun testOther() {
    val number = Random.nextInt(100)
    println("${number.takeIf { it % 2 == 0 }}")
}