package de.havox_design.aoc2022.day13

import de.havox_design.aoc2022.day13.Packet.PacketLiteral
import de.havox_design.aoc2022.day13.Packet.PacketList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day13Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestPacketCreation")
    fun testPacketCreation(packetCode: String, expectedPacket: Packet) =
        Packet.parsePacket(packetCode).shouldBe(expectedPacket)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        DistressSignal(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        DistressSignal(filename).processPart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestPacketCreation(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "[1,1,3,1,1]",
                    PacketList(
                        listOf(
                            PacketLiteral(1),
                            PacketLiteral(1),
                            PacketLiteral(3),
                            PacketLiteral(1),
                            PacketLiteral(1)
                        )
                    )
                ),
                Arguments.of(
                    "[1,1,5,1,1]",
                    PacketList(
                        listOf(
                            PacketLiteral(1),
                            PacketLiteral(1),
                            PacketLiteral(5),
                            PacketLiteral(1),
                            PacketLiteral(1)
                        )
                    )
                ),
                Arguments.of(
                    "[[1],[2,3,4]]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketLiteral(1)
                                )
                            ),
                            PacketList(
                                listOf(
                                    PacketLiteral(2),
                                    PacketLiteral(3),
                                    PacketLiteral(4)
                                )
                            )
                        )
                    )
                ),
                Arguments.of(
                    "[[1],4]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketLiteral(1)
                                )
                            ),
                            PacketLiteral(4)
                        )
                    )
                ),
                Arguments.of(
                    "[9]",
                    PacketList(
                        listOf(
                            PacketLiteral(9)
                        )
                    )
                ),
                Arguments.of(
                    "[[8,7,6]]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketLiteral(8),
                                    PacketLiteral(7),
                                    PacketLiteral(6)
                                )
                            )
                        )
                    )
                ),
                Arguments.of(
                    "[[4,4],4,4]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketLiteral(4),
                                    PacketLiteral(4)
                                )
                            ),
                            PacketLiteral(4),
                            PacketLiteral(4)
                        )
                    )
                ),
                Arguments.of(
                    "[[4,4],4,4,4]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketLiteral(4),
                                    PacketLiteral(4)
                                )
                            ),
                            PacketLiteral(4),
                            PacketLiteral(4),
                            PacketLiteral(4)
                        )
                    )
                ),
                Arguments.of(
                    "[7,7,7,7]",
                    PacketList(
                        listOf(
                            PacketLiteral(7),
                            PacketLiteral(7),
                            PacketLiteral(7),
                            PacketLiteral(7)
                        )
                    )
                ),
                Arguments.of(
                    "[7,7,7]",
                    PacketList(
                        listOf(
                            PacketLiteral(7),
                            PacketLiteral(7),
                            PacketLiteral(7)
                        )
                    )
                ),
                Arguments.of(
                    "[]",
                    PacketList(
                        listOf()
                    )
                ),
                Arguments.of(
                    "[3]",
                    PacketList(
                        listOf(
                            PacketLiteral(3)
                        )
                    )
                ),
                Arguments.of(
                    "[[[]]]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf(
                                    PacketList(
                                        listOf()
                                    )
                                )
                            )
                        )
                    )
                ),
                Arguments.of(
                    "[[]]",
                    PacketList(
                        listOf(
                            PacketList(
                                listOf()
                            )
                        )
                    )
                ),
                Arguments.of(
                    "[1,[2,[3,[4,[5,6,7]]]],8,9]",
                    PacketList(
                        listOf(
                            PacketLiteral(1),
                            PacketList(
                                listOf(
                                    PacketLiteral(2),
                                    PacketList(
                                        listOf(
                                            PacketLiteral(3),
                                            PacketList(
                                                listOf(
                                                    PacketLiteral(4),
                                                    PacketList(
                                                        listOf(
                                                            PacketLiteral(5),
                                                            PacketLiteral(6),
                                                            PacketLiteral(7)
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            ),
                            PacketLiteral(8),
                            PacketLiteral(9)
                        )
                    )
                ),
                Arguments.of(
                    "[1,[2,[3,[4,[5,6,0]]]],8,9]",
                    PacketList(
                        listOf(
                            PacketLiteral(1),
                            PacketList(
                                listOf(
                                    PacketLiteral(2),
                                    PacketList(
                                        listOf(
                                            PacketLiteral(3),
                                            PacketList(
                                                listOf(
                                                    PacketLiteral(4),
                                                    PacketList(
                                                        listOf(
                                                            PacketLiteral(5),
                                                            PacketLiteral(6),
                                                            PacketLiteral(0)
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            ),
                            PacketLiteral(8),
                            PacketLiteral(9)
                        )
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 13)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 140)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Packet.shouldBe(expectation: Packet) = Assertions.assertEquals(expectation, this)
