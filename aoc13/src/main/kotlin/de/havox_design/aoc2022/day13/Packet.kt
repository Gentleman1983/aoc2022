package de.havox_design.aoc2022.day13

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

sealed class Packet : Comparable<Packet> {
    data class PacketLiteral(val value: Int) : Packet() {
        override fun compareTo(other: Packet): Int = when (other) {
            is PacketLiteral -> this.value compareTo other.value
            is PacketList -> PacketList(listOf(this)) compareTo other
        }

        override fun hashCode(): Int =
            HashCodeBuilder()
                .append(value)
                .toHashCode()

        override fun equals(other: Any?): Boolean {
            if (other is PacketLiteral) {
                return EqualsBuilder()
                    .append(value, other.value)
                    .build()
            }

            return super.equals(other)
        }
    }

    data class PacketList(val valueList: List<Packet>) : Packet() {
        override fun compareTo(other: Packet): Int {
            return when (other) {
                is PacketLiteral -> this compareTo PacketList(listOf(other))
                is PacketList -> {
                    val thisListIndex = this.valueList.iterator()
                    val otherListIndex = other.valueList.iterator()
                    while (thisListIndex.hasNext() && otherListIndex.hasNext()) {
                        val comparison = thisListIndex.next() compareTo otherListIndex.next()
                        if (comparison != 0) return comparison
                    }
                    thisListIndex.hasNext() compareTo otherListIndex.hasNext()
                }
            }
        }

        override fun hashCode(): Int =
            HashCodeBuilder()
                .append(valueList)
                .toHashCode()

        override fun equals(other: Any?): Boolean {
            if (other is PacketList) {
                return EqualsBuilder()
                    .append(valueList, other.valueList)
                    .build()
            }

            return super.equals(other)
        }
    }

    override fun equals(other: Any?): Boolean =
        (other is Packet) && (compareTo(other) == 0)

    abstract override fun hashCode(): Int

    companion object {
        val decoderPackets = arrayOf(
            PacketList(listOf(PacketList(listOf(PacketLiteral(2))))),
            PacketList(listOf(PacketList(listOf(PacketLiteral(6)))))
        )

        private val parser = DeepRecursiveFunction<IndexedValue<String>, IndexedValue<Packet>> { (startIndex, string) ->
            if (string[startIndex] == '[') {
                var index = startIndex + 1
                val list = buildList {
                    while (string[index] != ']') {
                        val (endIndex, value) = callRecursive(IndexedValue(index, string))
                        add(value)
                        index = if (string[endIndex] == ',') endIndex + 1 else endIndex
                    }
                }
                IndexedValue(index + 1, PacketList(list))
            } else {
                var index = startIndex + 1
                while (index < string.length && string[index] in '0'..'9') index++
                IndexedValue(index, PacketLiteral(string.substring(startIndex, index).toInt()))
            }
        }

        fun parsePacket(code: String): Packet {
            val (index, packet) = parser.invoke(IndexedValue(0, code))
            require(index == code.length)
            return packet
        }
    }
}
