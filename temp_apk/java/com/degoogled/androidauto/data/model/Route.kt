package com.degoogled.androidauto.data.model

/**
 * Data class representing a navigation route.
 */
data class Route(
    val start: Location,
    val destination: Location,
    val waypoints: List<Location> = emptyList(),
    val distance: Double, // in meters
    val duration: Int, // in seconds
    val polyline: List<Location>, // route geometry
    val instructions: List<Instruction>
)

/**
 * Data class representing a navigation instruction.
 */
data class Instruction(
    val text: String,
    val distance: Double, // in meters
    val time: Int, // in seconds
    val type: InstructionType,
    val location: Location
)

/**
 * Enum representing the type of navigation instruction.
 */
enum class InstructionType {
    STRAIGHT,
    TURN_LEFT,
    TURN_RIGHT,
    TURN_SLIGHT_LEFT,
    TURN_SLIGHT_RIGHT,
    TURN_SHARP_LEFT,
    TURN_SHARP_RIGHT,
    U_TURN,
    ROUNDABOUT,
    MERGE,
    EXIT,
    ARRIVE
}