//
// Let's put together a squad to save the galaxy…
//

sealed class SquadMember {
  abstract val name: String
  abstract val title: String
}

data class Commander(override val name: String, override val title: String) : SquadMember()
data class Vigilante(override val name: String, override val title: String) : SquadMember()
data class ArtificalLifeform(override val name: String, override val title: String) : SquadMember()
data class Krogan(override val name: String, override val title: String) : SquadMember()
data class Engineer(override val name: String, override val title: String) : SquadMember()
data class ScientistSalarian(override val name: String, override val title: String) : SquadMember()

class Squad(
    private val max: Int = 3,
    members: List<SquadMember> = emptyList()) {
  private var members: MutableList<SquadMember> = members.take(3).toMutableList()

  operator fun plusAssign(member: SquadMember) {
    if (members.size < max) {
      members.add(member)
    }
  }

  operator fun minusAssign(member: SquadMember) {
    members.remove(member)
  }

  operator fun contains(obj: Any): Boolean = when(obj) {
    is SquadMember -> members.contains(obj)
    is String -> members.any { it.name == obj }
    else -> false
  }

  operator fun component1(): SquadMember = members[0]
  operator fun component2(): SquadMember = members[1]
}