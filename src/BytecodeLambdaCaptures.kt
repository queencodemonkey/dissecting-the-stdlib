
//
//      ___  _                                                 _
//     / __|| | ___  ___ _  _  _ _  ___  ___    __ _  _ _   __| |
//    | (__ | |/ _ \(_-<| || || '_|/ -_)(_-<   / _` || ' \ / _` |
//     \___||_|\___//__/ \_,_||_|  \___|/__/   \__,_||_||_|\__,_|
//      ___              _
//     / __| __ _  _ __ | |_  _  _  _ _  ___  ___
//    | (__ / _` || '_ \|  _|| || || '_|/ -_)(_-<
//     \___|\__,_|| .__/ \__| \_,_||_|  \___|/__/
//                |_|
//

//
//        "A closure is the combination of a function and the lexical environment
//         within which that function was declared."
//
//        Environment → local variables in scope to function when it was defined
//        Closure → function "closes over" those scoped values
//        Scoped values that can be accessed by that function (even much later) are "captured"
//
//        Think of anonymous classes in Java that can only access `final` local values
//        in outer scope.
//










// region // Pretend we are on a spaceship on a voyage of discovery… when SUDDENLY…
@file:Suppress("unused")

val captain: ImpetuousCaptain = ImpetuousCaptain("Jean-Luc Picard", 1, false)
val headEngineer: StalwartEngineer = StalwartEngineer("Geordie LaForge", 3, false)
val doctor: OrneryDoctor = OrneryDoctor("Beverly Crusher", 3, false)

val shipComputer = ShipComputer(100)
// endregion


fun initSelfDestruct(secondsTill: Long) {
  shipComputer.runCommands {
    if (provideCrewAuthorization(listOf(captain, headEngineer, doctor))) {
      selfDestruct()
    }
    evacuate()
  }
}










//     ___ __  __ ______ ____ _____ ____   ___   ____
//    / _ )\ \/ //_  __// __// ___// __ \ / _ \ / __/
//   / _  | \  /  / /  / _/ / /__ / /_/ // // // _/
//  /____/  /_/  /_/  /___/ \___/ \____//____//___/
//     ___   ___   ____ ___    __ __ __
//    / _ ) / _ \ / __// _ |  / //_// /
//   / _  |/ , _// _/ / __ | / ,<  /_/
//  /____//_/|_|/___//_/ |_|/_/|_|(_)
//










// region // Bytecode when runCommands has no capture.
//
//        public static final void initSelfDestruct(long secondsTill) {
//           shipComputer.runCommands((Function1)null.INSTANCE);
//        }
//
// endregion










// region // Bytecode when lambda has capture, `secondsTill`.
//
//        public static final void initSelfDestruct(final long secondsTill) {
//          shipComputer.runCommands((Function1)(new Function1() {
//            // $FF: synthetic method
//            // $FF: bridge method
//            public Object invoke(Object var1) {
//              this.invoke((ShipComputer)var1);
//              return Unit.INSTANCE;
//            }
//
//            public final void invoke(@NotNull ShipComputer $receiver) {
//            Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
//            if ($receiver.provideCrewAuthorization(CollectionsKt.listOf(new CrewMember[]{(CrewMember)BytecodeLambdaCapturesKt.getCaptain(), (CrewMember)BytecodeLambdaCapturesKt.getHeadEngineer(), (CrewMember)BytecodeLambdaCapturesKt.getDoctor()}))) {
//            $receiver.selfDestruct(secondsTill);
//          }
//
//            $receiver.evacuate();
//          }
//          }));
//        }
//
// endregion
