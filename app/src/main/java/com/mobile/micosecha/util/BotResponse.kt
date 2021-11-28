package com.mobile.micosecha.util


import android.os.Build
import androidx.annotation.RequiresApi
import com.mobile.micosecha.util.Constants.DATE
import com.mobile.micosecha.util.Constants.OPEN_GOOGLE
import com.mobile.micosecha.util.Constants.OPEN_INSTA
import com.mobile.micosecha.util.Constants.OPEN_SEARCH
import com.mobile.micosecha.util.Constants.OPEN_SPOTIFY
import com.mobile.micosecha.util.Constants.OPEN_WHATSAPP
import com.mobile.micosecha.util.Constants.OPEN_YOUTUBE
import com.mobile.micosecha.util.Constants.TIME

object BotResponse {

    // Todo.... ADD FUNCTION SLOT IN RESPONSE -> response("Hello!", listOf("hello", "hi", "hey", "sup"), singleResponse = true, _____fun_____)

    // -------------------------------------------------------------------------------------------------

    @RequiresApi(Build.VERSION_CODES.O)
    fun basicResponses(_message: String): String {
        val msg = filterString(_message)
        return checkAllMessages(msg)
    }

    private fun filterString(message: String): String {
        return message.replace("?", "").replace(",", "")
            .replace(".", "").replace("!", "")
    }

    private fun messageProb(userMsg: String, recognisedWords: List<String>, singleResponse:
    Boolean = false, requiredWords: List<String>): Int {
        var messageCertainty: Int = 0
        var hasRequiredWords: Boolean = false

        // counts how many words are present in each predefined message
        for(word in userMsg.split(" ")){
            if(recognisedWords.contains(word)) messageCertainty += 1
        }

        // Calculates the percent of recognised words in a user message
        val percentage: Float = (messageCertainty).toFloat() / (recognisedWords.size).toFloat()

        // Check that the required words are in the string
        for(word in requiredWords){
            if(userMsg.contains(word)){
                hasRequiredWords = true
                break
            }
        }

        // Must either have the required words, or be a single response
        return if(hasRequiredWords || singleResponse){
            (percentage * 100).toInt()
        }else{
            0
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAllMessages(message: String): String{

        val highestProbList = mutableMapOf<String, Int>()
        fun response(botResponse: String, listOfWords: List<String>, singleResponse: Boolean =false, requiredWords: List<String> = listOf()){
            highestProbList[botResponse] =
                messageProb(message, listOfWords, singleResponse, requiredWords)
        }

        // Responses ---------------------------------------------

        response("Hello!", listOf("hello", "hi", "hey", "sup"), singleResponse = true)
        response("See you", listOf("bye", "by", "goodbye"), singleResponse = true)
        response("I am fine", listOf("how", "are", "you"), singleResponse = true, requiredWords = listOf("how"))
        response("You're welcome", listOf("thanks", "thank", "thank you"), singleResponse = true)
        response("hurrah!", listOf("enjoy", "have fun", "good", "yeah"), singleResponse = true)
        response(TIME, listOf("tell", "time"), singleResponse = true, requiredWords = listOf("time"))
        response(DATE, listOf("tell", "date", "year", "month", "day", "which", "today"), singleResponse = true, requiredWords = listOf("date", "month", "year", "today", "day"))
        response(OPEN_SEARCH, listOf("search", "for", "on", "internet", "google"), singleResponse = true, requiredWords = listOf("search"))
        response(OPEN_GOOGLE, listOf("open", "google", "chrome", "run"), singleResponse = true, requiredWords = listOf("open", "run"))
        response(OPEN_INSTA, listOf("open", "instagram", "insta"), singleResponse = true, requiredWords = listOf("instagram", "insta"))
        response(OPEN_WHATSAPP, listOf("open", "whatsapp", "whats app"), singleResponse = true, requiredWords = listOf("whatsapp", "whats app"))
        response(OPEN_YOUTUBE, listOf("open", "youtube", "video"), singleResponse = true, requiredWords = listOf("youtube", "video"))
        response(OPEN_SPOTIFY, listOf("open", "spotify", "music", "songs", "player"), singleResponse = true, requiredWords = listOf("spotify", "music", "songs"))
        response("I'm Bot!", listOf("who", "are", "you", "tell", "about", "what"), singleResponse = true, requiredWords = listOf("who", "what", "you"))
        response("You are my Boss", listOf("who", "am", "i", "tell", "me", "about"), singleResponse = true, requiredWords = listOf("i", "me", "myself"))
        response("Thanks!", listOf("you", "are", "awesome", "fantastic", "amazing", "cool", "good", "nice", "better"), singleResponse = true, requiredWords = listOf("you", "your", "you're", "you are"))
        response("Yah! \nYou are", listOf("i", "am", "awesome", "fantastic", "amazing", "cool", "good", "nice", "better"), singleResponse = true, requiredWords = listOf("i'm", "me", "i am", "you are"))
        response("Yes. It is...!", listOf("it's", "it is", "day", "awesome", "fantastic", "amazing", "cool", "good", "nice", "better"), singleResponse = true, requiredWords = listOf("i'm", "me", "i am", "you are"))
        response("~~ Dhruv Soni ~~", listOf("Who", "is", "your", "owner", "malik", "kaun", "kon", "h", "developer", "creator", "designer"), singleResponse = true)
        response("Kotlin!", listOf("you", "developed", "language", "lan", "coding", "develop"), singleResponse = true)
        response("Currently my Version is \n0.1", listOf("version", "current", "model", "number", "no"), singleResponse = true, requiredWords = listOf("version", "model"))

        // Need to find highest probability message from map
        // {("hello", 23), ("see you", 6)}

        val arr: ArrayList<Int> = ArrayList()
        for(values in highestProbList.values){
            arr.add(values)
        }
        val bestMatch = arr.maxOrNull()

        val ans = (highestProbList.filterValues {
            it == bestMatch
        })

        val prob: Int? = ans.values.firstOrNull()

        val exe: String = "Be clear..."
        return if (prob != null) {
            if (prob < 2){
                exe
            } else {
                addfunc((ans.keys.first()).toString())
            }
        }else {
            "NULL"
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun addfunc(botResponse: String): String {
        return when (botResponse) {
            TIME -> {
                Time.getTime()
            }
            DATE -> {
                Time.getDay()
            }
            else -> botResponse
        }
    }

}
// -------------------------------------------------------------------------------------------------

//    fun basicResponses(_message: String): String {
//
//        val random = (0..2).random()
//        val message =_message.toLowerCase()
//        val hello: List<String> = listOf("hey", "hello", "what's up", "hi", "what is up", "hello")
//
//        return when {
//
//            //Flips a coin
//            message.contains("flip") && message.contains("coin") -> {
//                val r = (0..1).random()
//                val result = if (r == 0) "heads" else "tails"
//
//                "I flipped a coin and it landed on $result"
//            }
//
//            //Math calculations
//            message.contains("solve") -> {
//                val equation: String? = message.substringAfterLast("solve")
//                return try {
//                    val answer = SolveMath.solveMath(equation ?: "0")
//                    "$answer"
//
//                } catch (e: Exception) {
//                    "Sorry, I can't solve that."
//                }
//            }
//
//            //Hello
////            for(item: String in hello){
////                if(message.contains(item))-> {
////                    when (random) {
////                        0 -> "Hello there!"
////                        1 -> "Sup"
////                        2 -> "Buongiorno!"
////                        else -> "error" }
////                }
////            }
//            message.contains("hello") || message.contains("hi") -> {
//                when (random) {
//                    0 -> "Hello there!"
//                    1 -> "Sup"
//                    2 -> "Buongiorno!"
//                    else -> "error" }
//            }
//
//            //Good
//            message.contains("Good") || message.contains("well done") -> {
//                when (random) {
//                    0 -> "Thanks."
//                    1 -> "Thank you."
//                    2 -> "Yup!"
//                    else -> "error" }
//            }
//
//            //Good
//            message.contains("thanks") || message.contains("thank you") -> {
//                when (random) {
//                    0 -> "Your welcome."
//                    1 -> "You are welcome."
//                    2 -> "It's Alright!"
//                    else -> "error" }
//            }
//
//            //How are you?
//            message.contains("how are you") -> {
//                when (random) {
//                    0 -> "I'm doing fine, thanks!"
//                    1 -> "I'm hungry..."
//                    2 -> "Pretty good! How about you?"
//                    else -> "error"
//                }
//            }
//
//            //What time is it?
//            message.contains("time") && message.contains("?") -> {
////                val timeStamp = Timestamp(System.currentTimeMillis())
////                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
////                val date = sdf.format(Date(timeStamp.time))
////
////                date.toString()
//                Time.timeStamp()
//            }
//
//            //Open Google
//            message.contains("open") && message.contains("google") -> {
//                OPEN_GOOGLE
//            }
//
//            //Search on the internet
//            message.contains("search") -> {
//                OPEN_SEARCH
//            }
//
//            //When the programme doesn't understand...
//            else -> {
//                when (random) {
//                    0 -> "I don't understand..."
//                    1 -> "Try asking me something different"
//                    2 -> "Idk"
//                    else -> "error"
//                }
//            }
//        }
//    }
//}