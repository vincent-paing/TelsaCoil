package com.aungkyawpaing.coil

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Vincent on 2019-08-21
 */
object RandomGenerator {

  fun randomUuid(): String {
    return java.util.UUID.randomUUID().toString()
  }

  fun randomInt(): Int {
    return ThreadLocalRandom.current().nextInt(0, Int.MAX_VALUE)
  }

  fun randomLong(): Long {
    return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE)
  }

  fun randomBoolean(): Boolean {
    return Math.random() < 0.5
  }

  fun makeStringList(count: Int): List<String> {
    val items: MutableList<String> = mutableListOf()
    repeat(count) {
      items.add(randomUuid())
    }
    return items
  }

}