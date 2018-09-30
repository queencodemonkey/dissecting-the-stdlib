
// region // Things you'll need for the next Five Nights
fun clockIn(location: String) = Unit
fun turnOnLights() = Unit
fun watchSecurityCamera() = Unit
fun closeDoors() = Unit
fun checkRemainingBattery(): Float = 0.5F
fun isFanRunning(): Boolean = true
fun waitItOut(seconds: Long) = Unit
fun clockOut(location: String) = Unit
// endregion





inline fun workShift(location: String, block: (String) -> Unit) {
  println("Shift start battery level: ${checkRemainingBattery()}")
  block(location)
  println("Shift end battery level: ${checkRemainingBattery()}")
}

fun main(args: Array<String>) {
  workShift("FNAF01") { location: String ->
    if (checkRemainingBattery() < 1f) {
      return
    }
    clockIn(location)
    closeDoors()
    watchSecurityCamera()
    turnOnLights()
    clockOut(location)
  }
}








// Also… non-local returns!