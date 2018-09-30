//region <Insert some member enums, a member service, and a few other helper classes here>

class MemberService {
  fun getUserConfig(id: String): MemberConfig? = MemberConfig(id)
}

class MemberSession {
  fun load(config: MemberConfig?) = Unit
  fun start() = Unit
}

val memberService = MemberService()

//endregion