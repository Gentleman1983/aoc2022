package de.havox_design.aoc2022.meili

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.lang.IllegalArgumentException

class Node(
    var key: String,
    var kids: Set<String> = emptySet(),
    var left: Node? = null,
    var right: Node? = null,
    var parent: Node? = null
) {

    fun insert(path: String, kid: String) {
        var newPath = path
        if (key != "root") {
            newPath = path.substring(1, path.length)
        }

        if (newPath.isEmpty()) {
            kids += kid
            return
        }

        when (val nextDirection = newPath.substring(0, 1)) {
            "L" -> {
                if (left == null) {
                    left = Node(key = nextDirection, parent = this)
                }

                return left!!.insert(newPath, kid)
            }

            "R" -> {
                if (right == null) {
                    right = Node(key = nextDirection, parent = this)
                }

                return right!!.insert(newPath, kid)
            }

            else -> throw IllegalArgumentException("Unknown direction '$nextDirection'.")
        }

    }

    fun find(vararg values: String): Set<Node> {
        val result = emptySet<Node>().toMutableSet()

        for (value in values) {
            if (kids.contains(value)) {
                result += this
            }
        }

        if (left != null) {
            result += left!!.find(*values)
        }
        if (right != null) {
            result += right!!.find(*values)
        }

        return result
    }

    fun detectPath(): String {
        var path = key

        if (parent != null) {
            path = "${parent?.detectPath()} - $path"
        }

        return path
    }

    fun detectHops(): Int {
        var hops = 0

        if (parent != null) {
            hops += 1 + parent!!.detectHops()
        }

        return hops
    }

    fun optimize() {
        if (parent != null && parent?.key != "root"
            && parent!!.kids.isEmpty()
            && (parent!!.left == null && parent!!.right != null
                    || parent!!.left != null && parent!!.right == null)
        ) {
            key = "${parent!!.key}$key"

            when {
                parent!!.parent!!.left == parent -> parent!!.parent!!.left = this
                parent!!.parent!!.right == parent -> parent!!.parent!!.right = this
            }

            parent = parent!!.parent
        }

        left?.optimize()
        right?.optimize()
    }

    override fun hashCode(): Int =
        HashCodeBuilder()
            .append(key)
            .append(kids)
            .append(left)
            .append(right)
            .append(parent?.key)
            .toHashCode()

    override fun equals(other: Any?): Boolean =
        if (other is Node) {
            EqualsBuilder()
                .append(this.key, other.key)
                .append(this.kids, other.kids)
                .append(this.left, other.left)
                .append(this.right, other.right)
                .append(this.parent?.key, other.parent?.key)
                .isEquals
        } else {
            false
        }

    override fun toString(): String =
        ToStringBuilder(ToStringStyle.DEFAULT_STYLE)
            .append("key", key)
            .append("kids", kids)
            .append("left", left)
            .append("right", right)
            .append("parent", parent?.key)
            .build()


    companion object {
        fun treeOf(data: Map<String, String>, optimized: Boolean = true): Node {
            val root = Node("root")

            for (entry in data.entries) {
                root.insert(path = entry.value, kid = entry.key)
            }

            if (optimized) {
                root.optimize()
            }

            return root
        }
    }
}