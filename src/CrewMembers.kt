sealed class CrewMember {
  abstract val name: String
  abstract val rank: Int

  infix fun assignedTo(ship: Ship) = Unit
}

data class ImpetuousCaptain(override val name: String, override val rank: Int,
                            val overacts: Boolean) : CrewMember()

data class EnigmaticScienceOfficer(override val name: String, override val rank: Int,
                                   val specialty: String) : CrewMember()

data class StalwartEngineer(override val name: String, override val rank: Int,
                            val doingRepairs: Boolean) : CrewMember()

data class OrneryDoctor(override val name: String, override val rank: Int,
                        val isNotAMechanic: Boolean) : CrewMember()

data class RedShirt(override val name: String, override val rank: Int,
                    val isDead: Boolean = true) : CrewMember()

data class Ship(val name: String, val shipClass: String)

val essEnterprise = Ship("ESS Enterprise NX-01", "NX")
val ncc1701b = Ship("Enterprise NCC-1701B", "Excelsior")
val ncc1701c = Ship("Enterprise NCC-1701C", "Ambassador")
val voyager = Ship("USS Voyager NCC-74656", "Intrepid")

val kirk = ImpetuousCaptain("James Tiberius Kirk", 1, true)
val picard = ImpetuousCaptain("Jean-Luc Picard", 1, false)
val janeway = ImpetuousCaptain("Kathryn Janeway", 1, false)

class ShipComputer(val minDelay: Long) {
  fun runCommands(commands: ShipComputer.() -> Unit) = Unit
  fun provideCrewAuthorization(crewMembers: List<CrewMember>): Boolean = true
  fun selfDestruct(delay: Long = 10000) = Unit
  fun evacuate() = Unit
}

class CaptainsLog(starDate: String) {
  fun record(entry: String) = Unit
}