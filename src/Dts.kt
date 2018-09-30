@file:Suppress("unused")

import java.io.File
import java.text.Normalizer
import java.time.Instant
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.math.roundToInt
import kotlin.reflect.KProperty

//
//
//  https://kotlinlang.org/api/latest/jvm/stdlib/index.html
//
//










//
//         _____   _                        _    _
//        |  __ \ (_)                      | |  (_)
//        | |  | | _  ___  ___   ___   ___ | |_  _  _ __    __ _
//        | |  | || |/ __|/ __| / _ \ / __|| __|| || '_ \  / _` |
//        | |__| || |\__ \\__ \|  __/| (__ | |_ | || | | || (_| |
//        |_____/ |_||___/|___/ \___| \___| \__||_||_| |_| \__, |
//         _    _                    _       _  _  _  _     __/ |
//        | |  | |                  | |     | || |(_)| |   |___/
//        | |_ | |__    ___     ___ | |_  __| || | _ | |__
//        | __|| '_ \  / _ \   / __|| __|/ _` || || || '_ \
//        | |_ | | | ||  __/   \__ \| |_| (_| || || || |_) |
//         \__||_| |_| \___|   |___/ \__|\__,_||_||_||_.__/
//
//








//
//         _       _         _
//        | |     (_)       (_)
//        | |      _ __   __ _  _ __    __ _
//        | |     | |\ \ / /| || '_ \  / _` |
//        | |____ | | \ V / | || | | || (_| |
//        |______||_|  \_/  |_||_| |_| \__, |
//                                      __/ |
//                                      |___/
//         ______                         _    _         _
//        |  ____|                       | |  (_)       | |
//        | |__    ___  ___   ___  _ __  | |_  _   __ _ | | ___
//        |  __|  / __|/ __| / _ \| '_ \ | __|| | / _` || |/ __|
//        | |____ \__ \\__ \|  __/| | | || |_ | || (_| || |\__ \
//        |______||___/|___/ \___||_| |_| \__||_| \__,_||_||___/
//
//


//
// "The Kotlin Standard Library provides living essentials for everyday work with Kotlin."
//










//
//
//          _____        _  _              _    _
//         / ____|      | || |            | |  (_)
//        | |      ___  | || |  ___   ___ | |_  _   ___   _ __   ___
//        | |     / _ \ | || | / _ \ / __|| __|| | / _ \ | '_ \ / __|
//        | |____| (_) || || ||  __/| (__ | |_ | || (_) || | | |\__ \
//         \_____|\___/ |_||_| \___| \___| \__||_| \___/ |_| |_||___/
//
//









//
// Tons of out-of-the-box collection utilities and operators
//

//
// Fluent ways of performing operations on collections.
// Familiar to operators on RxJava or Java 8 streams.
//

//
// Functional API → evaluate expressions > executing statements,
//                  focus on what not how
//

//
// "Functional Programming in Java" by Venkat Subramaniam
// https://pragprog.com/book/vsjava8/functional-programming-in-java
//










fun getCrewForAwayMission(minRank: Int,
                          commandDeck: List<CrewMember>, engineering: List<StalwartEngineer>,
                          medBay: List<CrewMember>, other: List<RedShirt>) =
    commandDeck.filter { it is ImpetuousCaptain || it is EnigmaticScienceOfficer }
        .plus(engineering.first(StalwartEngineer::doingRepairs))
        .plus(medBay.first { it is OrneryDoctor && it.isNotAMechanic })
        .plus(other.filterNot { it.isDead }.takeLast(3))
        .filter { it.rank > minRank }
        .sortedBy(CrewMember::rank)










//
//     _    _  _         _                    ____            _
//    | |  | |(_)       | |                  / __ \          | |
//    | |__| | _   __ _ | |__    ___  _ __  | |  | | _ __  __| |  ___  _ __
//    |  __  || | / _` || '_ \  / _ \| '__| | |  | || '__|/ _` | / _ \| '__|
//    | |  | || || (_| || | | ||  __/| |    | |__| || |  | (_| ||  __/| |
//    |_|  |_||_| \__, ||_| |_| \___||_|     \____/ |_|   \__,_| \___||_|
//     ______      __/ |         _    _
//    |  ____|    |___/         | |  (_)
//    | |__  _   _  _ __    ___ | |_  _   ___   _ __   ___
//    |  __|| | | || '_ \  / __|| __|| | / _ \ | '_ \ / __|
//    | |   | |_| || | | || (__ | |_ | || (_) || | | |\__ \
//    |_|    \__,_||_| |_| \___| \__||_| \___/ |_| |_||___/
//
//

//
// First class functions; functions as first-class citizens
//
// 1. Store functions in variables.
// 2. Pass functions as parameters ➡️️ other functions.
// 3. Return functions ⬅️ other functions.
//










//   ___                 _    _
//  | __|_  _  _ _   __ | |_ (_) ___  _ _
//  | _|| || || ' \ / _||  _|| |/ _ \| ' \
//  |_|  \_,_||_||_|\__| \__||_|\___/|_||_|
//   _     _  _                    _
//  | |   (_)| |_  ___  _ _  __ _ | | ___
//  | |__ | ||  _|/ -_)| '_|/ _` || |(_-<
//  |____||_| \__|\___||_|  \__,_||_|/__/
//










//   _                  _        _
//  | |    __ _  _ __  | |__  __| | __ _  ___
//  | |__ / _` || '  \ | '_ \/ _` |/ _` |(_-<
//  |____|\__,_||_|_|_||_.__/\__,_|\__,_|/__/
//


val isPalindrome = { str: String -> str.toLowerCase() == str.reversed().toLowerCase() }
val isEven = { i: Int -> i % 2 == 0 }

val medianOf = lambda@{ ints: IntArray ->
  if (ints.isEmpty()) {
    return@lambda 0
  } else if (ints.size == 1) {
    ints.first()
  } else {
    val middle = (ints.size * 0.5f).roundToInt() - 1
    if (isEven(ints.size)) {
      ((ints[middle] + ints[middle + 1]) * 0.5f).roundToInt()
    } else {
      ints[middle]
    }
  }
}



// But no non-local returns. 😞










//     ___ __  __ ______ ____ _____ ____   ___   ____
//    / _ )\ \/ //_  __// __// ___// __ \ / _ \ / __/
//   / _  | \  /  / /  / _/ / /__ / /_/ // // // _/
//  /____/  /_/  /_/  /___/ \___/ \____//____//___/
//     ___   ___   ____ ___    __ __ __
//    / _ ) / _ \ / __// _ |  / //_// /
//   / _  |/ , _// _/ / __ | / ,<  /_/
//  /____//_/|_|/___//_/ |_|/_/|_|(_)
//




// 🎩 tip to Victoria Gonda ❤️
//
// Check out her talks, "Kotlin Uncovered" and "Understand Every Line of Your Codebase"
// https://youtu.be/8GhBDMKtEdM | https://youtu.be/DhfB9ulli08










//
// Let's talk higher-order functions…
//
// Passing functions as parameters and return functions as values…
//










//region // Pretend We Have Some Event Tracking Setup
enum class Destination { LOG, FILE, CLOUD }

data class TrackingEvent(val desc: String, val instant: Instant = Instant.now())

fun writeToFile(filename: String, event: TrackingEvent) = Unit
class TrackingService {
  fun sendEvent(event: TrackingEvent) = Unit
}

val trackingService = TrackingService()
//endregion

fun trackToLog(desc: String) {
  println(TrackingEvent(desc))
  println("Sent to log: $desc")
}

fun trackToFile(desc: String) {
  writeToFile("recent_events.txt", TrackingEvent(desc))
  println("Send to file: $desc")
}

fun trackToCloud(desc: String) {
  trackingService.sendEvent(TrackingEvent(desc))
  println("Sent to cloud: $desc")
}

fun getTracker(destination: Destination): (String) -> Unit {
  return when (destination) {
    Destination.LOG -> ::trackToLog
    Destination.FILE -> ::trackToFile
    Destination.CLOUD -> ::trackToCloud
  }
}

fun trackSomeStuff(tracker: (String) -> Unit) {
  // Do some stuff.
  tracker("We did some stuff!")
  // Do some other stuff
  tracker("We did some other stuff!")
}










//     _
//    /_\   _ _   ___  _ _  _  _  _ __   ___  _  _  ___
//   / _ \ | ' \ / _ \| ' \| || || '  \ / _ \| || |(_-<
//  /_/ \_\|_||_|\___/|_||_|\_, ||_|_|_|\___/ \_,_|/__/
//                           |__/
//   ___                 _    _
//  | __|_  _  _ _   __ | |_ (_) ___  _ _   ___
//  | _|| || || ' \ / _||  _|| |/ _ \| ' \ (_-<
//  |_|  \_,_||_||_|\__| \__||_|\___/|_||_|/__/
//




val isChrFile = { filename: String -> filename.endsWith(".chr") }

val isCharacterFile = fun(filename: String): Boolean {
  return filename.endsWith(".chr")
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










//
//      _____  _                     _                  _
//     / ____|| |                   | |                | |
//    | (___  | |_  __ _  _ __    __| |  __ _  _ __  __| |
//     \___ \ | __|/ _` || '_ \  / _` | / _` || '__|/ _` |
//     ____) || |_| (_| || | | || (_| || (_| || |  | (_| |
//    |_____/  \__|\__,_||_| |_| \__,_| \__,_||_|   \__,_|
//     ______        _                     _
//    |  ____|      | |                   (_)
//    | |__   __  __| |_  ___  _ __   ___  _   ___   _ __   ___
//    |  __|  \ \/ /| __|/ _ \| '_ \ / __|| | / _ \ | '_ \ / __|
//    | |____  >  < | |_|  __/| | | |\__ \| || (_) || | | |\__ \
//    |______|/_/\_\ \__|\___||_| |_||___/|_| \___/ |_| |_||___/
//
//










//
// apply and let and run, oh my!
// -----------------------------------

class MemberConfig(
    val memberId: String,
    var lastLogin: Instant = Instant.MIN,
    var darkMode: Boolean = true,
    var bossMode: Boolean = false,
    var aLaMode: Flavor = Flavor.PLAIN,
    var depecheMode: DepecheMode = DepecheMode.ENJOY_THE_SILENCE
) {

  //region <Insert class stuff here>
  fun validateConfig(): Boolean = false

  fun saveToLocalFile(filename: String) {
    // Pretend we're doing some I/O to save this config to a file
  }

  companion object {
    fun fromText(memberId: String, text: String): MemberConfig {
      return MemberConfig(memberId)
    }

    fun fromCloud(memberId: String): MemberConfig? {
      return memberService.getUserConfig(memberId)
    }

    fun fromFile(memberId: String): MemberConfig {
      val contents = File("$memberId.chr").readText(Charsets.UTF_8)
      return MemberConfig.fromText(memberId, contents)
    }
  }
  //endregion
}

fun startMemberSession() {
  // apply! initialization!
  val config: MemberConfig? = MemberConfig.fromCloud("monika")?.apply {
    bossMode = validateConfig()
    aLaMode = Flavor.NEAPOLITAN
  }
  // also! side effects!
  config?.also {
    val filename = "${it.memberId}.chr"
    it.saveToLocalFile(filename)
    println("Saved to $filename")
  }
  // run! do things!
  MemberSession().run {
    load(config)
    start()
  }
}








//
// Let's take a look at the standard extensions
// and a couple not-extensions…
//




//
// For guidelines on what to use when, check out…
// "Two Stones, One Bird: Implementation Tradeoffs by Christina Lee"
// https://youtu.be/YxOTU9F_YX4
//










//
// Figuring out which is why and how they differ…
//
// 1. Is it an extension?
//
// 2. Does it return the receiver or a new value?
//
// 3. Do you pass a lambda or a lambda with receiver?
//










// Let's look at the source for those standard extensions… *



inline fun <R> run(block: () -> R): R {
  return block()
}

inline fun <T, R> with(receiver: T, block: T.() -> R): R {
  return receiver.block()
}

inline fun <T, R> T.run(block: T.() -> R): R {
  return block()
}

inline fun <T> T.apply(block: T.() -> Unit): T {
  block()
  return this
}

inline fun <T> T.also(block: (T) -> Unit): T {
  block(this)
  return this
}

inline fun <T, R> T.let(block: (T) -> R): R {
  return block(this)
}






//   ___       _                   _
//  | __|__ __| |_  ___  _ _   ___(_) ___  _ _   ___
//  | _| \ \ /|  _|/ -_)| ' \ (_-<| |/ _ \| ' \ (_-<
//  |___|/_\_\ \__|\___||_||_|/__/|_|\___/|_||_|/__/
//

//     In Java we might write…

//     public static String removeDiacriticals(String str) {
//       return Normalizer.normalize(str, Normalizer.Form.NFD)
//           .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
//     }
//
//     StringUtil.removeDiacriticals("đặc biệt")
//


private val diacriticalsRegex =
    Regex("\\p{InCombiningDiacriticalMarks}+")

fun String.removeDiacriticals(): String {
  return Normalizer.normalize(this, Normalizer.Form.NFD)
      .replace(diacriticalsRegex, "")
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










fun otherAwesomeStdlibExtensions() {
  val str: String? = "Klaatu barada nickel"
  str.isNullOrEmpty()
  str?.endsWith("nikto")
  str?.codePointCount(0, str.lastIndex)

  val strings = listOf("Groovy.", "Shop smart. Shop S-mart", "Boom stick", "Housewares")
  strings.filter { it.contains("oo") }
  strings.reversed()
}










//   _                  _        _                       __
//  | |    __ _  _ __  | |__  __| | __ _  ___  __ __ __ / /
//  | |__ / _` || '  \ | '_ \/ _` |/ _` |(_-<  \ V  V // /
//  |____|\__,_||_|_|_||_.__/\__,_|\__,_|/__/   \_/\_//_/
//   ___                _
//  | _ \ ___  __  ___ (_)__ __ ___  _ _  ___
//  |   // -_)/ _|/ -_)| |\ V // -_)| '_|(_-<
//  |_|_\\___|\__|\___||_| \_/ \___||_|  /__/
//

class AiTester(private val version: String) {
  private val rand: Random = Random()
  fun activate() {
    // Turn it on.
    println("ACTIVATE Genetic Lifeform and Disk OS. Version $version")
  }

  fun deactivate() {
    // Turn it off.
    println("DEACTIVE Genetic Lifeform and Disk OS.")
  }

  fun runTest(testId: Long, subjectName: String): Boolean {
    // Welcome to the Aperture Science computer-aided enrichment center.
    println("Execution complete. Test $testId. Subject designation: $subjectName.")
    return rand.nextBoolean()
  }

  fun cancelTest(testId: Long) {
    // Nope.
    println("Cancelled $testId")
  }

  fun ventNeurotoxin(concentration: Float) {
    // Put it on cereal.
    println("Venting $concentration neurotoxin…")
  }

  fun encourageSubject(subjectName: String) {
    // Shallow platitude.
    println("PA communication to subject designated $subjectName: \"You're doing quite well.\"")
  }

  fun bakeCake(flavor: Flavor) {
    // There will be cake.
    println("Baked. Now cake. $flavor.")
  }

  fun toPotato(): Potato {
    return Potato()
  }
}

val multipleTest: AiTester.(String) -> Unit = { subjectName: String ->
  for (testId in 0x0001L..0x0010L) {
    runTest(testId, subjectName)
  }
}

val testOrBake: AiTester.(String) -> Unit = { subjectName: String ->
  if (runTest(0x0088, subjectName)) {
    encourageSubject(subjectName)
  } else {
    bakeCake(Flavor.CHOCOLATE)
  }
}

fun runTestSession(version: String, subject: String, block: AiTester.(String) -> Unit) {
  val glados = AiTester(version)
  glados.activate()
  glados.block(subject)
  glados.deactivate()
}

fun main(args: Array<String>) {
  runTestSession("C4.K3", "Chell") { subjectName ->
    if (runTest(0x0004, subjectName)) {
      encourageSubject(subjectName)
    } else {
      bakeCake(Flavor.CHOCOLATE)
    }
  }
}










//
// Execute Around Method (EAM) pattern
//
// You can see EAM patterns in lots of places in the stdlib…
//
//
// inline fun <T : Closeable?, R> T.use(block: (T) -> R): R (source)
//

fun readFile() {
  val reader = File("just_monika.chr").bufferedReader()
  reader.use {
    it.readText()
  }
}









// beginregion
//
// Android KTX has tons of examples of leveraging
// lambdas and lambdas with receivers
//
//
//    inline fun FragmentManager.commit(
//        allowStateLoss: Boolean = false,
//        body: FragmentTransaction.() -> Unit
//    ) {
//      val transaction = beginTransaction()
//      transaction.body()
//      if (allowStateLoss) {
//        transaction.commitAllowingStateLoss()
//      } else {
//        transaction.commit()
//      }
//    }
//
//
//    So you can do…
//
//    supportFragmentManager.commit(allowStateLoss = true) {
//      replace(R.id.container, someFragment, FRAGMENT_TAG_YOURE_IT)
//    }
//




//
//    inline fun Context.withStyledAttributes(
//        set: AttributeSet? = null,
//        attrs: IntArray,
//        @AttrRes defStyleAttr: Int = 0,
//        @StyleRes defStyleRes: Int = 0,
//        block: TypedArray.() -> Unit
//    ): Unit
//
//
//    So you can do…
//
//    withStyledAttributes(attrs) {
//      val background = getDrawable(R.styleable.MyStyleable_android_background)
//      val textBackgroundColor = getColor(R.styleable.MyStyleable_colorPrimary, Color.WHITE)
//      textColor = getColor(R.styleable.MyStyleable_colorAccent, Color.BLACK)
//    }
//









//
// Let's talk about…
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
//     _____         _  _
//    |_   _|       | |(_)
//      | |   _ __  | | _  _ __    ___
//      | |  | '_ \ | || || '_ \  / _ \
//     _| |_ | | | || || || | | ||  __/
//    |_____||_| |_||_||_||_| |_| \___|
//
//




//
// Can reduce some of the penalties of lambdas through inlining.
//
// Inline a function → calls to that function replaced
//                     with body of that function
//
// Inline a higher-order function -> both the function itself and
//                                   lambdas parameters are inlined
//











//     ___ __  __ ______ ____ _____ ____   ___   ____
//    / _ )\ \/ //_  __// __// ___// __ \ / _ \ / __/
//   / _  | \  /  / /  / _/ / /__ / /_/ // // // _/
//  /____/  /_/  /_/  /___/ \___/ \____//____//___/
//     ___   ___   ____ ___    __ __ __
//    / _ ) / _ \ / __// _ |  / //_// /
//   / _  |/ , _// _/ / __ | / ,<  /_/
//  /____//_/|_|/___//_/ |_|/_/|_|(_)
//










//
// Some inline-related modifier keywords
//

inline fun workNightShift(location: String, shiftWork: (String) -> Unit) {
  shiftWork(location)
}

inline fun workNightShift(location: String, shiftWork: (String) -> Unit, noinline report: () -> Unit) {
  shiftWork(location)
  report()
  val lambda = report
}

// Example from https://kotlinlang.org/docs/reference/inline-functions.html
var runnableForLater: Runnable? = null

inline fun crossinlined(crossinline body: () -> Unit, returnable: () -> Unit) {
  runnableForLater = Runnable { body(); }
  returnable()
}

fun usesCrossinlined() {
  crossinlined(
      body = { println("Stuff") },
      returnable = { println("Other stuff"); return }
  )
}



//
//     _____        _   __  _            _
//    |  __ \      (_) / _|(_)          | |
//    | |__) | ___  _ | |_  _   ___   __| |
//    |  _  / / _ \| ||  _|| | / _ \ / _` |
//    | | \ \|  __/| || |  | ||  __/| (_| |
//    |_|  \_\\___||_||_|  |_| \___| \__,_|
//
//

inline fun <reified T : CrewMember> List<CrewMember>.findByName(name: String): T? {
  return find { it is T && it.name == name } as T?
}

fun startMission(candidates: List<CrewMember>) {
  val captain: ImpetuousCaptain? = candidates.findByName("James Tiberius Kirk")
  val scienceOfficer: EnigmaticScienceOfficer? = candidates.findByName("Spock")
  val otherOfficer: RedShirt? = candidates.findByName("Ensign Dern")

  if (captain == null && scienceOfficer == null && otherOfficer == null) {
    // Guess we're staying home…
  } else {
    // Do some mission-y things.
  }
}






//
// BTW reified inline functions can't be called from Java.
//
// Normally inline functions can be called from Java but won't be inlined.
//


//
// For some Android examples, again check out KTX
//
// inline fun <reified T> Context.systemService() = getSystemService(T::class.java)
//










val favoritePairs = mapOf(
    "Stark" to "Banner",
    "Mulder" to "Scully",
    "Turner" to "Hooch",
    "Milly" to "Nicholas",
    "Saitama" to "Genos"
)










//
//    _____          __  _
//   |_   _|        / _|(_)
//     | |   _ __  | |_  _ __  __
//     | |  | '_ \ |  _|| |\ \/ /
//    _| |_ | | | || |  | | >  <
//   |_____||_| |_||_|  |_|/_/\_\
//
//

//
// Can call one-parameter class method/extension functions without `.` or `()`.
//

fun countdown(n: Int) {
  (n downTo 0).forEach {
    println("$it!")
  }
}

fun divideByTwo(n: Int): Int {
  return n shr 1
}










infix fun ImpetuousCaptain.startLog(starDate: String) {
  println("Captain's Log. Star date… $starDate")
}

fun assignCaptains() {
  val log = picard startLog "April 5, 2063"
  println(log)

  kirk assignedTo essEnterprise
  picard assignedTo ncc1701c
  janeway assignedTo voyager
}










//
//      _____                                 _    _
//     / ____|                               | |  (_)
//    | |      ___   _ __ __   __ ___  _ __  | |_  _   ___   _ __   ___
//    | |     / _ \ | '_ \\ \ / // _ \| '_ \ | __|| | / _ \ | '_ \ / __|
//    | |____| (_) || | | |\ V /|  __/| | | || |_ | || (_) || | | |\__ \
//     \_____|\___/ |_| |_| \_/  \___||_| |_| \__||_| \___/ |_| |_||___/
//
//

//
// Accessing language features via particular naming
// instead of particular types.
//









//      ___                         _
//     / _ \  _ __  ___  _ _  __ _ | |_  ___  _ _
//    | (_) || '_ \/ -_)| '_|/ _` ||  _|/ _ \| '_|
//     \___/ | .__/\___||_|  \__,_| \__|\___/|_|
//      ___  |_|             _                _  _
//     / _ \ __ __ ___  _ _ | | ___  __ _  __| |(_) _ _   __ _
//    | (_) |\ V // -_)| '_|| |/ _ \/ _` |/ _` || || ' \ / _` |
//     \___/  \_/ \___||_|  |_|\___/\__,_|\__,_||_||_||_|\__, |
//                                                       |___/
//


val commander = Commander("Shepard", "Specter")
val vigilante = Vigilante("Garrus Vakarian", "Archangel")
val scientist = ScientistSalarian("Mordin Solus", "Scientist Salarian")
val krogan = Krogan("Grunt", "")
val engineer = Engineer("Tali Z'orah", "vas Normandy")

fun recruitSquad(): Squad {
  val squad = Squad()

  squad += vigilante
  squad += krogan

  squad -= krogan

  return squad
}










//
//     _____              _                       _                 _
//    |  __ \            | |                     | |               (_)
//    | |  | |  ___  ___ | |_  _ __  _   _   ___ | |_  _   _  _ __  _  _ __    __ _
//    | |  | | / _ \/ __|| __|| '__|| | | | / __|| __|| | | || '__|| || '_ \  / _` |
//    | |__| ||  __/\__ \| |_ | |   | |_| || (__ | |_ | |_| || |   | || | | || (_| |
//    |_____/  \___||___/ \__||_|    \__,_| \___| \__| \__,_||_|   |_||_| |_| \__, |
//     _____               _                     _    _                        __/ |
//    |  __ \             | |                   | |  (_)                      |___/
//    | |  | |  ___   ___ | |  __ _  _ __  __ _ | |_  _   ___   _ __   ___
//    | |  | | / _ \ / __|| | / _` || '__|/ _` || __|| | / _ \ | '_ \ / __|
//    | |__| ||  __/| (__ | || (_| || |  | (_| || |_ | || (_) || | | |\__ \
//    |_____/  \___| \___||_| \__,_||_|   \__,_| \__||_| \___/ |_| |_||___/
//
//


fun whoGoesFirst(squad: List<SquadMember>) {
  val (first, second) = squad
  println("#2 → $second")
  println("#1 → $first")
}












//
//     _____         _                      _
//    |  __ \       | |                    | |
//    | |  | |  ___ | |  ___   __ _   __ _ | |_  ___  ___
//    | |  | | / _ \| | / _ \ / _` | / _` || __|/ _ \/ __|
//    | |__| ||  __/| ||  __/| (_| || (_| || |_|  __/\__ \
//    |_____/  \___||_| \___| \__, | \__,_| \__|\___||___/
//                             __/ |
//                            |___/
//
//

class SillyNameDelegate {

  private var actualName: String? = null

  private val random = Random()
  private val placeholderNames: List<String> =
      listOf("Englebert", "Cheeto", "Mojo", "Protagonist", "Kopfgeschlagen")

  // Getter
  operator fun getValue(thisRef: Any, property: KProperty<*>): String? {
    return actualName ?: placeholderNames[random.nextInt(placeholderNames.size)]
  }

  // Setter
  operator fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
    actualName = value
  }
}

class SillyUser {
  var name by SillyNameDelegate() // Actual property delegation.
}

fun doSomeSillyUserThings() {
  val user = SillyUser()
  println(user.name) // Protagonist
  println(user.name) // Englebert
  user.name = "Jerry"
  println(user.name) // Jerry
  println(user.name) // Jerry
}










//
//    _____               _                      _
//   / ____|             | |                    | |
//  | |      ___   _ __  | |_  _ __  __ _   ___ | |_  ___
//  | |     / _ \ | '_ \ | __|| '__|/ _` | / __|| __|/ __|
//  | |____| (_) || | | || |_ | |  | (_| || (__ | |_ \__ \
//   \_____|\___/ |_| |_| \__||_|   \__,_| \___| \__||___/
//
//
//  https://github.com/Kotlin/KEEP/blob/master/proposals/kotlin-contracts.md
//
//  https://blog.jetbrains.com/kotlin/2018/08/kotlin-1-3-m2/
//
//  Kotlin 1.3!




fun installSphereIntoQuantumFluxDrive(sphere: Sphere?) {
  requireNotNull(sphere)
  println("Is sphere beryllium: ${sphere.material == Material.BERYLLIUM}")
}










@ExperimentalContracts
fun operate(equipment: String, block: (CrewMember) -> Unit) {
  contract {
    callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  block(StalwartEngineer("Chen", 2, false))
}

@ExperimentalContracts
fun transport(operator: CrewMember?) {
  val insideOut: Boolean
  val exploded: Boolean
  val transported: Boolean

  operate("digitizer") {
    if (it is StalwartEngineer) {
      insideOut = false
      exploded = false
      transported = true
    } else {
      insideOut = true
      exploded = true
      transported = false
    }
  }
}







//
//   _______ _                 _
//  |__   __| |               | |
//     | |  | |__   __ _ _ __ | | __  _   _  ___  _   _
//     | |  | '_ \ / _` | '_ \| |/ / | | | |/ _ \| | | |
//     | |  | | | | (_| | | | |   <  | |_| | (_) | |_| |
//     |_|  |_| |_|\__,_|_| |_|_|\_\  \__, |\___/ \__,_|
//                                     __/ |
//                                     |___/
//                                    _     _
//                                   | |   | |
//   ___  ___    _ __ ___  _   _  ___| |__ | |
//  / __|/ _ \  | '_ ` _ \| | | |/ __| '_ \| |
//  \__ \ (_) | | | | | | | |_| | (__| | | |_|
//  |___/\___/  |_| |_| |_|\__,_|\___|_| |_(_)
//
//
//  Huyen Tue Dao
//  @queencodemonkey
//  randomlytyping.com
//