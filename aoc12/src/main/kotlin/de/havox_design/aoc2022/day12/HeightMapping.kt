package de.havox_design.aoc2022.day12

enum class HeightMapping(private var symbol: String, private var elevation: Int) {
    START("S", 0),
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7),
    I("i", 8),
    J("j", 9),
    K("k", 10),
    L("l", 11),
    M("m", 12),
    N("n", 13),
    O("o", 14),
    P("p", 15),
    Q("q", 16),
    R("r", 17),
    S("s", 18),
    T("t", 19),
    U("u", 20),
    V("v", 21),
    W("w", 22),
    X("x", 23),
    Y("y", 24),
    Z("z", 25),
    END("E", 25),
    UNKNOWN("UNKNOWN", -666);

    companion object {
        fun getElevationBySymbol(symbol: String): Int {
            for (mapping in values()) {
                if (mapping.symbol == symbol) {
                    return mapping.elevation
                }
            }
            return UNKNOWN.elevation
        }
    }
}
