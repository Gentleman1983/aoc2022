package de.havox_design.aoc2022.day10

enum class Order(var orderName: String, var duration: Int) {
    NOOP("noop", 1),
    ADDX("addx", 2),
    UNKNOWN("UNKNOWN", 0);

    companion object {
        fun findByOrderName(order: String): Order {
            for(element in values()) {
                if(element.orderName == order.lowercase()) {
                    return element
                }
            }

            return UNKNOWN
        }
    }
}
