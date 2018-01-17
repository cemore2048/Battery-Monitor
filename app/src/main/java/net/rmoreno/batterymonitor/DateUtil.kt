package net.rmoreno.batterymonitor


object DateUtil {

    fun formatElapsedTime(immuteDifference : Long): String {
        var mutableDifference = immuteDifference
        val secondsInMilli : Long = 1000
        val minutesInMilli : Long = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = mutableDifference / daysInMilli
        mutableDifference = mutableDifference % daysInMilli

        val elapsedHours = mutableDifference / hoursInMilli
        mutableDifference = mutableDifference % hoursInMilli

        val elapsedMinutes = mutableDifference / minutesInMilli
        mutableDifference = mutableDifference % minutesInMilli

        val elapsedSeconds = mutableDifference / secondsInMilli

        return formatElapsedTime(elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds)

    }

    private fun formatElapsedTime(elapsedDays: Long, elapsedHours: Long, elapsedMinutes: Long, elapsedSeconds: Long) : String {
        var time : String? = null

        when {
            elapsedDays > 1 -> {
                time += elapsedDays.toString() + " Days : "
            }
            elapsedHours > 1 -> {
                time += elapsedHours.toString() + " Hours : "
            }
            elapsedMinutes > 1-> {
                time += elapsedMinutes.toString() + " Minutes : "
            }
            elapsedSeconds > 1-> {
                time += elapsedSeconds.toString() + " Seconds : "
            }
        }
        return time!!
    }


}