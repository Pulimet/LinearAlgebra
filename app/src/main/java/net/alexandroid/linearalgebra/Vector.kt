@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package net.alexandroid.linearalgebra

import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Vector(private val coordinates: Array<Double>) {
    constructor(x: Double, y: Double) : this(arrayOf(x, y))
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(x: Double, y: Double, z: Double) : this(arrayOf(x, y, z))
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(x: Double, y: Double, z: Double, q: Double) : this(arrayOf(x, y, z, q))

    init {
        if (coordinates.size < 2) throw IllegalStateException("Vector should has at least two coordinates")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Vector) throw IllegalStateException("Vector can be compared only with vector")
        validateSameSize(other)
        return coordinates.contentEquals(other.coordinates)
    }

    override fun hashCode() = coordinates.contentHashCode()

    override fun toString(): String {
        return this.coordinates.asList().toString()
    }

    operator fun plus(vector: Vector): Vector {
        validateSameSize(vector)
        return Vector(this.coordinates.plus(vector.coordinates))
    }

    operator fun minus(vector: Vector): Vector {
        validateSameSize(vector)
        val newArray = Array(this.coordinates.size) { 0.0 }
        this.coordinates.forEachIndexed { index, coordinate ->
            newArray[index] = coordinate - vector.coordinates[index]
        }
        return Vector(newArray)
    }

    operator fun times(vector: Vector): Vector {
        validateSameSize(vector)
        val newArray = Array(this.coordinates.size) { 0.0 }
        this.coordinates.forEachIndexed { index, coordinate ->
            newArray[index] = coordinate * vector.coordinates[index]
        }
        return Vector(newArray)
    }

    operator fun times(d: Double): Vector {
        val newArray = Array(this.coordinates.size) { 0.0 }
        this.coordinates.forEachIndexed { index, coordinate ->
            newArray[index] = coordinate * d
        }
        return Vector(newArray)
    }

    fun magnitude(round: Int? = null): Double {
        var sum = 0.0
        this.coordinates.forEach { sum += it.pow(2) }
        return roundDouble(round, sqrt(sum))
    }

    fun normalization(round: Int? = null): Vector {
        val oneDivMagnitude = 1.0 / magnitude(3)
        val normalized = this * oneDivMagnitude
        return roundVector(round, normalized)
    }

    fun round(round: Int): Vector {
        val newArray = Array(this.coordinates.size) { 0.0 }
        this.coordinates.forEachIndexed { index, coordinate ->
            newArray[index] = coordinate.round(round)
        }
        return Vector(newArray)
    }

    fun dotProduct(vector: Vector): Double {
        validateSameSize(vector)
        var result = 0.0
        this.coordinates.forEachIndexed { index, coordinate ->
            result += coordinate * vector.coordinates[index]
        }
        return result
    }

    fun angle(vector: Vector): Double {
        validateSameSize(vector)
        val dotProduct = this.dotProduct(vector)
        val magnitudes = this.magnitude() * vector.magnitude()
        return acos(dotProduct / magnitudes)
    }

    fun isParallelTo(vector: Vector): Boolean {
        val dotProduct = this.dotProduct(vector)
        val magnitudes = this.magnitude() * vector.magnitude()
        //Log.d("AlgebraResult", "dotProduct: $dotProduct")
        //Log.d("AlgebraResult", "magnitudes: $magnitudes")
        return abs(dotProduct) == magnitudes
    }

    fun isOppositeDirectionTo(vector: Vector) = this.dotProduct(vector) == this.magnitude() * vector.magnitude() * -1

    // is90degreesTo
    fun isOrthogonalTo(vector: Vector): Boolean {
        val dotProduct = this.dotProduct(vector).round(3)
        //Log.d("AlgebraResult", "dotProduct: $dotProduct")
        return dotProduct == 0.0
    }

    fun componentParallelTo(baseVector: Vector): Vector {
        val basisNormalized = baseVector.normalization()
        val weight = this.dotProduct(basisNormalized)
        return basisNormalized * weight
    }

    fun componentOrthogonalTo(baseVector: Vector): Vector {
        val projection = this.componentParallelTo(baseVector)
        return this - projection
    }

    fun crossProduct(vector: Vector): Vector {
        validateSameSize(vector)
        validateSizeOfThree()
        val (x1, y1, z1) = this.coordinates
        val (x2, y2, z2) = vector.coordinates
        return Vector(
            y1 * z2 - y2 * z1,
            -1 * (x1 * z2 - x2 * z1),
            x1 * y2 - x2 * y1
        )
    }

    fun areaParallelogram(vector: Vector): Double {
        val crossProduct = this.crossProduct(vector)
        return crossProduct.magnitude()
    }

    fun areaOfTriangle(vector: Vector): Double {
        return this.areaParallelogram(vector) / 2.0
    }


    // Private
    private fun validateSameSize(vector: Vector) {
        if (this.coordinates.size != vector.coordinates.size) throw IllegalStateException("Failed due to unequal amount of coordinates")
    }

    private fun validateSizeOfThree() {
        if (this.coordinates.size != 3) throw IllegalStateException("Only for 3 dimension supported")
    }

    private fun roundDouble(r: Int?, d: Double) = if (r != null) {
        d.round(r)
    } else {
        d
    }

    private fun roundVector(r: Int?, v: Vector) = if (r != null) {
        v.round(r)
    } else {
        v
    }
}

fun Double.round(i: Int): Double {
    if (i < 0) throw IllegalStateException("Must be 0 or positive")
    val multi = 10.0.pow(i)
    return (this * multi).roundToInt() / multi
}

fun Double.toDegrees() = Math.toDegrees(this)

